package app.calculations.integral.impl;

import app.calculations.integral.IntegralCalculator;
import app.util.data.integration.NumericalIntegration;

public class RectangleRight implements IntegralCalculator {

    @Override
    public String methodName() {
        return "Метод правих прямокутників";
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

        for (int i = 0; i < n; i++) {
            result += NumericalIntegration.F((x[i + 1]));
        }

        result *= h;

        return (float) result;
    }
}
