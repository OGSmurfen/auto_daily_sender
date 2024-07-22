package com.papasmurfie.webapp_glassfish;

import Services.UserService;
import entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.transaction.Transactional;
import utils.EmailSender;
import utils.PasswordEncryptUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String username = (String) request.getAttribute("username");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        if(!password.equals(confirmPassword)){
            request.setAttribute("errorMessage", "Passwords must match");
            request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);
        }else{
            //if passwords match
            if(isThereSuchUser(username)){
                request.setAttribute("errorMessage", "Username already exists");
                request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);
            }else{
                //if username is free -> create new user
                createUserWithEncryptedPassword(request, response, username, password, email);
            }
        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage"));
//        dispatcher.forward(request, response);
    }

    private void createUserWithEncryptedPassword(HttpServletRequest request, HttpServletResponse response, String username, String password, String email) throws ServletException, IOException {
        try {
            byte[] salt = PasswordEncryptUtil.generateSalt();
            String hashedPassword = PasswordEncryptUtil.hashPassword(password, salt);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(hashedPassword);
            newUser.setSalt(salt);
            newUser.setEmail(email);

            userService.createUser(newUser);

            request.setAttribute("errorMessage", "User created successfully");
            request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);

        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("errorMessage", "Password encryption went wrong");
            request.getRequestDispatcher(getServletContext().getInitParameter("registerPage")).forward(request, response);

        }
    }
    private boolean isThereSuchUser(String username){
        return userService.getUserByUsername(username) != null;
    }
}