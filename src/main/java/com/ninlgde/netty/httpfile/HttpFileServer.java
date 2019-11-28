package com.ninlgde.netty.httpfile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer
{
	private static final String DEFAULT_URL = "/src/main/java/com/ninlgde/netty/";

	public void run(final int port, final String url)
		throws Exception
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel socketChannel)
					throws Exception
				{
					socketChannel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
					socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
					socketChannel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
					socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
					socketChannel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
				}
			});
			ChannelFuture f = b.bind("127.0.0.1", port).sync();

			System.out.println("HTTP File Server started, website : " + "http://localhost:"+port+"/");

			f.channel().closeFuture().sync();
		}
		finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
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

		String url = DEFAULT_URL;
		if (args != null && args.length > 1)
			url = args[1];

		new HttpFileServer().run(port, url);
	}
}
