package main.threadsafecollections.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {

    private Long duration;
    private String message;

    public DelayedWorker(Long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public int compareTo(Delayed other) {
        if (duration < ((DelayedWorker) other).getDuration())
            return -1;

        if (duration > ((DelayedWorker) other).getDuration())
            return 1;

        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "duration=" + duration +
                ", message='" + message + '\'' +
                '}';
    }
}

public class DelayQueueExample {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            //These can be inserted by different threads also. Result is same
            queue.put(new DelayedWorker(1000L, "This is the first message"));
            queue.put(new DelayedWorker(10000L, "This is the second message"));
            queue.put(new DelayedWorker(5000L, "This is the third message"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
