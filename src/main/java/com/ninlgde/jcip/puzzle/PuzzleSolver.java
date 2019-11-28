package com.ninlgde.jcip.puzzle;

import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M>
{
	private final AtomicInteger taskCount = new AtomicInteger(0);

	public PuzzleSolver(Puzzle<P, M> puzzle)
	{
		super(puzzle);
	}

	protected Runnable newTask(P p, M m, PuzzleNode<P, M> n)
	{
		return new CountingSolverTask(p, m, n);
	}

	class CountingSolverTask extends SolverTask
	{

		CountingSolverTask(P pos, M move, PuzzleNode<P, M> prev)
		{
			super(pos, move, prev);
			taskCount.incrementAndGet();
		}

		public void run()
		{
			try
			{
				super.run();
			}
			finally
			{
				if (taskCount.decrementAndGet() == 0)
					solution.setValue(null);
			}
		}
	}
}
