package com.ninlgde.patterns.observer;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:03
 */
public class CurrentConditionsDisplay implements Observer {
    private float temp;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    /**
     * 注册到指定的Subject，进行监听
     *
     * @param weatherData
     */
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity,
                       float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Current conditions: "
                + temp + "F degrees and " + humidity
                + "% humidity and " + pressure
                + " pressure");
    }
}
