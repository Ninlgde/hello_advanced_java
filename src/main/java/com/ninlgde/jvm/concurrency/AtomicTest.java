package com.ninlgde.jvm.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest
{
	public static AtomicInteger race = new AtomicInteger(0);

	public static void increase()
	{
		race.incrementAndGet();
	}

	private static final int THREADS_COUNT = 20;

	public static void main(String[] args)
		throws Exception
	{
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++)
		{
			threads[i] = new Thread(()-> {
				for (int j = 0; j < 10000; j++)
					increase();
			});
			threads[i].start();
		}

//		while (Thread.activeCount() > 1)
//			Thread.yield();
		Thread.sleep(3000);

		System.out.println(race);
	}
}
