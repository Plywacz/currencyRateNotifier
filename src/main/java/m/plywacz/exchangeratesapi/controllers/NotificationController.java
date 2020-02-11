package m.plywacz.exchangeratesapi.controllers;
/*
Author: BeGieU
Date: 10.02.2020
*/

import m.plywacz.exchangeratesapi.dto.NotificationDto;
import m.plywacz.exchangeratesapi.model.Notification;
import m.plywacz.exchangeratesapi.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification addNotification(@RequestBody @Valid NotificationDto notificationDto) {
        return notificationService.addNotification(notificationDto);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotification();
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
