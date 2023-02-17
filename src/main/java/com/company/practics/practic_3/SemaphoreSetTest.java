package com.company.practics.practic_3;

import java.util.Set;

public class SemaphoreSetTest {

    public static void main(String[] args) throws InterruptedException {
        Set<String> set = new SemaphoreSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Thread thread1 = new Thread(() -> {
            set.add("four");
            set.remove("one");
        });

        Thread thread2 = new Thread(() -> {
            set.add("five");
            set.remove("two");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(set); // Expected output: [three, four, five]
    }
}
