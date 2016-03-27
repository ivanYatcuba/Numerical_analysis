package app.calculations.equation.nonlinear.impl;

import app.calculations.equation.nonlinear.Result;
import app.util.data.nonlinear.Function;

public class SimpleIterationsEquationCalculator extends AbstractEquationCalculator {

    public SimpleIterationsEquationCalculator(double eps, double intervalMax, double intervalMin, Function function) {
        super(eps, intervalMax, intervalMin, function);
    }

    @Override
    public String getName() {
        return "Метод простих ітерацій";
    }

    @Override
    public Result calculate() {

        double x = 2f/3f;

        int iterations = 0;

        double m;
        double M;
        double alpha;
        double tmp_x = x;

        m = M = Math.abs(getFunction().dF(x));
        double[] tmp_double = new double[3];
        tmp_double[0] = getFunction().dF(x);
        tmp_double[1] = Math.abs(getFunction().dF(getIntervalMin()));
        tmp_double[2] = Math.abs(getFunction().dF(getIntervalMax()));

        for (int i = 0; i < 3; i++) {
            if (tmp_double[i] > M)
                M = tmp_double[i];
        }
        for (int i = 0; i < 3; i++) {
            if (tmp_double[i] < m)
                m = tmp_double[i];
        }

        while (Math.abs(getFunction().f(tmp_x)) / m > getEps()) {

            if (getFunction().dF(tmp_x) < 0) {
                alpha = -2 / (M + m);
            } else {
                alpha = 2 / (M + m);
            }
            tmp_x = tmp_x - alpha * getFunction().f(tmp_x);
            iterations++;
        }

        return new Result(tmp_x, iterations);
    }
}
