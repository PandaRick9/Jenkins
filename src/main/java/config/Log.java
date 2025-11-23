package config;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public static final Logger logger = Logger.getLogger("DeliveryLogger");

    static {
        try {
            logger.setUseParentHandlers(false);

            ConsoleHandler console = new ConsoleHandler();
            console.setLevel(Level.ALL);

            FileHandler file = new FileHandler("delivery.log", true);
            file.setFormatter(new SimpleFormatter());
            file.setLevel(Level.ALL);

            logger.addHandler(console);
            logger.addHandler(file);
            logger.setLevel(Level.ALL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
