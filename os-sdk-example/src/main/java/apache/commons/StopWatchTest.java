package apache.commons;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by kxw on 2016/1/7.
 */
public class StopWatchTest {

    public static void main(String[] args) {
        int total = 0;
        StopWatch watch = new StopWatch();
        watch.start();
        try {
           // total = jdbcTemplate.update(sql.toString(), objs);
        } catch (Exception e) {
            watch.stop();
            //log.error("  执行开始时间 = "+watch.getStartTime()+" 执行时间 = "+watch.getTime());
        }
        watch.stop();
        //log.info("  执行开始时间 = "+watch.getStartTime()+" 执行时间 = "+watch.getTime());
    }
}
