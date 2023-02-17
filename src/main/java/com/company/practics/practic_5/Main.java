package com.company.practics.practic_5;

import com.company.practics.practic_5.InterfaceLogger.Logger;
import com.company.practics.practic_5.ProgramLogger.ProgramLoggerEnum;
import com.company.practics.practic_5.ProgramLogger.ProgramLoggerLazyInit;
import com.company.practics.practic_5.ProgramLogger.ProgramLoggerStatic;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Logger[] loggers = new Logger[]{
                ProgramLoggerLazyInit.getProgramLogger(),
                ProgramLoggerStatic.getProgramLogger(),
                ProgramLoggerEnum.INSTANCE,
        };

        for (Logger logger : loggers){
            test(logger);
        }
    }

    public static void test(Logger logger) throws InterruptedException {
        Thread[] threads = new Thread[]{
                new Thread(() -> logger.addLogg("Thread: 1")),
                new Thread(() -> logger.addLogg("Thread: 2")),
                new Thread(() -> logger.addLogg("Thread: 3")),
                new Thread(() -> logger.addLogg("Thread: 4")),
                new Thread(() -> logger.addLogg("Thread: 5")),
                new Thread(() -> logger.addLogg("Thread: 6")),
        };

        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }

        logger.showLogg();
    }
}
