package com.example.myapp.Cron;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class CronTest {
    public static void main( String[] args) {
        Instant.now(Clock.fixed(Instant.parse("2018-08-22T10:00:00Z"),ZoneOffset.UTC));
        Date myDate = new Date(2022, 5, 17, 23, 50);
        System.out.println(myDate.getTime());
    

        // String cronExpression = "0 0 0 * * ?/PT2H";
        // CronTimeWindowUtils cronSchedule = CronTimeWindowUtils.createCronTimeWindowUtil (cronExpression);
        // Date currentTime = new Date ();

        // //Step 1 : Convert sdc config into date
        // Date startTime = cronSchedule.getExecutionStartTime (currentTime);
        // Date endTime = cronSchedule.getExecutionEndTime (startTime);

        // DateTime executionTime = new DateTime(startTime);
        // DateTime endExeTime = new DateTime(endTime);
        // DateTime current = new DateTime(2022, 5, 17, 23, 50);

        // System.out.println("Original Time");
        // System.out.println(executionTime.toDate());
        // System.out.println(endExeTime.toDate());

        // System.out.println("Current Time");
        // System.out.println(current.toDate());

        // DateTime executionTimeWithBuffer = executionTime.plusMinutes(-15);
        // DateTime endTimeWithBuffer = endExeTime.plusMinutes(15);
        // System.out.println("Original Time with buffer");
        // System.out.println(executionTimeWithBuffer.toDate());
        // System.out.println(endTimeWithBuffer.toDate());

        // if (executionTimeWithBuffer.isBefore(endTimeWithBuffer) && executionTimeWithBuffer.isBefore(current) && current.isBefore(endTimeWithBuffer)) {
        //     System.out.println("current In slot!");
        // }

        // DateTime current1 = new DateTime(2022, 5, 17, 23, 44);
        // if (executionTimeWithBuffer.isBefore(endTimeWithBuffer) && executionTimeWithBuffer.isBefore(current1) && current1.isBefore(endTimeWithBuffer)) {
        //     System.out.println("current1 In slot!");
        // }
    }
}