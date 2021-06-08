package com.ninlgde.jcip.memoizer;

import com.ninlgde.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

import static com.ninlgde.jcip.LaunderThrowable.launderThrowable;

/**
 * @author: ninlgde
 * @date: 11/24/20 4:17 PM
 */
@ThreadSafe
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
