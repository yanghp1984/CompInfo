package test.tool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
    private static Logger logger = LogManager.getLogger(Log4jTest.class);

    @Test
    public void testLog() {
        logger.debug("1.Debug");
        logger.info("2.Infomation");
        logger.warn("3.Warning");
        logger.error("4.Error");
    }

    public static void main(String[] args) {
        logger.info("Application start.");
    }
}
