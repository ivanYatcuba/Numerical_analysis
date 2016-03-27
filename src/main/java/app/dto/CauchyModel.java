package app.dto;

public class CauchyModel {
    private float X;
    private double Ye;
    private double Yrk;
    private double Ya;
    private double YDot;

    public CauchyModel(Float x) {
        X = x;
    }


    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public double getYe() {
        return Ye;
    }

    public void setYe(double ye) {
        Ye = ye;
    }

    public double getYrk() {
        return Yrk;
    }

    public void setYrk(double yrk) {
        Yrk = yrk;
    }

    public double getYa() {
        return Ya;
    }

    public void setYa(double ya) {
        Ya = ya;
    }

    public double getYDot() {
        return YDot;
    }

    public void setYDot(double YDot) {
        this.YDot = YDot;
    }
}
