package com.example.myapp.Cron;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.springframework.scheduling.support.CronSequenceGenerator;

public class CronTimeWindowUtils {
    private static final String SEPARATOR = "/";
    private CronSequenceGenerator cronSequenceGenerator;
    private Period duration;
    private Duration intervalBetweenSchedules;

    public static CronTimeWindowUtils createCronTimeWindowUtil(String croExpressionString)
    throws IllegalArgumentException
    {
        String cronStartInstantString = StringUtils.substringBeforeLast(croExpressionString, SEPARATOR);
        String periodString = StringUtils.substringAfterLast(croExpressionString, SEPARATOR);

        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cronStartInstantString);
        Period duration = new Period(periodString);

        //Calculate intervalBetweenSchedules
        DateTime current = new DateTime(cronSequenceGenerator.next(new Date()));
        DateTime next = new DateTime(cronSequenceGenerator.next(current.toDate()));
        Duration intervalBetweenSchedules = new Duration(current, next);

        CronTimeWindowUtils cronTimeWindowUtils = new CronTimeWindowUtils();
        cronTimeWindowUtils.cronSequenceGenerator = cronSequenceGenerator;
        cronTimeWindowUtils.duration = duration;
        cronTimeWindowUtils.intervalBetweenSchedules = intervalBetweenSchedules;
        return cronTimeWindowUtils;
    }

    /**
     * The method calculates the start time for current execution for maintenance based on cron expression
     * Cron by default calculates the next execution time based on current time,
     * hence the interval between two executions is deducted from next execution time to calculate
     * the current start time
     *
     * @param currentDate - current date time
     * @return date
     */
    public Date getExecutionStartTime(Date currentDate)
    {
        DateTime executionTime = new DateTime(currentDate);
        DateTime currentTime = new DateTime(currentDate);

        // Get start - end time for next execution
        DateTime nextExecutionStartTime = getExecutionTimeFromCron(currentTime.toDate());

        //Subtract interval to get start - end time for current execution
        //Cron always gives the next window based on current time
        DateTime currentExecutionStartTime = getExecutionTimeFromCron(currentTime.minus(intervalBetweenSchedules).toDate());
        DateTime currentExecutionEndTime = currentExecutionStartTime.plus(duration);

        //If current time less than current execution start time
        //OR in between current schedule, return current schedule
        if (currentTime.isBefore(currentExecutionStartTime)
        || currentTime.isEqual(currentExecutionStartTime) || currentTime.isEqual(currentExecutionEndTime) ||
        (currentTime.isAfter(currentExecutionStartTime) && currentTime.isBefore(currentExecutionEndTime)))
        {
            executionTime = currentExecutionStartTime;
        }

        //If current time is greater than current end date, return next execution date
        if (currentTime.isAfter(currentExecutionEndTime)) {
            executionTime = nextExecutionStartTime;
        }

        return clearSeconds(executionTime.toDate());
    }

    public Date getExecutionEndTime(Date inputTime)
    {
        DateTime dateTime = new DateTime(inputTime);
        return clearSeconds(dateTime.plus(duration).toDate());
    }

    private DateTime getExecutionTimeFromCron(Date inputTime)
    {
        return new DateTime(cronSequenceGenerator.next(inputTime));
    }

    /**
     * Clears the seconds and milliseconds portion from the date object
     * @date : Accepts a date object to process the time
     */
    private static Date clearSeconds(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);
        return c.getTime();
    }
}
