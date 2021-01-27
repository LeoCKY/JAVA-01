package netty.gateway.filter.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import netty.gateway.filter.HttpReqFilter;

import java.util.ArrayList;
import java.util.List;

public class HttpReqFilterImpl implements HttpReqFilter {

    private List<String> ignoreUris = new ArrayList<>();

    public HttpReqFilterImpl() {
        ignoreUris.add("/");
        ignoreUris.add("/login");
        ignoreUris.add("/js");
        ignoreUris.add("/css");
        ignoreUris.add("/images");
     }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        if(!ignoreUris.contains(uri)){
            String token = fullRequest.headers().get("token");
            if(token == null || "".equals(token)){
                System.err.println("Token is empty");
             }
        }
    }
}
