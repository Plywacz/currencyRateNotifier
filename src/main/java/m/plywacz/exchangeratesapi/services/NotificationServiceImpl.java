package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.dto.NotificationDto;
import m.plywacz.exchangeratesapi.exceptions.ResourceNotFoundException;
import m.plywacz.exchangeratesapi.model.Notification;
import m.plywacz.exchangeratesapi.model.User;
import m.plywacz.exchangeratesapi.repo.NotificationRepo;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import m.plywacz.exchangeratesapi.services.schedule.JobManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/*
maps notification in db with those in quartz scheduler i.e you delete notification from db ypu kill notification task,
keeps same id in db and as the id of quartz jobs
 */
@Component
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;
    private final JobManager jobManager;

    public NotificationServiceImpl(NotificationRepo notificationRepo, UserRepo userRepo, JobManager jobManager) {
        this.notificationRepo = notificationRepo;
        this.userRepo = userRepo;
        this.jobManager = jobManager;
    }

    //adds notification to db and to quartz scheduler
    @Override public Notification addNotification(NotificationDto notificationDto) {
        var newNotification = insertNotificationToDb(notificationDto);

        jobManager.scheduleJob(newNotification);

        return newNotification;
    }

    private Notification insertNotificationToDb(NotificationDto notificationDto) {
        var user = userRepo.findById(notificationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user with id: " + notificationDto.getUserId()));

        Notification newNotification = convertDto(notificationDto, user);
        user.addNotification(newNotification);

        notificationRepo.save(newNotification);
        userRepo.save(user);
        return newNotification;
    }

    private Notification convertDto(NotificationDto notificationDto, User user) {
        var newNotification = new Notification();

        newNotification.setUser(user);//important!

        newNotification.setCurrency(notificationDto.getCurrency());
        newNotification.setFrequency(notificationDto.getFrequency());
        newNotification.setSendingValue(notificationDto.getCurrencyVal());
        return newNotification;
    }

    @Override public List<Notification> getAllNotification() {
        return notificationRepo.findAll();
    }

    /*
    Deletes notification in db and a notification job
     */
    @Override public void deleteNotification(Long id) {
        var notification = notificationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("notification with id: " + id));

        notificationRepo.delete(notification);
        jobManager.killJob(notification);
    }

}
