package app.dto;

public class MyTableModel {
    private final double xi;

    private final String L_coef;
    private final String P_coef;
    private final String Q0_coef;
    private final String Q1_coef;
    private final String Q2_coef;
    private final String Q3_coef;

    public MyTableModel(double xi, String l_coef, String p_coef, String q0_coef, String q1_coef, String q2_coef, String q3_coef) {
        this.xi = xi;
        L_coef = l_coef;
        P_coef = p_coef;
        Q0_coef = q0_coef;
        Q1_coef = q1_coef;
        Q2_coef = q2_coef;
        Q3_coef = q3_coef;
    }

    public double getXi() {
        return xi;
    }

    public String getL_coef() {
        return L_coef;
    }

    public String getP_coef() {
        return P_coef;
    }

    public String getQ0_coef() {
        return Q0_coef;
    }

    public String getQ1_coef() {
        return Q1_coef;
    }

    public String getQ2_coef() {
        return Q2_coef;
    }

    public String getQ3_coef() {
        return Q3_coef;
    }
}
