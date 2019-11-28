package com.ninlgde.jcip;

import com.ninlgde.jcip.annotations.GuardedBy;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LogService
{
	private static final int CAPACITY = 1000;
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private final PrintWriter writer;
	@GuardedBy("this") private boolean isShutDown;
	@GuardedBy("this") private int reservations;

	public LogService(Writer writer)
		throws FileNotFoundException
	{
		this.queue = new ArrayBlockingQueue<>(CAPACITY);
		this.loggerThread = new LoggerThread();
		this.writer = new PrintWriter(writer);
	}

	public void start()
	{
		loggerThread.start();
	}

	public void stop()
	{
		synchronized (this)
		{
			isShutDown = true;
		}
		loggerThread.interrupt();
	}

	public void log(String msg)
		throws InterruptedException
	{
		synchronized (this)
		{
			if (isShutDown)
				throw new IllegalStateException();
			++reservations;
		}
		queue.put(msg);
	}

	private class LoggerThread extends Thread
	{
		public void run()
		{
			try
			{
				while (true)
				{
					try
					{
						synchronized (LogService.this)
						{
							if (isShutDown && reservations == 0)
								break;
						}
						String msg = queue.take();
						synchronized (LogService.this)
						{
							--reservations;
						}
						writer.println(msg);
					}
					catch (InterruptedException e)
					{ /* retry */}
				}
			}
			finally
			{
				writer.close();
			}
		}
	}
}
