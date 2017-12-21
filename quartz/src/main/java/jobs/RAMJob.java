package jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/25
 * Time: 15:24
 */
public class RAMJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(RAMJob.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("Say hello to Quartz" + new Date());
    }
}
