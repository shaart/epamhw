package com.epam.controllers;

import static com.epam.util.JsonHelper.sendAsJson;

import com.epam.model.Value;
import com.epam.services.ValuesService;
import com.epam.services.impl.ValuesServiceImpl;
import com.epam.util.JsonHelper;
import com.epam.util.RRHelper;
import java.util.Collection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@WebServlet("/values/*")
public class ValuesServlet extends HttpServlet {

  private static final String ROOT_PATH = "/";
  private static final String PATH_SEPARATOR = "/";
  @Setter
  private static ValuesService valuesService;

  @Override
  public void init() {
    valuesService = ValuesServiceImpl.getInstance();
  }

  // DELETE /values/id
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    try {
      String pathInfo = request.getPathInfo();
      if (pathInfo == null || pathInfo.equals(ROOT_PATH)) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      String[] splits = pathInfo.split(PATH_SEPARATOR);
      if (splits.length != 2) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      Integer modelId = Integer.valueOf(splits[1]);
      Value value = valuesService.deleteValue(modelId);
      if (value == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }

      sendAsJson(response, value);
    } catch (Exception e) {
      log.error(e);
      RRHelper.writeErrorToResponse(response, e);
    }
  }

  // GET /values/
  // GET /values/id
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String pathInfo = request.getPathInfo();

      if (pathInfo == null || pathInfo.equals(ROOT_PATH)) {
        Collection<Value> models = valuesService.getValues();

        sendAsJson(response, models);
        return;
      }
      String[] splits = pathInfo.split(PATH_SEPARATOR);
      if (splits.length != 2) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      Integer valueId = Integer.valueOf(splits[1]);
      Value value = valuesService.getValue(valueId);
      if (value == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }

      sendAsJson(response, value);
    } catch (Exception e) {
      log.error(e);
      RRHelper.writeErrorToResponse(response, e);
    }
  }

  // POST /values/
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String pathInfo = request.getPathInfo();

      if (pathInfo != null && !pathInfo.equals(ROOT_PATH)) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      Value value = JsonHelper.parseModel(request, Value.class);

      Value createdValue = valuesService.createValue(value);
      if (createdValue == null) {
        RRHelper.writeErrorToResponse(response, "Already exists");
        return;
      }

      sendAsJson(response, value);
    } catch (Exception e) {
      log.error(e);
      RRHelper.writeErrorToResponse(response, e);
    }
  }

  // PUT /values/id
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
    try {
      String pathInfo = request.getPathInfo();
      if (pathInfo == null || pathInfo.equals(ROOT_PATH)) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      String[] splits = pathInfo.split(PATH_SEPARATOR);
      if (splits.length != 2) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      Integer modelId = Integer.valueOf(splits[1]);

      Value value = JsonHelper.parseModel(request, Value.class);
      Value updatedValue = valuesService.putValue(modelId, value);

      sendAsJson(response, updatedValue);
    } catch (Exception e) {
      log.error(e);
      RRHelper.writeErrorToResponse(response, e);
    }
  }
}
