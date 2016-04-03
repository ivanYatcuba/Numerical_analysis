package app.calculations.integral.impl;

import app.calculations.integral.IntegralCalculator;
import app.util.data.integration.NumericalIntegration;

public class SimpsonCalculator implements IntegralCalculator {
    @Override
    public String methodName() {
        return "Метод Сімпсона";
    }

    @Override
    public float calculate(double a, double b, int n) {
        double result, h, sumx2k, sumx2k_1;
        double[] x = new double[n + 1];

        sumx2k = sumx2k_1 = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 1; i < 6; i++) {
            sumx2k_1 += NumericalIntegration.F(x[2 * i - 1]);
        }
        for (int i = 1; i < 5; i++) {
            sumx2k += NumericalIntegration.F(x[2 * i]);
        }
        result = h / 3 * (NumericalIntegration.F(x[0]) + NumericalIntegration.F(x[n]) + 4 * sumx2k_1 + 2 * sumx2k);
        return (float) result;
    }
}
