package app.util.data.nonlinear;

public interface Function {

    double f(double x);

    double dF(double x);

    double ddF2(double x);

    double dF2(double x);

    double dF3(double x);

    Integer[] getCoefs();

}
