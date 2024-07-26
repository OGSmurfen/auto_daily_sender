package com.papasmurfie.webapp_glassfish;

import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.EmailSender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "SendMailServlet", value = "/SendMailServlet")
public class SendMailServlet extends HttpServlet {
    @Inject
    private EmailSender emailSender;

    private String emailTemplatePath;

    @Override
    public void init() throws ServletException {
        emailTemplatePath = getServletConfig().getInitParameter("emailTemplatePath");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if(session == null || session.getAttribute("username") == null){
//            resp.sendRedirect("login.jsp");
//            return;
//        }

        sendMail(req, resp);
    }


    private void sendMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mailContentToday = req.getParameter("mailcontenttoday");
        String mailContentTomorrow = req.getParameter("mailcontenttomorrow");
        String mailContentIssues = req.getParameter("mailcontentissues");
        //String recipient = req.getParameter("recipient");
        String recipient = "chakarov.ilia@gmail.com";

        String template = new String(Files.readAllBytes(Paths.get(getServletContext().getRealPath(emailTemplatePath))));
        String filledTemplate = template
                .replace("[Recipient]", "OGSmurfen")
                .replace("[Today's Content]", mailContentToday)
                .replace("[Tomorrow's Content]", mailContentTomorrow)
                .replace("[Issues Content]", mailContentIssues);

        try {
            //emailSender.sendEmail(recipient, "Daily-Auto-Mail", mailBody);
            emailSender.sendEmail(recipient, "Daily-Auto-Mail", filledTemplate);
            req.setAttribute("errorMessage", "Mail sent successfully!");
            HttpSession session = req.getSession(false);
            session.setAttribute("email", "Email sent successfully");
            resp.sendRedirect("my_account.jsp");
           // req.getRequestDispatcher(getServletContext().getInitParameter("myAccountPage")).forward(req, resp);
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
