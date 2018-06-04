/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This abstract class contains the building of messages for the different logger types.
 * specificLog is the only different part of the two loggers so it is appropriate to create a method template pattern.
 * @author Robert Lillnor
 */
public abstract class LogTemplate implements Logger {
    
    @Override
    public void log(Exception exception){
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        specificLog(exception, logMsgBuilder);
    }
    
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    
    protected abstract void specificLog(Exception exception, StringBuilder logMsg);
}
