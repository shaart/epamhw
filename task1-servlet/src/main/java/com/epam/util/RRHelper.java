package com.epam.util;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Request & Response Helper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j
public class RRHelper {

  public static void writeErrorToResponse(HttpServletResponse response, Exception e) {
    try {
      writeErrorToResponse(response, e.getCause().getMessage());
    } catch (IOException ex) {
      log.error(ex);
    }
  }

  /**
   * Sets status 400 and writes JSON to response's body with <code>"errors"</code>
   *
   * @param response HTTP response
   * @param errors Error messages
   */
  public static void writeErrorToResponse(HttpServletResponse response, List<String> errors)
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
  public static void writeErrorToResponse(HttpServletResponse response, String error)
      throws IOException {
    List<String> errors = new ArrayList<>();
    errors.add(error);

    writeErrorToResponse(response, errors);
  }
}