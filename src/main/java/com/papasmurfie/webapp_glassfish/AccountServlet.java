package com.papasmurfie.webapp_glassfish;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getAttribute("username");

//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        String htmlResponse = "<html>";
//        htmlResponse += "<h2>Welcome, " + username + "!</h2>";
//        htmlResponse += "</html>";
//        writer.println(htmlResponse);

        RequestDispatcher dispatcher = request.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage"));
        dispatcher.forward(request, response);
    }
}