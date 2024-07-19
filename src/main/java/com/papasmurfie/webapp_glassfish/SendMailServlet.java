package com.papasmurfie.webapp_glassfish;

import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EmailSender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "SendMailServlet", value = "/SendMailServlet")
public class SendMailServlet extends HttpServlet {
    @Inject
    private EmailSender emailSender;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendMail(req, resp);
    }






    private void sendMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mailBody = req.getParameter("mailcontent");
        String recipient = req.getParameter("recipient");
        try {
            emailSender.sendEmail(recipient, "Auto-mail", mailBody);
            req.setAttribute("errorMessage", "Mail sent successfully!");
            req.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage")).forward(req, resp);
        } catch (MessagingException e) {
            req.setAttribute("errorMessage", e.toString());
            req.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage")).forward(req, resp);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("exception_log.txt", true))) {
                // Write the exception details to the file
                writer.write("Exception occurred: " + e.toString() + "\n");
            } catch (IOException ioException) {
                // Handle IOException if failed to write to the file
                ioException.printStackTrace();
            }
        }
    }


}