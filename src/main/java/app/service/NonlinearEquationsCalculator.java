package app.service;

import app.util.lab1.IterationMethodResult;
import app.util.lab1.NonlinearFunction;

public class NonlinearEquationsCalculator {

    /**
     * NonlinearSettings
     */
    static double eps = 0.00001;
    static double min = 1;
    static double max = 2;



    public NonlinearFunction func = new NonlinearFunction();

    public IterationMethodResult Iter() {
        return Iter(max);
    }
    public IterationMethodResult Nuton() {
        return Iter(max);
    }


    public IterationMethodResult Pol_del() {
        int a = 0;
        double tmp_x = 0;
        double tmp_min = min;
        double tmp_max = max;
        while (((tmp_max - tmp_min) / 2) >= eps) {
            tmp_x = (tmp_max + tmp_min) / 2;
            if ((func.F(tmp_x) * func.F(tmp_min)) < 0) {
                tmp_max = tmp_x;
            } else {
                tmp_min = tmp_x;
            }

            a++;
        }

        return new IterationMethodResult(tmp_x, a);
    }

    public IterationMethodResult Iter(double x) {
        int b = 0;

        double m, M;
        double alph = 0;
        double tmp_x = x;

        m = M = Math.abs(func.dF(x));
        double[] tmp_double = new double[3];
        tmp_double[0] = Math.abs(M);
        tmp_double[1] = Math.abs(func.dF(min));
        tmp_double[2] = Math.abs(func.dF(max));

        for (int i = 0; i < 3; i++) {
            if (tmp_double[i] > M)
                M = tmp_double[i];
        }
        for (int i = 0; i < 3; i++) {
            if (tmp_double[i] < m)
                m = tmp_double[i];
        }

        while (Math.abs(func.F(tmp_x)) / m > eps) {

            if (func.dF(tmp_x) < 0) {
                alph = -2 / (M + m);
            } else {
                alph = 2 / (M + m);
            }
            tmp_x = tmp_x - alph * func.F(tmp_x);
            b++;
        }

        return new IterationMethodResult(tmp_x, b);
    }

    public IterationMethodResult Nuton(double x) {
        int c = 0;
        double m = 0, M = 0;
        double tmp_x = 0;
        double x_k = x;

        if (Math.abs(func.dF(min)) < Math.abs(func.dF(max))) {
            m = Math.abs(func.dF(min));
        } else {
            m = Math.abs(func.dF(max));
        }
        if (Math.abs(func.dF2(min)) < Math.abs(func.dF2(max))) {
            M = Math.abs(func.dF2(max));
        } else {
            M = Math.abs(func.dF2(min));
        }

        tmp_x = x_k - func.F(x_k) / func.dF(x_k);
        while ((M / (2 * m)) * Math.pow(Math.abs(tmp_x - x_k), 2) > eps) {
            x_k = tmp_x;
            tmp_x = x_k - func.F(x_k) / func.dF(x_k);
            c++;
        }

        return new IterationMethodResult(tmp_x, c);
    }

    public IterationMethodResult Hord() {
        int d = 0;
        double m, M, gamm;
        double tmp_x = max;
        double x_k = min;


        if (Math.abs(func.dF(min)) < Math.abs(func.dF(max))) {
            m = Math.abs(func.dF(min));
            M = Math.abs(func.dF(max));
        } else {
            m = Math.abs(func.dF(max));
            M = Math.abs(func.dF(min));
        }
        gamm = (m * eps) / (M - m);
        tmp_x = x_k - (func.F(x_k) / (func.F(max) - func.F(x_k))) * (max - x_k);

        while (Math.abs(tmp_x - x_k) > gamm) {
            x_k = tmp_x;
            tmp_x = x_k - (func.F(x_k) / (func.F(max) - func.F(x_k))) * (max - x_k);
            d++;
        }

        return new IterationMethodResult(tmp_x, d);
    }

}
