package com.ninlgde.jcip.taxi.unsafe;

import com.ninlgde.jcip.annotations.GuardedBy;
import com.ninlgde.jcip.annotations.NotThreadSafe;
import com.ninlgde.jcip.taxi.Image;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ninlgde
 * @date: 1/14/21 4:54 PM
 */
@NotThreadSafe
public class Dispatcher {
    @GuardedBy("this")
    private final Set<Taxi> taxis;
    @GuardedBy("this")
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis) {
            image.drawMarker(t.getLocation());
        }

        return image;
    }
}
