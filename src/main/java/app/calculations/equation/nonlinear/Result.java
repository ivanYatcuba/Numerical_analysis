package app.calculations.equation.nonlinear;

public class Result {

    private double result;
    private int numberIteration;

    public Result(double result, int numberIteration) {
        this.result = result;
        this.numberIteration = numberIteration;
    }

    public double getResult() {
        return result;
    }

    public int getNumberIteration() {
        return numberIteration;
    }

}
