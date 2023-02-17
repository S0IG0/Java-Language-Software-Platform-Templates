package com.company.practics.practic_5.ProgramLogger;

import com.company.practics.practic_5.InterfaceLogger.Logger;

// Реализация с использованием ленивой инициализации
public class ProgramLoggerLazyInit implements Logger {
    private static ProgramLoggerLazyInit programLogger;
    private String logg = "";
    private ProgramLoggerLazyInit() {}

    public static synchronized ProgramLoggerLazyInit getProgramLogger(){
        if (programLogger == null){
            programLogger = new ProgramLoggerLazyInit();
        }
        return programLogger;
    }

    @Override
    public synchronized void addLogg(String logg){
        this.logg += logg + "\n";
    }

    @Override
    public synchronized void showLogg(){
        System.out.println(logg);
    }
}
