package com.ninlgde.patterns.strategy;

public class Context {
    private Strategy strategy;
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void performAction() {
        strategy.action();
    }
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new StrategyImpl1());
        context.performAction();
        // 运行过程变更算法
        context.setStrategy(new StrategyImpl2());
        context.performAction();
    }
}
