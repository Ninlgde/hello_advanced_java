package com.ninlgde.jcip.taxi.copyonwrite;

import com.ninlgde.jcip.annotations.ThreadSafe;
import com.ninlgde.jcip.taxi.Image;
import com.ninlgde.jcip.taxi.Taxi;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: ninlgde
 * @date: 1/14/21 5:07 PM
 */
@ThreadSafe
public class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new CopyOnWriteArraySet<>();
        availableTaxis = new CopyOnWriteArraySet<>();
    }

    public void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
}
