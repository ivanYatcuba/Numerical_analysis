package app.calculations.polynomial;

import app.calculations.linear.system.calculator.impl.GaussianLinearSystemCalculator;
import app.dto.PolinomialAnswer;
import app.util.data.linear.system.LinearSystem;
import app.util.data.polinomial.PolinomialData;

public abstract class AbstractLeastSquare extends AbstractPolinomialCalculator {

    public AbstractLeastSquare(PolinomialData polinomialData) {
        super(polinomialData);
    }

    protected double[] calcGaussSystem(int n, final double[][] matrix_A, final double[] matrix_F) {
        final GaussianLinearSystemCalculator systemCalculator = new GaussianLinearSystemCalculator(new LinearSystem() {
            @Override
            public double[] newInstanceFMatrix() {
                return matrix_F;
            }

            @Override
            public double[][] newInstanceAMatrix() {
                return matrix_A;
            }

            @Override
            public int getId() {
                return 0;
            }
        }, n);

        return systemCalculator.calculateRaw();
    }

    public double calculateDeviation() {
        final double[] y = getPolinomialData().getY();
        final PolinomialAnswer answer = calculate();
        double sum = 0;

        for (int i = 0; i < getDataSize(); i++) {
            sum += Math.pow((y[i] - answer.getPAt(i)), 2);
        }
        return Math.sqrt(sum / getDataSize());
    }

}
