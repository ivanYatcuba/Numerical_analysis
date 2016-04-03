package app.calculations.integral.impl;

import app.calculations.integral.IntegralCalculator;
import app.util.data.integration.NumericalIntegration;

public class TrapezoidsCalculator implements IntegralCalculator {

    @Override
    public String methodName() {
        return "Метод трапецій";
    }

    @Override
    public float calculate(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        for (int i = 1; i < n; i++) {
            result += NumericalIntegration.F(x[i]);
        }

        result *= h;
        result += h / 2 * (NumericalIntegration.F(x[0]) + NumericalIntegration.F(x[n]));

        return (float) result;
    }
}
