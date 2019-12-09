package com.ninlgde.jvm.concurrency;

public class Singleton
{
	private volatile static Singleton instance;

	public static Singleton getInstance()
	{
		if (instance == null)
			synchronized (Singleton.class)
			{
				if (instance == null)
					instance = new Singleton();
			}

		return instance;
	}
}
