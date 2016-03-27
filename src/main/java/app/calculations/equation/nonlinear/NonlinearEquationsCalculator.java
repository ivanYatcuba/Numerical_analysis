package app.calculations.equation.nonlinear;

import app.util.data.nonlinear.Function;

public interface NonlinearEquationsCalculator {

    Result calculate();

    Function getFunction();

    double getIntervalMin();

    double getIntervalMax();

    double getEps();
}
