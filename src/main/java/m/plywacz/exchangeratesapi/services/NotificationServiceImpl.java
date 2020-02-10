package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.dto.NotificationDto;
import m.plywacz.exchangeratesapi.exceptions.ResourceNotFoundException;
import m.plywacz.exchangeratesapi.model.Notification;
import m.plywacz.exchangeratesapi.repo.NotificationRepo;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;

    public NotificationServiceImpl(NotificationRepo notificationRepo, UserRepo userRepo) {
        this.notificationRepo = notificationRepo;
        this.userRepo = userRepo;
    }

    @Override public Notification addNotification(NotificationDto notificationDto) {
        var newNotification = insertNotificationToDb(notificationDto);


        return newNotification;
    }

    private Notification insertNotificationToDb(NotificationDto notificationDto) {
        var userOpt = userRepo.findById(notificationDto.getUserId());
        var user = userOpt.orElseThrow(() -> new ResourceNotFoundException("user with id: " + notificationDto.getUserId()));

        var newNotification = new Notification();
        newNotification.setUser(user);
        newNotification.setCurrency(notificationDto.getCurrency());
        newNotification.setFrequency(notificationDto.getFrequency());

        user.addNotification(newNotification);

        notificationRepo.save(newNotification);
        userRepo.save(user);
        return newNotification;
    }
}
