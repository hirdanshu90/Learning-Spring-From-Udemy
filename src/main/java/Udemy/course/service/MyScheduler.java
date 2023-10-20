package Udemy.course.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


// Class for scheduler OR using CRON expression ....
@Service
public class MyScheduler {

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void firstScheduler() {
        System.out.println(new Date());
    }

}
