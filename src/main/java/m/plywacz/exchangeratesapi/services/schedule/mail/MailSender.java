package m.plywacz.exchangeratesapi.services.schedule.mail;
/*
Author: BeGieU
Date: 10.02.2020
*/

import m.plywacz.exchangeratesapi.model.Notification;
import org.quartz.JobExecutionException;

public interface MailSender {
    void sendNotification(Notification n) throws JobExecutionException;
}
