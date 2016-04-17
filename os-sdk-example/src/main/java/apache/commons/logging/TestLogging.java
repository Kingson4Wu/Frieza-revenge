package apache.commons.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestLogging {
    private static Log log = LogFactory.getLog(TestLogging.class);
    //日志打印
    public static void main(String[] args) {
        log.error("ERROR");
        log.debug("DEBUG");
        log.warn("WARN");
        log.info("INFO");
        log.trace("TRACE");
        System.out.println(log.getClass());
    }
}
