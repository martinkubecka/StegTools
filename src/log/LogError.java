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

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        try {

            FileHandler fh = new FileHandler("errors.xml");
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, "File logger not working.", ioException);
        }
    }
}
