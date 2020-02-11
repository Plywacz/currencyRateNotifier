package m.plywacz.exchangeratesapi.services.schedule;
/*
Author: BeGieU
Date: 10.02.2020
*/

import m.plywacz.exchangeratesapi.model.Notification;
import org.quartz.Job;

public interface JobManager {
    void scheduleJob(Notification job);

    void killJob(Notification notification);
}
