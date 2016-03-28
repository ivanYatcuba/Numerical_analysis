package app.calculations.polynomial.impl;

import app.calculations.polynomial.AbstractLeastSquare;
import app.dto.PolinomialAnswer;
import app.util.data.polinomial.PolinomialData;

public class LeastSquaresQ2 extends AbstractLeastSquare {

    public LeastSquaresQ2(PolinomialData polinomialData) {
        super(polinomialData);
    }

    @Override
    public String getPolinomialName() {
        return "Q2(x)";
    }

    @Override
    public PolinomialAnswer calculate() {

        final PolinomialAnswer answer = new PolinomialAnswer(PolinomialData.DATA_X_SIZE, getPolinomialName());

        final double[] x = getPolinomialData().getX();
        final double[] y = getPolinomialData().getY();
        final double[] xi = getPolinomialData().getXi();

        final double[][] c = new double[3][3];
        final double[] d = new double[3];

        for (int i = 0; i < getDataSize(); i++) {
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
        final double[] a = calcGaussSystem(3, c, d);
        for (double aXi : xi) {
            answer.addAnswer(aXi, a[0] + a[1] * aXi + a[2] * Math.pow(aXi, 2));
        }
        return answer;
    }
}
