package netty.gateway.rounter;

import java.util.List;

public interface HttpRouter {

    String route(List<String> endpoints);

}
