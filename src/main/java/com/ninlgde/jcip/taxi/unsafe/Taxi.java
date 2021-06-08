package com.ninlgde.jcip.taxi.unsafe;

import com.ninlgde.jcip.annotations.GuardedBy;
import com.ninlgde.jcip.annotations.NotThreadSafe;
import com.ninlgde.jcip.vehicle.Point;

/**
 * @author: ninlgde
 * @date: 1/14/21 4:53 PM
 */
@NotThreadSafe
public class Taxi {
    @GuardedBy("this")
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination))
            dispatcher.notifyAvailable(this);
    }
}
