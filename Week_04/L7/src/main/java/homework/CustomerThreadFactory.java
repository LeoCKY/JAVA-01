package homework;

import java.util.concurrent.ThreadFactory;

public class CustomerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        return thread;
    }
}
