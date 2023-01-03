package main.threadsafecollections.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapFirstWorker implements Runnable {

    private final ConcurrentMap<String, Integer> concurrentMap;

    MapFirstWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            concurrentMap.put("T", 24);
            Thread.sleep(1000);
            concurrentMap.put("V", 25);
            concurrentMap.put("A", 30);
            Thread.sleep(2000);
            concurrentMap.put("R", 25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MapSecondWorker implements Runnable {

    private final ConcurrentMap<String, Integer> concurrentMap;

    MapSecondWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(concurrentMap.get("T"));
            Thread.sleep(2000);
            System.out.println(concurrentMap.get("V"));
            System.out.println(concurrentMap.get("A"));
            Thread.sleep(2000);
            System.out.println(concurrentMap.get("R"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        MapFirstWorker mapFirstWorker = new MapFirstWorker(map);
        MapSecondWorker mapSecondWorker = new MapSecondWorker(map);

        new Thread(mapFirstWorker).start();
        new Thread(mapSecondWorker).start();
    }
}
