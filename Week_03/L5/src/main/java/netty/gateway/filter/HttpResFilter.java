package netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResFilter {

    void filter(FullHttpResponse response);

}
