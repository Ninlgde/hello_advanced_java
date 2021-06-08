package com.ninlgde.patterns.abstractfactory;

import com.ninlgde.patterns.factory.Factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:43
 */
public interface AbstractFactory extends Factory {
    /**
     * 抽象方法，子类实现
     * @return
     */
    Animal getAnimal();
}
