package com.ninlgde.effectivejava.lambda_stream;

import java.util.function.DoubleBinaryOperator;

/**
 * @author ninlgde
 * @date 2022/10/9 19:13
 */
public enum BaseOperation implements Operation {
    PLUS("+", Double::sum),
    MINUS("-", (x, y) -> x - y),
    MULTIPLY("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y),
    ;

    private final String symbol;
    private final DoubleBinaryOperator op;

    BaseOperation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}
