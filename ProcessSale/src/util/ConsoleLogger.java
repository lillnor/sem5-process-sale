/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * A logger that logs exceptions to the console.
 * @author Robert Lillnor
 */
public class ConsoleLogger extends LogTemplate {
    
    @Override
    protected void specificLog(Exception exception, StringBuilder logMsg) {
        System.out.println(logMsg);
        exception.printStackTrace(System.out);
    }
}
