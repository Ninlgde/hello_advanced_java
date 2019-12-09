package com.ninlgde.jcip;

import java.util.*;
import java.util.concurrent.*;

public abstract class Travel {
    private final ExecutorService exec;

    public Travel(ExecutorService exec) {
        this.exec = exec;
    }

    private class QuoteTask implements Callable<TravelQuote> {
        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        @Override
        public TravelQuote call() {
            return company.solicitQuote(travelInfo);
        }

        public TravelQuote getFailureQuote(Throwable throwable) {
            return null;
        }

        public TravelQuote getTimeoutQuote(CancellationException e) {
            return null;
        }
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies, Comparator<TravelQuote> ranking, long time, TimeUnit unit)
            throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies)
            tasks.add(new QuoteTask(company, travelInfo));

        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        Collections.sort(quotes, ranking);
        return quotes;
    }

    abstract class TravelCompany {
        abstract TravelQuote solicitQuote(TravelInfo travelInfo);
    }

    abstract class TravelQuote {
    }

    abstract class TravelInfo {
    }
}
