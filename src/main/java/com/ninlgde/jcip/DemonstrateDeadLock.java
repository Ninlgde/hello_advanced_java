package com.ninlgde.jcip;

import java.util.Random;

import com.ninlgde.jcip.DynamicOrderDeadlock.Account;

import static com.ninlgde.jcip.DynamicOrderDeadlock.transferMoney;

public class DemonstrateDeadLock
{
	private static final int NUM_THREADS = 20;
	private static final int NUM_ACCOUNTS = 5;
	private static final int NUM_ITERATIONS = 1000000;

	public static void main(String[] args)
	{
		final Random rnd = new Random();
		final Account[] accounts = new Account[NUM_ITERATIONS];

		for (int i = 0; i < accounts.length; i++)
			accounts[i] = new Account();

		class TransferThread extends Thread
		{
			public void run()
			{
				for (int i = 0; i < NUM_ITERATIONS; i++) {
					int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
					int toAcct = rnd.nextInt(NUM_ACCOUNTS);
					DynamicOrderDeadlock.DollarAmount amount = new DynamicOrderDeadlock.DollarAmount(rnd.nextInt(1000));
					try
					{
						transferMoney(accounts[fromAcct], accounts[toAcct], amount);
					}
					catch (DynamicOrderDeadlock.InsufficientFundsException e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		for (int i = 0; i < NUM_THREADS; i++)
			new TransferThread().start();
	}
}
