package com.kxw.Quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 

public class HelloJob implements Job{  
	 private static Logger log = LoggerFactory.getLogger(HelloJob.class);  
    
    public HelloJob(){}  
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