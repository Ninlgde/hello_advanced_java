package com.ninlgde.advanced.aqs;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ninlgde
 * @date 2022/4/26 13:34
 */
public class LockConditionDemo {

    public static void main(String[] args) {
        Message msg = new Message();
        Thread producer = new Thread(new MessageProducer(msg));
        Thread consumer = new Thread(new MessageConsumer(msg));
        producer.start();
        consumer.start();
    }

    static class MessageProducer implements Runnable {

        private final Message message;

        public MessageProducer(Message msg) {
            message = msg;
        }

        @Override
        public void run() {
            produce();
        }

        public void produce() {
            List<String> msgs = new ArrayList<>();
            msgs.add("Begin");
            msgs.add("Msg1");
            msgs.add("Msg2");

            for (String msg : msgs) {
                message.produce(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            message.produce("End");
            message.setEnd(true);
        }

    }

    static class MessageConsumer implements Runnable {

        private final Message message;

        public MessageConsumer(Message msg) {
            message = msg;
        }

        @Override
        public void run() {
            while (!message.isEnd()) {
                message.consume();
            }
        }

    }

    static class Message {

        private final Lock lock = new ReentrantLock();

        private final Condition producedMsg = lock.newCondition();

        private final Condition consumedMsg = lock.newCondition();

        private String message;

        private boolean state;

        private boolean end;

        public void consume() {
            //lock
            lock.lock();
            try {
                // no new message wait for new message
                while (!state) {
                    producedMsg.await();
                }

                System.out.println("consume message : " + message);
                state = false;
                // message consumed, notify waiting thread
                consumedMsg.signal();
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted - viewMessage");
            } finally {
                lock.unlock();
            }
        }

        public void produce(String message) {
            lock.lock();
            try {
                // last message not consumed, wait for it be consumed
                while (state) {
                    consumedMsg.await();
                }

                System.out.println("produce msg: " + message);
                this.message = message;
                state = true;
                // new message added, notify waiting thread
                producedMsg.signal();
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted - publishMessage");
            } finally {
                lock.unlock();
            }
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

    }
}
