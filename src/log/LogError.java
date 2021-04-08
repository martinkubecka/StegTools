package log;

import java.io.IOException;
import java.util.logging.*;

public class LogError {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor.
     */
    public LogError() {

        LogError.setUpLogger();
    }

    /**
     * Initialize logger and its FileHandler.
     */
    private static void setUpLogger() {

        // Add the following lines to each class where logging should be present
        // private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        // LOGGER.log(Level.SEVERE, ioException.toString(), ioException);

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

//        ConsoleHandler ch = new ConsoleHandler();
//        ch.setLevel(Level.SEVERE);
//        LOGGER.addHandler(ch);

        try {
//            FileHandler fh = new FileHandler("errors.log", true);
            FileHandler fh = new FileHandler("errors.log");
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);

//            SimpleFormatter simpleFormatter = new SimpleFormatter();
//            fh.setFormatter(simpleFormatter);

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, "File logger not working.", ioException);
        }
    }
}
