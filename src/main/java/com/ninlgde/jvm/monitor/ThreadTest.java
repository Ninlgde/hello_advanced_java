package com.ninlgde.jvm.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadTest
{
	static volatile boolean l = true;
	public static void creatBusyThread()
	{
		Thread thread = new Thread(() -> {
			while (l)
				;
		}, "testBusyThread");
		thread.start();
	}

	public static void createLockThread(final Object lock)
	{
		Thread thread = new Thread(() -> {
			synchronized (lock)
			{
				try
				{
					lock.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}, "testLockThread");
		thread.start();
	}

	public static void main(String[] args)
		throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		creatBusyThread();
		br.readLine();
		Object obj = new Object();
		createLockThread(obj);
		br.readLine();
		l = false;
	}
}
