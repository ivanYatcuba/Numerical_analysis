package app.service;

public class NumericalIntegrationService {

    public double F(double x) //Подынтегральная функция
    {
        return (x + 1.9) * Math.sin(x / 3);
    }

    public double CalcRectangleMiddle(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            result += F((x[i + 1] + x[i]) / 2);
        }

        result *= h;

        return result;
    }

    public double CalcRectangleLeft(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            result += F((x[i]));
        }

        result *= h;

        return result;
    }

    public double CalcRectangleRight(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            result += F((x[i + 1]));
        }

        result *= h;

        return result;
    }


    public double CalcGauss(double a, double b, int n) {
        double[] t = {-0.97823, -0.88706, -0.73015, -0.5191, -0.26954, 0.00000, 0.26954, 0.5191, 0.73015, 0.88706, 0.97823};
        double[] A = {0.05567, 0.12558, 0.18629, 0.23319, 0.2628, 0.27293, 0.2628, 0.23319, 0.18629, 0.12558, 0.05567};
        double[] z = new double[n + 1];
        double result;

        result = 0;
        for (int i = 0; i < n + 1; i++) {
            z[i] = (1 + t[i]) / 2;
        }
        for (int i = 0; i < n + 1; i++) {
            result += A[i] * F(z[i]);
        }

        result *= (b - a) / 2;
        return result;
    }

    public double CalcSimpson(double a, double b, int n) {
        double result, h, sumx2k, sumx2k_1;
        double[] x = new double[n + 1];

        sumx2k = sumx2k_1 = result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 1; i < 6; i++) {
            sumx2k_1 += F(x[2 * i - 1]);
        }
        for (int i = 1; i < 5; i++) {
            sumx2k += F(x[2 * i]);
        }
        result = h / 3 * (F(x[0]) + F(x[n]) + 4 * sumx2k_1 + 2 * sumx2k);
        return result;
    }


    public double CalcTrapezoidal(double a, double b, int n) {
        double result, h;
        double[] x = new double[n + 1];

        result = 0;

        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        for (int i = 1; i < n; i++) {
            result += F(x[i]);
        }

        result *= h;
        result += h / 2 * (F(x[0]) + F(x[n]));

        return result;
    }


}
