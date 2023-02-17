package com.company.practics.practic_5.ProgramLogger;

import com.company.practics.practic_5.InterfaceLogger.Logger;

public class ProgramLoggerStatic implements Logger {
    private static final ProgramLoggerStatic ProgramLoggerStatic;
    private String logg = "";

    static {
        ProgramLoggerStatic = new ProgramLoggerStatic();
    }

    private ProgramLoggerStatic() {}

    public static synchronized ProgramLoggerStatic getProgramLogger(){
        return ProgramLoggerStatic;
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
