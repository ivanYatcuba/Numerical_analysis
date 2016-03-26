package app.calculations.equation.nonlinear.impl;

import app.calculations.equation.nonlinear.NonlinearEquationsCalculator;
import app.util.data.Function;

public abstract class AbstractEquationCalculator implements NonlinearEquationsCalculator {

    private final Function function;

    private final double intervalMin;
    private final double intervalMax;

    private final double eps;

    public AbstractEquationCalculator(double eps, double intervalMax, double intervalMin, Function function) {
        this.eps = eps;
        this.intervalMax = intervalMax;
        this.intervalMin = intervalMin;
        this.function = function;
    }

    public abstract String getName();

    @Override
    public Function getFunction() {
        return function;
    }

    @Override
    public double getIntervalMin() {
        return intervalMin;
    }

    @Override
    public double getIntervalMax() {
        return intervalMax;
    }

    @Override
    public double getEps() {
        return eps;
    }
}
