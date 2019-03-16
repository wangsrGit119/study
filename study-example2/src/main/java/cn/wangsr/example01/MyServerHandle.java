package cn.wangsr.example01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class MyServerHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("ctx："+ctx);
        System.out.println("收到消息："+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("当前时间："+ LocalDateTime.now()));
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelActive："+ctx.channel().id().asLongText());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerRemoved："+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常：");
        ctx.close();
    }
}
