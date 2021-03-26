package wechatOrder.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author JJ
 * @date 2019/12/10 - 21:20
 */
public class DriverMangerListner {
    private final static Logger logger = Logger.getLogger(DriverMangerListner.class);
    public void contextInitialized(ServletContextEvent sce) {

    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[DriverMangerListner]:-------DriverManager deregisterDriver start...");
        //com.mysql.jdbc.AbandonedConnectionCleanupThread.uncheckedShutdown();
        Enumeration<Driver> enumeration = DriverManager.getDrivers();
        while (enumeration.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(enumeration.nextElement());
                Thread.sleep(2000);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
        logger.debug("[DriverMangerListner]:-------DriverManager deregisterDriver end...");
    }
}
