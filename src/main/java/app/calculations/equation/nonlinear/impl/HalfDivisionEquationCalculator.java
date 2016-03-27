package app.calculations.equation.nonlinear.impl;

import app.calculations.equation.nonlinear.Result;
import app.util.data.nonlinear.Function;

public class HalfDivisionEquationCalculator extends AbstractEquationCalculator {

    public HalfDivisionEquationCalculator(double eps, double intervalMax, double intervalMin, Function function) {
        super(eps, intervalMax, intervalMin, function);
    }

    @Override
    public String getName() {
        return "Метод ділення навпіл";
    }

    @Override
    public Result calculate() {
        int iterations = 0;
        double tmpX = 0;
        double tmpMin = getIntervalMin();
        double tmpMax = getIntervalMax();
        while (((tmpMax - tmpMin) / 2) >= getEps()) {
            tmpX = (tmpMax + tmpMin) / 2;
            if ((getFunction().f(tmpX) * getFunction().f(tmpMin)) < 0) {
                tmpMax = tmpX;
            } else {
                tmpMin = tmpX;
            }
            iterations++;
        }
        return new Result(tmpX, iterations);
    }

}
