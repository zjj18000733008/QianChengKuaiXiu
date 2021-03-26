package wechatOrder.job;

import org.quartz.*;
import wechatOrder.service.impl.QuartzService;

/**
 * @author JJ
 * @date 2020/1/29 - 14:01
 */
public class QuartzJob implements Job {


    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        JobDetail jobDetail = jec.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        QuartzService quartzService = (QuartzService) jobDataMap.get("quartzService");
        quartzService.deleteTimeoutOrder();
    }
}
