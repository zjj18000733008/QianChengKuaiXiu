package wechatOrder.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class ContextFinalizer implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(ContextFinalizer.class);
//    private QuartzService quartzService=quartzService = new QuartzService();
    private static SchedulerFactory schedulerFactory;
    private static Scheduler scheduler;

    static {
        schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void contextInitialized(ServletContextEvent sce) {
//        log.info("开始初始化石英调度>>>");
//        JobDetailImpl jobDetail = new JobDetailImpl();
//        jobDetail.setJobClass(QuartzJob.class);
//        jobDetail.setKey(new JobKey("job1", "group1"));
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.put("quartzService", quartzService);
//        jobDetail.setJobDataMap(jobDataMap);
//
//        try {
//            CronTriggerImpl cronTrigger = new CronTriggerImpl();
//            cronTrigger.setCronExpression("* 0/2 * * * ?");//每十五分钟执行一次
//            cronTrigger.setName("trigger1");
//            cronTrigger.setGroup("group1");
//            scheduler.scheduleJob(jobDetail, cronTrigger);
//            scheduler.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//

    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            boolean started = scheduler.isStarted();
            if (started) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                System.out.println(String.format("ContextFinalizer:Driver %s deregistered", d));
            } catch (SQLException ex) {
                System.out.println(String.format("ContextFinalizer:Error deregistering driver %s", d) + ":" + ex);
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}