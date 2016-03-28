package app.util.data.polinomial;

public class PolinomialData {

    public static final int DATA_SIZE = 4;
    public static final int DATA_X_SIZE = 5;

    private final double[] x;
    private final double[] y;
    private double[] xi;

    public PolinomialData(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        xi = calculateXI();
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public double[] getXi() {
        return xi;
    }

    private double[] calculateXI() {
        xi = new double[DATA_X_SIZE];
        xi[0] = x[0] - (x[1] - x[0]) / 2;
        xi[1] = x[0] + (x[1] - x[0]) / 2;
        xi[2] = x[1] + (x[2] - x[1]) / 2;
        xi[3] = x[2] + (x[3] - x[2]) / 2;
        xi[4] = x[3] + (x[3] - x[2]) / 2;

        return xi;
    }

}
