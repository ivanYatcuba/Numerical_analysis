package app.service;

import app.data.NonlinearFunction;
import app.dto.IterationMethodResult;

public class NonlinearEquationsCalculator {

    /**
     * NonlinearSettings
     */
    static double eps = 0.00001;
    static double min = 1;
    static double max = 2;



    public NonlinearFunction func = new NonlinearFunction();


    public IterationMethodResult calculateHalfDivisionEquation() {
        int a = 0;
        double tmp_x = 0;
        double tmp_min = min;
        double tmp_max = max;
        while (((tmp_max - tmp_min) / 2) >= eps) {
            tmp_x = (tmp_max + tmp_min) / 2;
            if ((func.f(tmp_x) * func.f(tmp_min)) < 0) {
                tmp_max = tmp_x;
            } else {
                tmp_min = tmp_x;
            }

            a++;
        }

        return new IterationMethodResult(tmp_x, a);
    }

    public IterationMethodResult calculateChordEquation() {
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
        tmp_x = x_k - (func.f(x_k) / (func.f(max) - func.f(x_k))) * (max - x_k);

        while (Math.abs(tmp_x - x_k) > gamm) {
            x_k = tmp_x;
            tmp_x = x_k - (func.f(x_k) / (func.f(max) - func.f(x_k))) * (max - x_k);
            d++;
        }

        return new IterationMethodResult(tmp_x, d);
    }


    public IterationMethodResult calculateSimpleIterationsEquation() {
        int b = 0;

        double m, M;
        double alph;
        double tmp_x = min;

        double fByMin = Math.abs(func.dF(min));
        double fByMax = Math.abs(func.dF(max));

        M = fByMax > fByMin ? fByMax : fByMin;
        m = fByMax > fByMin ? fByMin : fByMax;


        while ((Math.abs(func.f(tmp_x)) / m) > eps) {

            if (func.dF(tmp_x) < 0) {
                alph = -2 / (M + m);
            } else {
                alph = 2 / (M + m);
            }
            tmp_x = tmp_x - alph * func.f(tmp_x);
            b++;
        }

        return new IterationMethodResult(tmp_x, b);
    }

    public IterationMethodResult calculateNutonEquation() {
        int c = 0;
        double m;
        double M;
        double tmp_x;
        double x_k = min;


        double fByMin = Math.abs(func.dF(min));
        double fByMax = Math.abs(func.dF(max));
        m = fByMax > fByMin ? fByMin : fByMax;

        double f2ByMin = Math.abs(func.dF2(min));
        double f2ByMax = Math.abs(func.dF2(max));
        M = f2ByMax > f2ByMin ? f2ByMax : f2ByMin;



        tmp_x = x_k - func.f(x_k) / func.dF(x_k);
        while ((M / (2 * m)) * Math.pow(Math.abs(tmp_x - x_k), 2) > eps) {
            x_k = tmp_x;
            tmp_x = x_k - func.f(x_k) / func.dF(x_k);
            c++;
        }

        return new IterationMethodResult(tmp_x, c);
    }
}
