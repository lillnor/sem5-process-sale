/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final LogHandler INSTANCE = new LogHandler();
    private Logger logger;
    
    /**
     * Gets a reference to the logHandler, because it is a singleton.
     * @return This logHandler.
     */
    public static LogHandler getLogger() {
        return INSTANCE;
    }
    
    private LogHandler() {
    }
    
    /**
     * Sets the logger type to file, which makes this log messages to a file.
     */
    public void setFileLogger() {
        logger = new FileLogger();
    }
    
    /**
     * Sets the logger type to console, which makes this log messages to the console.
     */
    public void setConsoleLogger() {
        logger = new ConsoleLogger();
    }
    
    /**
     * Writes a log entry describing a thrown exception to a file or the console depending on which type is set.
     * @param exception the exception to be logged.
     */
    public void logException(Exception exception) {
        logger.log(exception);
    }
}
