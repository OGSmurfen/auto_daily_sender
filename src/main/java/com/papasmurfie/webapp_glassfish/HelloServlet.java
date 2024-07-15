package com.papasmurfie.webapp_glassfish;

import java.io.*;
import java.util.List;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @PersistenceContext
    EntityManager em;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = getAllUsers();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Users: </h1>");
        for (User user :
                users) {
            out.print("<p>"+ user.getUsername() +"</p>");
        }
        out.println("</body></html>");
    }

    private List<User> getAllUsers(){
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

}