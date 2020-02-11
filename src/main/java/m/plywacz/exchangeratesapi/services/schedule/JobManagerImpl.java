package m.plywacz.exchangeratesapi.services.schedule;
/*
Author: BeGieU
Date: 10.02.2020
*/

import m.plywacz.exchangeratesapi.model.Notification;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class JobManagerImpl implements JobManager {
    private final Scheduler scheduler;

    public JobManagerImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(Notification notification) {
        var jobDetail = createMeasurementJob(notification);
        var trigger = createTrigger(jobDetail, notification.getFrequency());
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail createMeasurementJob(Notification notification) {
        var jobDataMap = new JobDataMap();
        jobDataMap.put("notification", notification);

        return JobBuilder.newJob(NotifyJob.class)
                .withDescription("Getting currency info for " + notification.getUser().getLastName() + " with id " + notification.getUser().getId())
                .withIdentity(notification.getId().toString()) //this id is used when killing job
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();

    }

    private Trigger createTrigger(JobDetail jobDetail, Integer frequency) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withDescription("currency Trigger")
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(frequency)
                                //.withRepeatCount(DEFAULT_REPEAT_COUNT)
                                .repeatForever()
                ).build();
    }
    //============


    @Override
    public void killJob(Notification notification) {
        var jobId = new JobKey(notification.getId().toString());
        try {
            scheduler.deleteJob(jobId);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
