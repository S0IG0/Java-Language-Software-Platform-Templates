package com.company.practics.practic_5.ProgramLogger;

import com.company.practics.practic_5.InterfaceLogger.Logger;

public enum ProgramLoggerEnum implements Logger {
    INSTANCE;

    private String logg = "";

    @Override
    public synchronized void addLogg(String logg){
        this.logg += logg + "\n";
    }

    @Override
    public synchronized void showLogg(){
        System.out.println(logg);
    }
}
