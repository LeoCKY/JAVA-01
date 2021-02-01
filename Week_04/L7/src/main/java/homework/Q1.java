package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Q1 {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        int result = -1;

//          result = fun1(); //这是得到的返回值
//          result = fun2(); //这是得到的返回值
//          result = fun3(); //这是得到的返回值
//        result = fun4(); //这是得到的返回值
        Q1 q = new Q1();
        result = q.fun10(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    public static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    /**
     * newSingleThreadExecutor 創建單線程的線程池
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int fun1() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                return sum();
            }
        };
        Future<Integer> future = executor.submit(callable);
        executor.shutdown();
        return future.get();
    }

    /**
     * 主要增加 Join 讓主線程等待子線程執行完畢
     *
     * @return
     * @throws InterruptedException
     */
    public static int fun2() throws InterruptedException {
        Fun2Runnable r = new Fun2Runnable();
        Thread thread = new Thread(r);
        thread.start();
        thread.join();
        int value = r.getValue();
        return value;
    }

    /**
     * newFixedThreadPool 固定大小線程池
     *
     * @return
     * @throws Exception
     */
    public static int fun3() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        // 创建CompletionService
        CompletionService<Integer> serv =
                new ExecutorCompletionService<Integer>(exec);
        for (int index = 0; index < 1; index++) {
            // Callable 接口类似于 Runnable
            Callable<Integer> downImg = new Callable<Integer>() {
                public Integer call() throws Exception {
                    return sum();
                }
            };
            // 提交要执行的值返回任务，并返回表示挂起的任务结果的 Future。在完成时，可能会提取或轮询此任务。
            serv.submit(downImg);
        }
        Integer img = null;

        for (int index = 0; index < 1; index++) {
            // 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
            Future<Integer> task = serv.take();
            // 如有必要，等待计算完成，然后获取其结果。
            img = task.get();
            System.out.println(img);
        }
        System.out.println("End");
        // 关闭线程池
        exec.shutdown();
        return img;
    }

    /**
     * newCachedThreadPool 緩存連線池
     *
     * @return
     * @throws Exception
     */
    public static int fun4() throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // 创建CompletionService
        CompletionService<Integer> serv =
                new ExecutorCompletionService<Integer>(exec);
        for (int index = 0; index < 1; index++) {
            // Callable 接口类似于 Runnable
            Callable<Integer> downImg = new Callable<Integer>() {
                public Integer call() throws Exception {
                    return sum();
                }
            };
            // 提交要执行的值返回任务，并返回表示挂起的任务结果的 Future。在完成时，可能会提取或轮询此任务。
            serv.submit(downImg);
        }
        Integer img = null;

        for (int index = 0; index < 1; index++) {
            // 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
            Future<Integer> task = serv.take();
            // 如有必要，等待计算完成，然后获取其结果。
            img = task.get();
            System.out.println(img);
        }
        System.out.println("End");
        // 关闭线程池
        exec.shutdown();
        return img;
    }

    /**
     * newFixedThreadPool 固定大小線程池
     *
     * @return
     * @throws Exception
     */
    public static int fun5() throws Exception {
        ExecutorService exec = Executors.newScheduledThreadPool(1);
        // 创建CompletionService
        CompletionService<Integer> serv =
                new ExecutorCompletionService<Integer>(exec);
        for (int index = 0; index < 1; index++) {
            // Callable 接口类似于 Runnable
            Callable<Integer> downImg = new Callable<Integer>() {
                public Integer call() throws Exception {
                    return sum();
                }
            };
            // 提交要执行的值返回任务，并返回表示挂起的任务结果的 Future。在完成时，可能会提取或轮询此任务。
            serv.submit(downImg);
        }
        Integer img = null;

        for (int index = 0; index < 1; index++) {
            // 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
            Future<Integer> task = serv.take();
            // 如有必要，等待计算完成，然后获取其结果。
            img = task.get();
            System.out.println(img);
        }
        System.out.println("End");
        // 关闭线程池
        exec.shutdown();
        return img;
    }

    /**
     * 使用 CountDownLatch
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static int fun6() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        int[] value = new int[1];
        Thread uiThread = new Thread() {
            @Override
            public void run() {
                value[0] = sum();
                latch.countDown(); // Release await() in the test thread.
            }
        };
        uiThread.start();
        latch.await();
        return value[0];
    }


    /**
     * 使用線程工廠建立 線程
     *
     * @return
     * @throws InterruptedException
     */
    public static int fun7() throws InterruptedException {
        CustomerThreadFactory factory = new CustomerThreadFactory();
        int[] value = new int[1];

        Thread t = factory.newThread(new Runnable() {
            @Override
            public void run() {
                value[0] = sum();
            }
        });
        t.start();
        t.join();
        return value[0];
    }


    /**
     * CyclicBarrier
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int fun8() throws ExecutionException, InterruptedException {
        int num = 3;
        CyclicBarrier barrier = new CyclicBarrier(num);
        List<CompletableFuture> list = new ArrayList<>();
        CyclicBarrieTask task = new CyclicBarrieTask(barrier);
        for (int i = 0; i < num; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(task);
            list.add(future);
        }

        for (CompletableFuture future : list) {
            System.out.println("CompletableFuture 迴圈");
            future.get();
        }

//        barrier.reset();
        return task.getResult();
    }


    /**
     * 運用 Thread Sleep() ，釋放CPU 切換時間片。
     *
     * @return
     * @throws InterruptedException
     */
    public static int fun9() throws InterruptedException {
        final int[] num = new int[1];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                num[0] = sum();
            }
        });
        t.start();
        Thread.sleep(100);
        return num[0];
    }

    /**
     * 運用 Thread Sleep() ，釋放CPU 切換時間片。
     *
     * @return
     * @throws InterruptedException
     */
    public int fun10() throws InterruptedException {
        final int[] num = new int[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this){
                        num[0] = sum();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
        });
        t.start();
        return num[0];
    }


}
