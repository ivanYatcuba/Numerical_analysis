package app.dto;

public class IterationMethodResult {
    private double result;
    private int numberIteration;

    public IterationMethodResult(double result, int numberIteration) {
        this.result = result;
        this.numberIteration = numberIteration;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getNumberIteration() {
        return numberIteration;
    }

    public void setNumberIteration(int numberIteration) {
        this.numberIteration = numberIteration;
    }
}
