package app.service;

import java.util.ArrayList;

public class CauchyProblemService {
    public static int n = 10;
    static double a = 1, b = 2;
    public double[] y = new double[n + 1];
    double h = (b - a) / n;


    public ArrayList<Float> getXSteps() {
        ArrayList<Float> xSteps = new ArrayList<>();
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            xSteps.add(((float) (a + i * h)));
        }

        return xSteps;
    }

    public double F(double x, double y) {
        return Math.exp(y) - 2 / x;
    }

    public void CalcEller() {
        double[] x = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        for (int i = 0; i < n; i++) {
            y[i + 1] = y[i] + h * F(x[i], y[i]);
        }

    }

    public void CalcRengeKut() {
        double[] k1 = new double[n];
        double[] k2 = new double[n];
        double[] k3 = new double[n];
        double[] x = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }

        for (int i = 0; i < n; i++) {
            k1[i] = h * F(x[i], y[i]);
            k2[i] = h * F(x[i] + h / 2, y[i] + k1[i] / 2);
            k3[i] = h * F(x[i] + h, y[i] - k1[i] + 2 * k1[2]);
            y[i + 1] = y[i] + (k1[i] + 4 * k2[i] + k3[i]) / 6;
        }


    }

    public void CalcAdams() {
        double[] x = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        for (int i = 3; i < n; i++) {
            y[i + 1] = y[i] + h / 24 * (55 * F(x[i], y[i]) - 59 * F(x[i - 1], y[i - 1]) + 37 * F(x[i - 2], y[i - 2]) - 9 * F(x[i - 3], y[i - 3]));
        }
    }

    public void CalcToch() {
        double[] x = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        for (int i = 0; i < n + 1; i++) {
            y[i] = -Math.log(x[i] * (x[i] + 1));
        }
    }


}
