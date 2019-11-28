package com.ninlgde.concurrency;

public class ThreadLocalExample
{

	public static class MyRunnable implements Runnable
	{

		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

		@Override
		public void run()
		{
			threadLocal.set((int)(Math.random() * 100D));

			try
			{
				Thread.sleep(threadLocal.get()*50);
			}
			catch (InterruptedException e)
			{
			}

			System.out.println(Thread.currentThread().getName() + ' ' + threadLocal.get());
		}
	}

	public static void main(String[] args)
		throws InterruptedException
	{
		MyRunnable sharedRunnableInstance = new MyRunnable();

		Thread thread1 = new Thread(sharedRunnableInstance, "1");
		Thread thread2 = new Thread(sharedRunnableInstance, "2");

		thread1.start();
		thread2.start();

		thread1.join(); //wait for thread 1 to terminate
		thread2.join(); //wait for thread 2 to terminate
	}
}