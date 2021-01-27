package netty.gateway.rounter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 順序 Router
 */
public class OrderHttpRouter implements HttpRouter {

    public AtomicInteger num = null;

    public OrderHttpRouter() {
        num = new AtomicInteger(0);
    }

    @Override
    public String route(List<String> urls) {
        int len = urls.size();
        int inx = num.addAndGet(1);

        if (len <= inx) {
            num.set(0);
            return urls.get(0);
        }
        return urls.get(inx);
    }

}
