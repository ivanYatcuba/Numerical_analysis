package app.dto;

public class SystemSolutionDto {

    private String function;
    private int functionNumber;

    private double x1;
    private double x2;
    private double x3;

    private double sigma1;
    private double sigma2;
    private double sigma3;

    private String iterations;


    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getFunctionNumber() {
        return functionNumber;
    }

    public void setFunctionNumber(int functionNumber) {
        this.functionNumber = functionNumber;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getSigma1() {
        return sigma1;
    }

    public void setSigma1(double sigma1) {
        this.sigma1 = sigma1;
    }

    public double getSigma2() {
        return sigma2;
    }

    public void setSigma2(double sigma2) {
        this.sigma2 = sigma2;
    }

    public double getSigma3() {
        return sigma3;
    }

    public void setSigma3(double sigma3) {
        this.sigma3 = sigma3;
    }

    public String getIterations() {
        return iterations;
    }

    public void setIterations(String iterations) {
        this.iterations = iterations;
    }
}
