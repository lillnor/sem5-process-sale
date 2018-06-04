/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A logger that logs exceptions to files.
 * @author Robert Lillnor
 */
public class FileLogger extends LogTemplate {
    private PrintWriter logStream;
    
    public FileLogger(){
        try {
            logStream = new PrintWriter(new FileWriter("sale-log.txt"), true);
        } catch (IOException ex) {
            System.out.println("Could not create logger.");
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void specificLog(Exception exception, StringBuilder logMsg) {
        logStream.println(logMsg);
        exception.printStackTrace(logStream);
    }
}
