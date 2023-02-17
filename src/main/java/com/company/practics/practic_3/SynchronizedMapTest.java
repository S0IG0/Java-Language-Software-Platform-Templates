package com.company.practics.practic_3;

import java.util.Map;

public class SynchronizedMapTest {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new SynchronizedMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Thread thread1 = new Thread(() -> {
            map.put(4, "four");
            map.remove(1);
        });

        Thread thread2 = new Thread(() -> {
            map.put(5, "five");
            map.remove(2);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(map); // Expected output: {3=three, 4=four, 5=five}
    }
}
