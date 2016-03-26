package app.model;

public class MyTableModel {
    private final double xi;

    private final double L_coef;
    private final double P_coef;
    private final double Q0_coef;
    private final double Q1_coef;
    private final double Q2_coef;
    private final double Q3_coef;

    public MyTableModel(double xi, double L_coef, double P_coef, double Q0_coef, double Q1_coef, double Q2_coef, double Q3_coef) {
        this.xi = xi;
        this.L_coef = L_coef;
        this.P_coef = P_coef;
        this.Q0_coef = Q0_coef;
        this.Q1_coef = Q1_coef;
        this.Q2_coef = Q2_coef;
        this.Q3_coef = Q3_coef;
    }


    public double getXi() {
        return xi;
    }

    public double getL_coef() {
        return L_coef;
    }

    public double getP_coef() {
        return P_coef;
    }

    public double getQ0_coef() {
        return Q0_coef;
    }

    public double getQ1_coef() {
        return Q1_coef;
    }

    public double getQ2_coef() {
        return Q2_coef;
    }

    public double getQ3_coef() {
        return Q3_coef;
    }
}
