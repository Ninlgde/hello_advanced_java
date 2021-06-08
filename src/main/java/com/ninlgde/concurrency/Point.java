package com.ninlgde.concurrency;

import com.ninlgde.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.StampedLock;

/**
 * @author: ninlgde
 * @date: 2020/4/29 15:10
 */
@ThreadSafe
public class Point {
    private double x, y;

    private StampedLock lock = new StampedLock();

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double deltaX, double deltaY) {
        long stamp = lock.tryWriteLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /**
     * 1. 先获取乐观读锁 -> 读取
     * 2. 验证是否被修改 -> 没有
     *              -> 有:获取读悲观锁 -> 读取 -> 释放悲观锁
     * 3. 返回计算结果
     * @return
     */
    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 使用悲观锁获取读锁，并尝试转换为写锁
    public void moveIfAtOrigin(double newX, double newY) {
        // 这里可以使用乐观读锁替换（1）
        long stamp = lock.readLock();
        try {
            // 如果当前点在原点则移动（2）
            while (x == 0.0 && y == 0.0) {
                // 尝试将获取的读锁升级为写锁（3）
                long ws = lock.tryConvertToWriteLock(stamp);
                // 升级成功，则更新票据，并设置坐标值，然后退出循环（4）
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试（5）
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
            }
        } finally {
            // 释放锁（6）
            lock.unlock(stamp);
        }
    }
}
