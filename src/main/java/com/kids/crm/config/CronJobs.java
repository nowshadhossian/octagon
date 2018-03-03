package com.kids.crm.config;

import com.kids.crm.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronJobs {
    private final MailSender mailSender;

    @Autowired
    public CronJobs(MailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Scheduled(cron = "0 0 10 * * ?")
    public void sendDailyEmailToParentWithDailyExamResult(){
        mailSender.sendEmailToParentsWithDailyExamResult();
    }
}
