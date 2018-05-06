package com.epam.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

@Log4j
@WebServlet("")
public class MainServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    } catch (ServletException | IOException e) {
      log.error(e);
    }
  }
}
