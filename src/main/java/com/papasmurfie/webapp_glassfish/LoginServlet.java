package com.papasmurfie.webapp_glassfish;

import Services.UserService;
import entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.PasswordEncryptUtil;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Inject
    private UserService userService;

    private String errorMessage = "Invalid username or password!";

    @Override
    public void init() throws ServletException {
        errorMessage = "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        try {
            if(validateLogin(username, password)){
                req.setAttribute("username", username);

                errorMessage = "";
                RequestDispatcher dispatcher = req.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage"));
                dispatcher.forward(req, resp);
            }else{
                errorMessage = "Invalid username or password!";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher(getServletContext().getInitParameter("indexPage")).forward(req, resp);
            }
        } catch (NoSuchAlgorithmException e) {
            errorMessage = "Password validation went wrong!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher(getServletContext().getInitParameter("indexPage")).forward(req, resp);
        }

    }

    private boolean validateLogin(String un, String pw) throws NoSuchAlgorithmException {
        User retrievedUser = userService.getUserByUsername(un);
        if(retrievedUser == null){
            return false;
        }
        if(retrievedUser.getSalt() != null){
            return PasswordEncryptUtil.validatePassword(pw, retrievedUser.getPassword(), retrievedUser.getSalt());
        }
        return Objects.equals(retrievedUser.getPassword(), pw);
    }
}