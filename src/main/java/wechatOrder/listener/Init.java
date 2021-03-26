package wechatOrder.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author JJ
 * @date 2020/1/29 - 18:44
 */
@Component
public class Init {
    private static final Logger log = LoggerFactory.getLogger(Init.class);
//    private QuartzService quartzService=quartzService = new QuartzService();
//    private static SchedulerFactory schedulerFactory;
//    private static Scheduler scheduler;
//
//    static {
//        schedulerFactory = new StdSchedulerFactory();
//        try {
//            scheduler = schedulerFactory.getScheduler();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }


    public void init() throws ParseException {
        log.info("开始初始化石英调度>>>");
        System.out.println(">>>");
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


    }
}
