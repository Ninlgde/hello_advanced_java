package com.ninlgde.jcip;

import com.ninlgde.jcip.annotations.Immutable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DynamicOrderDeadlock
 * <p/>
 * Dynamic lock-ordering deadlock
 *
 * @author Brian Goetz and Tim Peierls
 */
public class DynamicOrderDeadlock {
    // Warning: deadlock-prone!
    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     DollarAmount amount)
            throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

//    public boolean transferMoney(Account fromAccount,
//                                 Account toAccount,
//                                 DollarAmount amount,
//                                 long timeout,
//                                 TimeUnit unit)
//            throws InsufficientFundsException, InterruptedException {
//        long fixedDelay = getF
//    }

    @Immutable
    static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation
        private final int amount;

        public DollarAmount(int amount) {
            this.amount = amount;
        }

        public DollarAmount add(DollarAmount d) {
            return new DollarAmount(amount + d.amount);
        }

        public DollarAmount subtract(DollarAmount d) {
            return new DollarAmount(amount - d.amount);
        }

        public int compareTo(DollarAmount dollarAmount) {
            return amount - dollarAmount.amount;
        }
    }

    static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
    }
}
