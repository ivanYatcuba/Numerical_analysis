package app.calculations.polynomial.impl;

import app.calculations.polynomial.AbstractPolinomialCalculator;
import app.dto.PolinomialAnswer;
import app.util.data.polinomial.PolinomialData;

public class NutonPolinomial extends AbstractPolinomialCalculator {

    public NutonPolinomial(PolinomialData polinomialData) {
        super(polinomialData);
    }

    @Override
    public String getPolinomialName() {
        return "P3(x)";
    }

    @Override
    public PolinomialAnswer calculate() {

        final PolinomialAnswer answer = new PolinomialAnswer(PolinomialData.DATA_X_SIZE, getPolinomialName());

        final double[] x = getPolinomialData().getX();
        final double[] y = getPolinomialData().getY();
        final double[] xi = getPolinomialData().getXi();

        final double[] f1 = new double[4];
        final double[] f2 = new double[4];
        final double[] f3 = new double[4];

        double f;
        double P;
        f = y[0];
        for (int i = 0; i < getDataSize() - 1; i++) {
            f1[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
        }
        for (int i = 0; i < getDataSize() - 2; i++) {
            f2[i] = (f1[i + 1] - f1[i]) / (x[i + 2] - x[i]);
        }
        for (int i = 0; i < getDataSize() - 3; i++) {
            f3[i] = (f2[i + 1] - f2[i]) / (x[i + 3] - x[i]);
        }

        for (int i = 0; i < xi.length; i++) {
            P = f + f1[0] * (xi[i] - x[0]) + f2[0] * (xi[i] - x[0]) * (xi[i] - x[1]) + f3[0] * (xi[i] - x[0]) * (xi[i] - x[1]) * (xi[i] - x[2]);
           answer.addAnswer(xi[i], P);
        }

        return answer;
    }
}
