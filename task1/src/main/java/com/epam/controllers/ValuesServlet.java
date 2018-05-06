package com.epam.controllers;

import com.epam.services.ValuesService;
import com.epam.services.impl.ValuesServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

@Log4j
@WebServlet("/values")
public class ValuesServlet extends HttpServlet {

  private static final String JSON_CONTENT_TYPE = "application/json";
  private static final String PARAMETER_VALUE = "value";
  private static final String PARAMETER_NEW_VALUE = "newValue";
  private static final String NOT_FOUND_PARAMETER_VALUE =
      "Not found parameter '" + PARAMETER_VALUE + "'";
  private static final String NOT_FOUND_PARAMETER_NEW_VALUE =
      "Not found parameter '" + PARAMETER_NEW_VALUE + "'";

  private ValuesService valuesService;

  @Override
  public void init() {
    valuesService = ValuesServiceImpl.getInstance();
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      response.setContentType(JSON_CONTENT_TYPE);

      JsonElement element = getJsonObject(request).get(PARAMETER_VALUE);
      if (element == null) {
        setErrorResponse(response, NOT_FOUND_PARAMETER_VALUE);
        return;
      }

      valuesService.deleteValue(element.getAsString());
      response.setStatus(HttpServletResponse.SC_OK);

    } catch (Exception e) {
      log.error(e);
      writeErrorToResponse(response, e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      PrintWriter responseWriter = response.getWriter();

      List<String> values = valuesService.getValues();
      String outputJson = new Gson().toJson(values);

      responseWriter.print(outputJson);
      response.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception e) {
      log.error(e);
      writeErrorToResponse(response, e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      response.setContentType(JSON_CONTENT_TYPE);

      JsonObject jsonObject = getJsonObject(request);
      JsonElement element = jsonObject.get(PARAMETER_VALUE);
      if (element == null) {
        setErrorResponse(response, NOT_FOUND_PARAMETER_VALUE);
        return;
      }

      valuesService.createValue(element.getAsString());
      response.setStatus(HttpServletResponse.SC_OK);

    } catch (Exception e) {
      log.error(e);
      writeErrorToResponse(response, e);
    }
  }

  private JsonObject getJsonObject(HttpServletRequest request) throws IOException {
    StringBuilder sb = new StringBuilder();
    String buffer;
    while ((buffer = request.getReader().readLine()) != null) {
      sb.append(buffer);
    }
    String json = sb.toString();

    return new JsonParser().parse(json).getAsJsonObject();
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {

      response.setContentType(JSON_CONTENT_TYPE);

      JsonObject jsonObject = getJsonObject(request);
      JsonElement element = jsonObject.get(PARAMETER_VALUE);
      JsonElement newValue = jsonObject.get(PARAMETER_NEW_VALUE);
      if (element == null || newValue == null) {
        List<String> errors = new ArrayList<>();
        if (element == null) {
          errors.add(NOT_FOUND_PARAMETER_VALUE);
        }
        if (newValue == null) {
          errors.add(NOT_FOUND_PARAMETER_NEW_VALUE);
        }
        setErrorResponse(response, errors);
        return;
      }

      if (!element.equals(newValue)) {
        valuesService.putValue(element.getAsString(), newValue.getAsString());
      }
      response.setStatus(HttpServletResponse.SC_OK);

    } catch (Exception e) {
      log.error(e);
      writeErrorToResponse(response, e);
    }
  }

  private void writeErrorToResponse(HttpServletResponse response, Exception e) throws IOException {
    response.getWriter().write(
        String.format("{\"error\" : \"%s\"}", e.getCause().toString())
    );
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  /**
   * Sets status 400 and writes JSON to response's body with <code>"errors"</code>
   *
   * @param response HTTP response
   * @param errors Error messages
   */
  private void setErrorResponse(HttpServletResponse response, List<String> errors)
      throws IOException {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    PrintWriter responseWriter = response.getWriter();
    Gson gson = new Gson();
    String json = gson.toJson(errors);

    responseWriter.write(json);
  }

  /**
   * Sets status 400 and writes JSON to response's body with <code>"errors"</code>
   *
   * @param response HTTP response
   * @param error Error messages
   */
  private void setErrorResponse(HttpServletResponse response, String error)
      throws IOException {
    List<String> errors = new ArrayList<>();
    errors.add(error);

    setErrorResponse(response, errors);
  }
}
