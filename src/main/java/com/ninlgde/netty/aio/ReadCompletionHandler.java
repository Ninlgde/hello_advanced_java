package com.ninlgde.netty.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>
{
	private AsynchronousSocketChannel channel;

	public ReadCompletionHandler(AsynchronousSocketChannel channel)
	{
		if (this.channel == null)
			this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment)
	{
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		String req = new String(body, StandardCharsets.UTF_8);
		System.out.println("The time server receive order: " + req);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
		doWrite(currentTime);
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment)
	{
		try
		{
			this.channel.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void doWrite(String currentTime)
	{
		if (currentTime != null && currentTime.trim().length() > 0)
		{
			byte[] bytes = (currentTime).getBytes();
			final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>()
			{
				@Override
				public void completed(Integer result, ByteBuffer buffer)
				{
					if (buffer.hasRemaining())
						channel.write(buffer, buffer, this);
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment)
				{
					try
					{
						channel.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			});
		}
	}
}
