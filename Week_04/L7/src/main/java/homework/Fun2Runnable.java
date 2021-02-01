package homework;

public class Fun2Runnable implements Runnable {

    private int value;

    @Override
    public void run() {
        value = Q1.sum();
    }

    public int getValue() {
        return value;
    }
}
