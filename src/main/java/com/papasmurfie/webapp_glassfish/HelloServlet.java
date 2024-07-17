package com.papasmurfie.webapp_glassfish;

import java.io.*;
import java.util.List;

import Services.UserService;
import entity.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Inject
    private UserService userService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.getAll();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Users: </h1>");
        for (User user : users) {
            out.print("<p> Username: \""+ user.getUsername() + "\"; Password: \"" + user.getPassword() + "\"</p>");
        }
        out.println("</body></html>");
    }

}