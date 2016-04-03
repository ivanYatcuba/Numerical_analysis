package app.calculations.integral.impl;

import app.calculations.integral.IntegralCalculator;
import app.util.data.integration.NumericalIntegration;

public class GaussianCalculator implements IntegralCalculator {
    @Override
    public String methodName() {
        return "Метод Гаусса";
    }

    @Override
    public float calculate(double a, double b, int n) {
        double[] t = {-0.97823, -0.88706, -0.73015, -0.5191, -0.26954, 0.00000, 0.26954, 0.5191, 0.73015, 0.88706, 0.97823};
        double[] A = {0.05567, 0.12558, 0.18629, 0.23319, 0.2628, 0.27293, 0.2628, 0.23319, 0.18629, 0.12558, 0.05567};
        double[] z = new double[n + 1];
        double result;

        result = 0;
        for (int i = 0; i < n + 1; i++) {
            z[i] =  (1f + t[i]) / 2f;
        }
        for (int i = 0; i < n + 1; i++) {
            result += A[i] * NumericalIntegration.F(z[i]);
        }

        result *= (b - a) / 2;
        return (float) result;
    }
}
