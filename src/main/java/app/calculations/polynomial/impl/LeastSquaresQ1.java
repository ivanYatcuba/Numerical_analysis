package app.calculations.polynomial.impl;

import app.calculations.polynomial.AbstractLeastSquare;
import app.dto.PolinomialAnswer;
import app.util.data.polinomial.PolinomialData;

public class LeastSquaresQ1 extends AbstractLeastSquare {

    public LeastSquaresQ1(PolinomialData polinomialData) {
        super(polinomialData);
    }

    @Override
    public String getPolinomialName() {
        return "Q1(x)";
    }

    @Override
    public PolinomialAnswer calculate() {

        final PolinomialAnswer answer = new PolinomialAnswer(PolinomialData.DATA_X_SIZE, getPolinomialName());

        final double[] x = getPolinomialData().getX();
        final double[] y = getPolinomialData().getY();
        final double[] xi = getPolinomialData().getXi();

        final double[][] c = new double[2][2];
        final double[] d = new double[2];

        for (int i = 0; i < getDataSize(); i++) {
            c[0][0]++;
            c[0][1] += x[i];
            c[1][0] = c[0][1];
            c[1][1] += Math.pow(x[i], 2);
            d[0] += y[i];
            d[1] += x[i] * y[i];
        }
        final double[] a = calcGaussSystem(2, c, d);
        for (double aXi : xi) {
            answer.addAnswer(aXi, a[0] + a[1] * aXi);
        }

        return answer;
    }

}
