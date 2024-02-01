package utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    public static Logger log = LoggerFactory.getLogger(Log.class);

    public static void logMessage(String message){
        log.info(message);
    }
}
