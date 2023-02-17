package com.company.practics.practic_4;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MyExecutorService implements ExecutorService {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<MyThread> threads;
    private final AtomicBoolean isShutdown;
    private final AtomicReference<Thread> shutdownThread;
    private final AtomicInteger activeThreads;

    public MyExecutorService(int numThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        threads = new ArrayList<>(numThreads);
        isShutdown = new AtomicBoolean(false);
        shutdownThread = new AtomicReference<>(null);
        activeThreads = new AtomicInteger(0);

        for (int i = 0; i < numThreads; i++) {
            MyThread thread = new MyThread();
            thread.start();
            threads.add(thread);
        }
    }

    private class MyThread extends Thread {
        public void run() {
            while (!isShutdown.get()) {
                try {
                    Runnable task = taskQueue.take();
                    activeThreads.incrementAndGet();
                    task.run();
                    activeThreads.decrementAndGet();
                } catch (InterruptedException e) {
                    // Interrupted, do nothing
                }
            }
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isShutdown.get()) {
            throw new RejectedExecutionException("Executor has been shut down");
        }

        taskQueue.add(command);
    }

    @Override
    public void shutdown() {
        isShutdown.set(true);
        shutdownThread.set(Thread.currentThread());
        for (MyThread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown();
        List<Runnable> remainingTasks = new ArrayList<>();
        taskQueue.drainTo(remainingTasks);
        return remainingTasks;
    }

    @Override
    public boolean isShutdown() {
        return isShutdown.get();
    }

    @Override
    public boolean isTerminated() {
        return isShutdown.get() && activeThreads.get() == 0;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        long lastTime = System.nanoTime();
        while (!isTerminated() && nanosTimeout > 0) {
            TimeUnit.NANOSECONDS.timedWait(taskQueue, nanosTimeout);
            nanosTimeout = nanosTimeout - (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
        }
        return isTerminated();
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> futureTask = new FutureTask<>(task);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        FutureTask<T> futureTask = new FutureTask<>(task, result);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public Future<?> submit(Runnable task) {
        FutureTask<Void> futureTask = new FutureTask<>(task, null);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            execute(futureTask);
            futures.add(futureTask);
        }
        for (Future<T> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                // Do nothing
            }
        }
        return futures;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        long lastTime = System.nanoTime();
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(new FutureTask<>(task));
        }
         for (Future<T> tFuture : futures) {
            execute((Runnable) tFuture);
            nanosTimeout = nanosTimeout - (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
            if (nanosTimeout <= 0) {
                break;
            }
        }
        for (Future<T> future : futures) {
            if (!future.isDone()) {
                try {
                    future.get(nanosTimeout, TimeUnit.NANOSECONDS);
                } catch (TimeoutException e) {
                    // Do nothing
                    System.out.println("TimeoutException: " + e.toString());
                } catch (ExecutionException e) {
                    // Do nothing
                    System.out.println("ExecutionException: " + e.toString());
                }
            }
            nanosTimeout = nanosTimeout - (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
            if (nanosTimeout <= 0) {
                break;
            }
        }
        return futures;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            execute(futureTask);
            futures.add(futureTask);
        }
        while (true) {
            for (Future<T> future : futures) {
                if (future.isDone()) {
                    try {
                        return future.get();
                    } catch (ExecutionException e) {
                        // Do nothing
                    }
                }
            }
        }
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long nanosTimeout = unit.toNanos(timeout);
        long lastTime = System.nanoTime();
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(new FutureTask<>(task));
        }
        for (Future<T> tFuture : futures) {
            execute((Runnable) tFuture);
            nanosTimeout = nanosTimeout - (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
            if (nanosTimeout <= 0) {
                break;
            }
        }
        while (true) {
            for (Future<T> future : futures) {
                if (future.isDone()) {
                    try {
                        return future.get();
                    } catch (ExecutionException e) {
                        // Do nothing
                    }
                }
            }
            nanosTimeout = nanosTimeout - (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
            if (nanosTimeout <= 0) {
                throw new TimeoutException();
            }
        }
    }
}
