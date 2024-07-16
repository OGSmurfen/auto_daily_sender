package com.papasmurfie.webapp_glassfish;

import Services.UserService;
import entity.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Transactional
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String username = (String) request.getAttribute("username");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!password.equals(confirmPassword)){
            request.setAttribute("errorMessage", "Passwords must match");
            request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);
        }else{
            //if passwords match
            if(isThereSuchUser(username, password)){
                request.setAttribute("errorMessage", "Username already exists");
                request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);
            }else{
                //if username is free -> create new user
                User newUser = new User();
                newUser.setUsername(username); newUser.setPassword(password);
                userService.createUser(newUser);

                request.setAttribute("errorMessage", "User created successfully");
                request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);
            }
        }



//        RequestDispatcher dispatcher = request.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage"));
//        dispatcher.forward(request, response);
    }

    private boolean isThereSuchUser(String username, String password){
        List<User> retreivedUsers = userService.findAllByUsername(username);
        for (User user : retreivedUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}