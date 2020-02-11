package m.plywacz.exchangeratesapi.services.schedule;
/*
Author: BeGieU
Date: 11.02.2020
*/

import m.plywacz.exchangeratesapi.repo.NotificationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DBJobSynchronizer implements CommandLineRunner {
    private final NotificationRepo notificationRepo;
    private final JobManager jobManager;

    public DBJobSynchronizer(NotificationRepo notificationRepo, JobManager jobManager) {
        this.notificationRepo = notificationRepo;
        this.jobManager = jobManager;
    }

    @Override public void run(String... args) throws Exception {
        synchronizeJobs();
    }

    /**
     * When app has been run this method make sure that notification in db
     * are mapped with those in quartz-scheduler, that means if there is
     * notification in db it has to be handled by quartz
     */
    private void synchronizeJobs() {
        var allJobs = notificationRepo.findAll();
        for (var job : allJobs) {
            jobManager.scheduleJob(job);
        }
    }
}
