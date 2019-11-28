package com.ninlgde.jcip;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestMain
{
	public static void main(String[] args)
		throws InterruptedException
	{
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try
		{
			SECONDS.sleep(1);
		} finally
		{
			generator.cancel();
		}
		System.out.println(generator.get());
	}
}
