package app.util.data.nonlinear;

public class Function22 implements Function {

    final int a = 1;

    int b = -2;
    int c = 3;
    int d = -4;

    @Override
    public double f(double x) {
        return a * x * x * x
                + b * x * x
                + c * x
                + d;
    }

    @Override
    public double dF(double x) {
        return 3 * a * x * x
                + 2 * b * x
                + c;
    }

    @Override
    public double ddF2(double x) {
        return 6*x - 4;
    }

    @Override
    public double dF2(double x) {
        return -1 * x + 3;
    }

    @Override
    public double dF3(double x) {
        return 1;
    }

    @Override
    public Integer[] getCoefs() {
        return new Integer[]{a, b, c};
    }

    @Override
    public String toString() {
        return  "x^3 "
                + strParameter(b, "*x^2 ")
                + strParameter(c, "*x ")
                + strParameter(d, " ")
                + "= 0";
    }

    private String strParameter(double b, String sufix) {
        return b == 0 ? "" : str(b) + sufix;
    }


    private String str(double n) {
        return n < 0 ? String.valueOf(n) : "+" + String.valueOf(n);
    }
}
