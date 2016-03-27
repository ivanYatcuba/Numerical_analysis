package app.service;

import app.service.linearsystem.calculator.GaussianEliminationCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionApproximationService {
    public static int SIZE = 4;

    public final double[] x = {-3, -1, 0, 1};
    public final double[] y = {3, -2, 0, 3,};
    private final GaussianEliminationCalculator gaussianCalculator = new GaussianEliminationCalculator();
    public double[] xi = new double[SIZE + 1];
    public List<Double> L_coef = new ArrayList<>();
    public List<Double> P_coef = new ArrayList<>();
    public List<Double> Q0_coef = new ArrayList<>();
    public List<Double> Q1_coef = new ArrayList<>();
    public List<Double> Q2_coef = new ArrayList<>();
    public List<Double> Q3_coef = new ArrayList<>();

    public void CalculateL(double[] xi) {
        L_coef.clear();
        double L, Proiz;

        for (int k = 0; k < xi.length; k++) {
            L = 0;
            for (int i = 0; i < SIZE; i++) {
                Proiz = 1;
                for (int j = 0; j < SIZE; j++) {
                    if (j != i) {
                        Proiz *= (xi[k] - x[j]) / (x[i] - x[j]);
                    }
                }

                L += y[i] * Proiz;
            }
            L_coef.add(L);
        }
    }

    public void CalculateP(double[] xi) {
        P_coef.clear();
        final double f = y[0];

        final double[] f1 = new double[4];
        for (int i = 0; i < SIZE - 1; i++) {
            f1[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
        }


        final double[] f2 = new double[4];
        for (int i = 0; i < SIZE - 2; i++) {
            f2[i] = (f1[i + 1] - f1[i]) / (x[i + 2] - x[i]);
        }

        final double[] f3 = new double[4];
        for (int i = 0; i < SIZE - 3; i++) {
            f3[i] = (f2[i + 1] - f2[i]) / (x[i + 3] - x[i]);
        }

        for (int i = 0; i < xi.length; i++) {
            double P = f
                    + f1[0] * (xi[i] - x[0])
                    + f2[0] * (xi[i] - x[0]) * (xi[i] - x[1])
                    + f3[0] * (xi[i] - x[0]) * (xi[i] - x[1]) * (xi[i] - x[2]);
            P_coef.add(P);
        }
    }

    public void CalculateQ0() {
        Q0_coef.clear();
        double d = Arrays.stream(y).sum();

        for (int i = 0; i < xi.length; i++) {
            Q0_coef.add(d / SIZE);
        }
    }

    public void CalculateQ1(double[] xi) {
        Q1_coef.clear();
        double[][] c = new double[2][2];
        double[] d = new double[2];

        for (int i = 0; i < SIZE; i++) {
            c[0][0]++;
            c[0][1] += x[i];
            c[1][0] = c[0][1];
            c[1][1] += Math.pow(x[i], 2);
            d[0] += y[i];
            d[1] += x[i] * y[i];
        }
        double[] a = gaussianCalculator.krivoCalculate(c, d, 2);
        for (int i = 0; i < xi.length; i++) {
            Q1_coef.add(a[0] + a[1] * xi[i]);
        }

    }

    public void CalculateQ2(double[] xi) {
        Q2_coef.clear();
        double[][] c = new double[3][3];
        double[] d = new double[3];

        for (int i = 0; i < SIZE; i++) {
            c[0][0]++;
            c[0][1] += x[i];
            c[1][0] = c[0][1];
            c[1][1] += Math.pow(x[i], 2);
            c[2][0] = c[0][2] = c[1][1];
            c[1][2] += Math.pow(x[i], 3);
            c[2][1] = c[1][2];
            c[2][2] += Math.pow(x[i], 4);
            d[0] += y[i];
            d[1] += x[i] * y[i];
            d[2] += x[i] * x[i] * y[i];
        }
        double[] a = gaussianCalculator.krivoCalculate(c, d, 3);
        for (double aXi : xi) {
            Q2_coef.add(a[0] + a[1] * aXi + a[2] * Math.pow(aXi, 2));
        }

    }

    public void CalculateQ3(double[] xi) {
        Q3_coef.clear();
        double[][] c = new double[4][4];
        double[] d = new double[4];
        for (int i = 0; i < SIZE; i++) {
            c[0][0]++;
            c[0][1] += x[i];
            c[1][0] = c[0][1];
            c[1][1] += Math.pow(x[i], 2);
            c[2][0] = c[0][2] = c[1][1];
            c[1][2] += Math.pow(x[i], 3);
            c[2][1] = c[0][3] = c[3][0] = c[1][2];
            c[2][2] += Math.pow(x[i], 4);
            c[1][3] = c[3][1] = c[2][2];
            c[2][3] += Math.pow(x[i], 5);
            c[3][2] = c[2][3];
            c[3][3] += Math.pow(x[i], 6);
            d[0] += y[i];
            d[1] += x[i] * y[i];
            d[2] += x[i] * x[i] * y[i];
            d[3] += x[i] * x[i] * x[i] * y[i];
        }
        double[] a = gaussianCalculator.krivoCalculate(c, d, 4);
        for (double aXi : xi) {
            Q3_coef.add(a[0] + a[1] * aXi + a[2] * Math.pow(aXi, 2) + a[3] * Math.pow(aXi, 3));
        }

    }

    public double[] CalculateXI() {
        xi[0] = x[0] - (x[1] - x[0]) / 2;
        xi[1] = x[0] + (x[1] - x[0]) / 2;
        xi[2] = x[1] + (x[2] - x[1]) / 2;
        xi[3] = x[2] + (x[3] - x[2]) / 2;
        xi[4] = x[3] + (x[3] - x[2]) / 2;

        return xi;
    }

    public double[] CalculateDet() {
        double[] det = new double[SIZE];
        double sum = 0;
        CalculateL(x);
        CalculateP(x);
        CalculateQ1(x);
        CalculateQ2(x);
        CalculateQ3(x);
        for (int i = 0; i < SIZE; i++) {
            sum += Math.pow((y[i] - Q0_coef.get(0)), 2);
        }
        det[0] = Math.sqrt(sum / SIZE);
        sum = 0;

        for (int i = 0; i < SIZE; i++) {
            sum += Math.pow((y[i] - Q1_coef.get(i)), 2);
        }
        det[1] = Math.sqrt(sum / SIZE);
        sum = 0;

        for (int i = 0; i < SIZE; i++) {
            sum += Math.pow((y[i] - Q2_coef.get(i)), 2);
        }
        det[2] = Math.sqrt(sum / SIZE);
        sum = 0;

        for (int i = 0; i < SIZE; i++) {
            sum += Math.pow((y[i] - Q3_coef.get(i)), 2);
        }
        det[3] = Math.sqrt(sum / SIZE);

        return det;
    }


    public void CalculateL() {
        CalculateL(x);
    }

    public void CalculateP() {
        CalculateP(x);
    }

    public void CalculateQ1() {
        CalculateQ1(x);
    }

    public void CalculateQ2() {
        CalculateQ2(x);
    }

    public void CalculateQ3() {
        CalculateQ3(x);
    }


    public void CalculateLi() {
        CalculateL(xi);
    }

    public void CalculatePi() {
        CalculateP(xi);
    }

    public void CalculateQ1i() {
        CalculateQ1(xi);
    }

    public void CalculateQ2i() {
        CalculateQ2(xi);
    }

    public void CalculateQ3i() {
        CalculateQ3(xi);
    }
}
