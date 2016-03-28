package app.calculations.polynomial;

import app.util.data.polinomial.PolinomialData;

public abstract class AbstractPolinomialCalculator implements PolinomialCalculator {

    private PolinomialData polinomialData;
    private final int dataSize;

    public AbstractPolinomialCalculator(PolinomialData polinomialData) {
        this.polinomialData = polinomialData;
        dataSize = PolinomialData.DATA_SIZE;
    }

    public abstract String getPolinomialName();

    public PolinomialData getPolinomialData() {
        return polinomialData;
    }

    public int getDataSize() {
        return dataSize;
    }
}
