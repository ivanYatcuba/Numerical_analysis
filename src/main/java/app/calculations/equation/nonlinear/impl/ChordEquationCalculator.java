package app.calculations.equation.nonlinear.impl;

import app.calculations.equation.nonlinear.Result;
import app.util.data.Function;

public class ChordEquationCalculator extends AbstractEquationCalculator {

    public ChordEquationCalculator(double eps, double intervalMax, double intervalMin, Function function) {
        super(eps, intervalMax, intervalMin, function);
    }

    @Override
    public String getName() {
        return "Метод хорд";
    }

    @Override
    public Result calculate() {

        int iterations = 0;
        double m;
        double M;
        double gamma;
        double tmpX;
        double xK = getIntervalMin();

        if (Math.abs(getFunction().dF(getIntervalMin())) < Math.abs(getFunction().dF(getIntervalMax()))) {
            m = Math.abs(getFunction().dF(getIntervalMin()));
            M = Math.abs(getFunction().dF(getIntervalMax()));
        } else {
            m = Math.abs(getFunction().dF(getIntervalMax()));
            M = Math.abs(getFunction().dF(getIntervalMin()));
        }
        gamma = (m * getEps()) / (M - m);
        tmpX = xK - (getFunction().f(xK) / (getFunction().f(getIntervalMax()) - getFunction().f(xK))) * (getIntervalMax() - xK);

        while (Math.abs(tmpX - xK) > gamma) {
            xK = tmpX;
            tmpX = xK - (getFunction().f(xK) / (getFunction().f(getIntervalMax()) - getFunction().f(xK))) * (getIntervalMax() - xK);
            iterations++;
        }

        return new Result(tmpX, iterations);
    }
}
