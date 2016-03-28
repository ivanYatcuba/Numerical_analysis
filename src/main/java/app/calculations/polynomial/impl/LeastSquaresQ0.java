package app.calculations.polynomial.impl;

import app.calculations.polynomial.AbstractLeastSquare;
import app.dto.PolinomialAnswer;
import app.util.data.polinomial.PolinomialData;

public class LeastSquaresQ0 extends AbstractLeastSquare {

    public LeastSquaresQ0(PolinomialData polinomialData) {
        super(polinomialData);
    }

    @Override
    public String getPolinomialName() {
        return "Q0(x)";
    }

    @Override
    public PolinomialAnswer calculate() {

        final PolinomialAnswer answer = new PolinomialAnswer(PolinomialData.DATA_X_SIZE, getPolinomialName());

        final double[] y = getPolinomialData().getY();
        final double[] xi = getPolinomialData().getXi();

        double d;
        double c;
        d = c = 0;
        for (int i = 0; i < getDataSize(); i++) {
            c++;
            d += y[i];
        }
        for (double aXi : xi) {
            answer.addAnswer(aXi, d / c);
        }

        return answer;
    }

}
