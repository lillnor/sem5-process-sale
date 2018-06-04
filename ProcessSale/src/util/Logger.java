/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * This is an interface for the different types of loggers that exist.
 * @author Robert Lillnor
 */
public interface Logger {
    
    /**
     * Logs an exception to a file or console.
     * @param exception The exception to be logged. 
    */
    void log(Exception exception);
}
