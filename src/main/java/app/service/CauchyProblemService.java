package app.service;


public class CauchyProblemService {
    public static int N = 10;
    private static double a = 1, b = 2;

    public double[] y = new double[N + 1];
    private double h = (b - a) / N;

    public double function(double x, double y) {
        return (y / x) * Math.log(y / x);
    }

    public void initStartValue() {
        y[0] = 1;
    }

    public double[] getXSteps() {
        double[] x = new double[N + 1];
        for (int i = 0; i < N + 1; i++) {
            x[i] = a + i * h;
        }

        return x;
    }

    public double[] eulerMethod() {
        double[] x = getXSteps();

        for (int i = 0; i < N; i++) {
            y[i + 1] = y[i] + h * function(x[i], y[i]);
        }

        return y;
    }

    public double[] rungeKuttaMethod() {
        double[] k1 = new double[N];
        double[] k2 = new double[N];
        double[] k3 = new double[N];
        double[] x = getXSteps();

        for (int i = 0; i < N; i++) {
            k1[i] = h * function(x[i], y[i]);
            k2[i] = h * function(x[i] + h / 2, y[i] + k1[i] / 2);
            k3[i] = h * function(x[i] + h, y[i] - k1[i] + 2 * k1[2]);
            y[i + 1] = y[i] + (k1[i] + 4 * k2[i] + k3[i]) / 6;
        }
        return y;
    }

    public double[] adamsMethod() {
        double[] x = getXSteps();

        for (int i = 3; i < N; i++) {
            y[i + 1] = y[i] + h / 24 * (55 * function(x[i], y[i]) - 59 * function(x[i - 1], y[i - 1]) + 37 * function(x[i - 2], y[i - 2]) - 9 * function(x[i - 3], y[i - 3]));
        }

        return y;
    }

    public double[] exactCalculate() {
        double[] x = getXSteps();

        for (int i = 0; i < N + 1; i++) {
            y[i] = x[i] * Math.exp(-x[i] + 1);
        }

        return y;
    }


}
