package netty.gateway.rounter;

import java.util.List;
import java.util.Random;

/**
 * 隨機Router
 */
public class RandomHttpRouter implements HttpRouter {

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }

}
