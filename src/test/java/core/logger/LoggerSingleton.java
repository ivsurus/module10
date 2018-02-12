package core.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {

	private static Logger logger = LogManager.getLogger(LoggerSingleton.class);

	private LoggerSingleton() {
	}

	public static Logger getLogger() {
		return logger;
	}

}
