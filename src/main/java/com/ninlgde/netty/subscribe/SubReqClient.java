package com.ninlgde.netty.subscribe;

import com.ninlgde.netty.protobuf.SubscribeRespProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class SubReqClient
{
	public void connect(String host, int port)
		throws Exception
	{
		EventLoopGroup group = new NioEventLoopGroup();
		try
		{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel socketChannel)
					throws Exception
				{
					socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
					socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
					socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					socketChannel.pipeline().addLast(new ProtobufEncoder());
					socketChannel.pipeline().addLast(new SubReqClientHandler());
				}
			});
			ChannelFuture f = b.connect(host, port).sync();

			f.channel().closeFuture().sync();
		}
		finally
		{
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args)
		throws Exception
	{
		int port = 8080;
		if (args != null && args.length > 0)
		{
			try
			{
				port = Integer.valueOf(args[0]);
			}
			catch (NumberFormatException e)
			{
				// do nothing
			}
		}

		new SubReqClient().connect("127.0.0.1", port);
	}
}
