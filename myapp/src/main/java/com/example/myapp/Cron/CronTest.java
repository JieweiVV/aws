package com.example.myapp.Cron;

public class CronTest {
    public static void main( String[] args) {
        String cronExpression = "0 0 0 * * ?/PT2H";
        CronTimeWindowUtils cronSchedule = CronTimeWindowUtils.createCronTimeWindowUtil (cronExpression);
        Date currentTime = new Date ();

        //Step 1 : Convert sdc config into date
        Date startTime = cronSchedule.getExecutionStartTime (currentTime);
        Date endTime = cronSchedule.getExecutionEndTime (startTime);

        System.out.println(startTime);
        System.out.println(endTime);
    }
    
}