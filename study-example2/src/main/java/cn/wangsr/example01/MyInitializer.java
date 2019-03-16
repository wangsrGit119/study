package cn.wangsr.example01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p=socketChannel.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new ChunkedWriteHandler());
        p.addLast(new HttpObjectAggregator(8888));//用于分段请求聚合  参数：最大长度
        p.addLast(new WebSocketServerProtocolHandler("/ws"));//webscoket
        p.addLast(new MyServerHandle()); //自定义处理器
    }
}
