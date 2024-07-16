package com.papasmurfie.webapp_glassfish;

import entity.User;
import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String errorMessage = "Invalid username or password!";
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void init() throws ServletException {
        errorMessage = "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        if(validateLogin(username, password)){
            req.setAttribute("username", username);

            req.removeAttribute("errorMessage");
            RequestDispatcher dispatcher = req.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage"));
            dispatcher.forward(req, resp);
        }else{
            errorMessage = "Invalid username or password!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher(getServletContext().getInitParameter("indexPage")).forward(req, resp);
        }

    }

    private boolean validateLogin(String un, String pw){//TODO: use UserService here
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", un);

        try{
            User retrievedUser = query.getSingleResult();
            return Objects.equals(retrievedUser.getPassword(), pw);

        }catch (NoResultException e){
            return false;
        }

    }
}