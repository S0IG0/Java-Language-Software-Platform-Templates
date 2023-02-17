package com.company.practics.practic_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 4; // количество потоков
        ExecutorService executorService = new MyExecutorService(numThreads);
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(() -> {
            // выполнение задачи 1
            return 1;
        });
        tasks.add(() -> {
            // выполнение задачи 2
            return 2;
        });
        tasks.add(() -> {
            // выполнение задачи 3
            return 3;
        });
        List<Future<Integer>> results = executorService.invokeAll(tasks);
        executorService.shutdown();
    }
}
