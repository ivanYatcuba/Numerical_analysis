package app.calculations.equation.nonlinear.impl;

import app.calculations.equation.nonlinear.Result;
import app.util.data.Function;

public class NutonEquationCalculator extends AbstractEquationCalculator {

    public NutonEquationCalculator(double eps, double intervalMax, double intervalMin, Function function) {
        super(eps, intervalMax, intervalMin, function);
    }

    @Override
    public String getName() {
        return "Метод Ньютона";
    }

    @Override
    public Result calculate() {

        double x = getIntervalMax();

        int iterations = 0;
        double m;
        double M;
        double tmpX;
        double xK = x;

        if (Math.abs(getFunction().dF(getIntervalMin())) < Math.abs(getFunction().dF(getIntervalMax()))) {
            m = Math.abs(getFunction().dF(getIntervalMin()));
        } else {
            m = Math.abs(getFunction().dF(getIntervalMax()));
        }
        if (Math.abs(getFunction().ddF2(getIntervalMin())) < Math.abs(getFunction().ddF2(getIntervalMax()))) {
            M = Math.abs(getFunction().ddF2(getIntervalMax()));
        } else {
            M = Math.abs(getFunction().ddF2(getIntervalMin()));
        }

        tmpX = xK - getFunction().f(xK) / getFunction().dF(xK);
        while ((M / (2 * m)) * Math.pow(Math.abs(tmpX - xK), 2) > getEps()) {
            xK = tmpX;
            tmpX = xK - getFunction().f(xK) / getFunction().dF(xK);
            iterations++;
        }

        return new Result(tmpX, iterations);
    }
}
