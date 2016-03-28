package app.calculations.polynomial.impl;

import app.calculations.polynomial.AbstractPolinomialCalculator;
import app.dto.PolinomialAnswer;
import app.util.data.polinomial.PolinomialData;

public class LagrangePolynomial extends AbstractPolinomialCalculator {

    public LagrangePolynomial(PolinomialData polinomialData) {
        super(polinomialData);
    }

    @Override
    public String getPolinomialName() {
        return "L3(x)";
    }

    @Override
    public PolinomialAnswer calculate() {

        final PolinomialAnswer answer = new PolinomialAnswer(PolinomialData.DATA_X_SIZE, getPolinomialName());

        final double[] x = getPolinomialData().getX();
        final double[] y = getPolinomialData().getY();
        final double[] xi = getPolinomialData().getXi();

        for (int k = 0; k < xi.length; k++) {
            double L = 0;
            for (int i = 0; i < getDataSize(); i++) {
                double multiplication = 1;
                for (int j = 0; j < getDataSize(); j++) {
                    if (j != i) {
                        multiplication *= (xi[k] - x[j]) / (x[i] - x[j]);
                    }
                }
                L += y[i] * multiplication;
            }
            answer.addAnswer(xi[k], L);
        }

        return answer;
    }
}
