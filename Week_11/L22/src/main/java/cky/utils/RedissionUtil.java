package cky.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@
@Component
public class RedissionUtil {

    @Autowired
    private RedissonClient redissonClient;


    public void lock(String key, Integer second) {
        RLock rLock = redissonClient.getLock(key);
        Future<Boolean> coffeeLockFuture = rLock.tryLockAsync(20, 5, TimeUnit.SECONDS);

        Future<Boolean> lockFuture = rLock.tryLockAsync(20, 5, TimeUnit.SECONDS);
        boolean result = lockFuture.get();
        if (result) {
            log.info("我获得了锁");
            Coffee coffee = coffeeService.getById(1);
            if (coffee.getStock() > 0) {
                coffee.setStock(coffee.getStock() - 1);
                coffeeService.updateById(coffee);
            }
        }

        rLock.lock();
    }

    public void unLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        rLock.unlock();
    }

}
