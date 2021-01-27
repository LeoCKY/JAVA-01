package netty.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpReqFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
