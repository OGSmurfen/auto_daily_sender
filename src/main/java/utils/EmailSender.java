package utils;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.Dependent;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Dependent
public class EmailSender {
    @Resource(name = "java:comp/env/mail/MyMailSession")
    private Session emailSession;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Message message = new MimeMessage(emailSession);
        message.setFrom(new InternetAddress("chakarov.ilia@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//TODO: check, may be errors here !
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
