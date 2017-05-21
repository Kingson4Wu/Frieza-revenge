package org.kxw.helloworld;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 

public class TestJob implements Job{  
	 private static Logger log = LoggerFactory.getLogger(TestJob.class);  
    
    public TestJob(){}  
    /** 
     * Test Job == Print Job Name + Execute Time 
     * @param context 
     * @throws JobExecutionException 
     */  
    public void execute(JobExecutionContext context)throws JobExecutionException{  
        String name = context.getJobDetail().getJobDataMap().getString("name");    
        log.info("job executing..."+ name + " -->> Date : " + new Date());
        
    }  
}  