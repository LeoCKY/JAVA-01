package homework;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter {

    private int sum = 0;
    private Semaphore readSemaphore = new Semaphore(100, true);
    private Semaphore writeSemaphore = new Semaphore(1);


    public int incrAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeSemaphore.release();
        }
        return ++sum;
    }

    public int getSum() {
        try {
            readSemaphore.acquireUninterruptibly();
            Thread.sleep(100);
        } catch (Exception ex){

        } finally {
            readSemaphore.release();
            return sum;
        }
    }

    public static void main(String[] args) {
        SemaphoreCounter s = new SemaphoreCounter();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Write : " + s.incrAndGet());
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Read1 : " + s.getSum());
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Read2 : " + s.getSum());
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Read3 : " + s.getSum());
                }
            }).start();
        }
    }
}
