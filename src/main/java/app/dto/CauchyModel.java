package app.dto;

public class CauchyModel {
    private String X;
    private String Ye;
    private String Yrk;
    private String Ya;
    private String YDot;

    public CauchyModel(String x) {
        X = x;
    }


    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getYe() {
        return Ye;
    }

    public void setYe(String ye) {
        Ye = ye;
    }

    public String getYrk() {
        return Yrk;
    }

    public void setYrk(String yrk) {
        Yrk = yrk;
    }

    public String getYa() {
        return Ya;
    }

    public void setYa(String ya) {
        Ya = ya;
    }

    public String getYDot() {
        return YDot;
    }

    public void setYDot(String YDot) {
        this.YDot = YDot;
    }
}
