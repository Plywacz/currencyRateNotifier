package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/


import m.plywacz.exchangeratesapi.dto.NotificationDto;
import m.plywacz.exchangeratesapi.model.Notification;

public interface NotificationService {
    Notification addNotification(NotificationDto notificationDto);
}
