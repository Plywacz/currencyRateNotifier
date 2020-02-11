package m.plywacz.exchangeratesapi.services.schedule.mail;
/*
Author: BeGieU
Date: 10.02.2020
*/

import m.plywacz.exchangeratesapi.model.Notification;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSenderImpl implements MailSender {
    @Value("${email.address}")
    private String emailAddr;
    @Value("${email.password}")
    private String emailPasswd;
    @Value("${smtp.host}")
    private String smtpHost;
    @Value("${smtp.port}")
    private String smtpPort;

    @Override
    public void sendNotification(Notification notification) throws JobExecutionException {

        var prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAddr, emailPasswd);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAddr));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(notification.getUser().getEmail())
            );
            message.setSubject("Currency rate alert");
            message.setText("Alert: your currency exceed given value: "+ notification.getSendingValue());

            Transport.send(message);

            System.out.println("Alert msg sent to: "+ notification.getUser().getEmail());
        }
        catch (MessagingException e) {//kill this job when mail issues occurs
            e.printStackTrace();

            var killThisJob = new JobExecutionException();
            killThisJob.setUnscheduleFiringTrigger(true); //set flag to kill this job
            throw killThisJob;
        }
    }
}
