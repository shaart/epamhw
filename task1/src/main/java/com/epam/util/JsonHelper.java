package com.epam.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonHelper {

  private static Gson gson = new Gson();

  /**
   * A utility method to send object as JSON response
   */
  public static void sendAsJson(
      HttpServletResponse response,
      Object obj) throws IOException {

    response.setContentType("application/json");

    String res = gson.toJson(obj);

    PrintWriter out = response.getWriter();
    out.print(res);
    out.flush();
  }

  public static <T> T parseModel(HttpServletRequest request, Class<T> parsingClass)
      throws IOException {
    StringBuilder buffer = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      buffer.append(line);
    }

    String payload = buffer.toString();
    return gson.fromJson(payload, parsingClass);
  }
}