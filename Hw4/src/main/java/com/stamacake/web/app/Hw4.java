package com.stamacake.web.app;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloIndex", urlPatterns = "/hi")
public class Hw4  extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(Hw4.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Log: GET");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.printf("<p><a href=\"Hw4_war/about.html\">About info</a></p>");
        out.printf("<p><a href=\"Hw4_war/table.html\">Matrix</a></p>");
        out.close();
    }

}
