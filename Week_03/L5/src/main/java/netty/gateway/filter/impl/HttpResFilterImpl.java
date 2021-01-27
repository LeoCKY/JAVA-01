package netty.gateway.filter.impl;

import io.netty.handler.codec.http.FullHttpResponse;
import netty.gateway.filter.HttpResFilter;

public class HttpResFilterImpl implements HttpResFilter {

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("token", "gbrgbr84r295nmertger34");
    }
}
