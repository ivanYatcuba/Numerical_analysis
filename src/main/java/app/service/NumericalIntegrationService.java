package app.service;

import app.data.NumericalIntegration;

public class NumericalIntegrationService {


    public double calculateRectangleMiddle(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            result += NumericalIntegration.F((x[i + 1] + x[i]) / 2);
        }

        result *= h;

        return result;
    }

    public double calculateRectangleLeft(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            result += NumericalIntegration.F((x[i]));
        }

        result *= h;

        return result;
    }

    public double calculateRectangleRight(double a, double b, int n) {
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

        return result;
    }


    public double calculateGaussian(double a, double b, int n) {
        double[] t = {-0.97823, -0.88706, -0.73015, -0.5191, -0.26954, 0.00000, 0.26954, 0.5191, 0.73015, 0.88706, 0.97823};
        double[] A = {0.05567, 0.12558, 0.18629, 0.23319, 0.2628, 0.27293, 0.2628, 0.23319, 0.18629, 0.12558, 0.05567};
        double[] z = new double[n + 1];
        double result;

        result = 0;
        for (int i = 0; i < n + 1; i++) {
            z[i] =  (((b - a) / 2) * t[i]);
        }
        for (int i = 0; i < n + 1; i++) {
            result += A[i] * NumericalIntegration.F(z[i]);
        }

        result *= (b - a) / 2;
        return result;
    }

    public double calculateSimpson(double a, double b, int n) {
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
        return result;
    }


    public double calculateTrapezoidal(double a, double b, int n) {
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

        return result;
    }


}
