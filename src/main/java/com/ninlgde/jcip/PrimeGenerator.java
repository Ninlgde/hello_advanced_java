package com.ninlgde.jcip;

import com.ninlgde.jcip.annotations.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class PrimeGenerator implements Runnable
{
	@GuardedBy("this")
	private final List<BigInteger> primes = new ArrayList<>();

	private volatile boolean cancelled;

	@Override
	public void run()
	{
		BigInteger p = BigInteger.ONE;
		while (!cancelled)
		{
			p = p.nextProbablePrime();
			synchronized (this){
				primes.add(p);
			}
		}
	}

	public void cancel()
	{
		cancelled = true;
	}

	public synchronized List<BigInteger> get()
	{
		return new ArrayList<>(primes);
	}
}
