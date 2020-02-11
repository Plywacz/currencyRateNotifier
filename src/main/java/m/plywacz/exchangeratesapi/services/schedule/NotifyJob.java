package m.plywacz.exchangeratesapi.services.schedule;
/*
Author: BeGieU
Date: 10.02.2020
*/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;
import m.plywacz.exchangeratesapi.model.Currency;
import m.plywacz.exchangeratesapi.model.Notification;
import m.plywacz.exchangeratesapi.model.Reading;
import m.plywacz.exchangeratesapi.repo.ReadingRepo;
import m.plywacz.exchangeratesapi.services.schedule.mail.MailSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@Component
public class NotifyJob implements Job {
    private final ReadingRepo readingRepo;
    private final URL API_URL;
    private final MailSender mailSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotifyJob(ReadingRepo readingRepo, URL API_URL, MailSender mailSender) {
        this.readingRepo = readingRepo;
        this.API_URL = API_URL;
        this.mailSender = mailSender;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        var notification = (Notification) jobExecutionContext.getMergedJobDataMap().get("notification");

        var currencyValue = readCurrencyRate(notification.getCurrency());
        currencyValue = convertCurrencyValue(currencyValue);

        insertReadingToDb(notification, currencyValue);

        if (currencyValue >= notification.getSendingValue())
            //mailSender.sendNotification(notification);      todo uncomment, delete below sout also

        System.out.println("doing job for notification with id: "+ notification.getId());
    }

    private Double convertCurrencyValue(Double currencyVal) {
        //without this conversion currencyVal says Base value in given currency i.e 1 PLN is 0.25 EUR
        return 1 / currencyVal; //i want to have given currencyVal expressed with base value. i.e 1 EUR is  4.25 PLN
    }

    private void insertReadingToDb(Notification notification, Double currencyValue) {
        var reading = new Reading();
        reading.setCurrencyValue(currencyValue);
        reading.setCurrency(notification.getCurrency());
        reading.setDateOfRead(LocalDate.now());
        reading.setNotification(notification);

        notification.addReading(reading);
        readingRepo.save(reading);
    }

    @Nullable
    private Double readCurrencyRate(Currency currency) throws JobExecutionException {
        JsonNode json = null;
        try {
            json = objectMapper.readTree(API_URL);
        }
        catch (IOException e) { //if there is problem with api kill this job
            e.printStackTrace();

            //killing this job
            var killingJobException = new JobExecutionException();
            killingJobException.setUnscheduleFiringTrigger(true); //set flag to kill this job
            throw killingJobException;
        }
        return json.get("rates").get(currency.toString()).asDouble();
    }
}
