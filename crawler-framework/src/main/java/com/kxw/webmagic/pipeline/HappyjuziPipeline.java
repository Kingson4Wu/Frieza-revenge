package com.kxw.webmagic.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class HappyjuziPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process(ResultItems resultItems, Task task) {

        Map<String, Object> data =  resultItems.getAll();

        for(Map.Entry<String, Object> ele : data.entrySet()){
            System.out.println(ele.getKey() + " -> "+ ele.getValue());
        }
    }
}
