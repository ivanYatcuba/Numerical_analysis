package app.model;

import app.util.data.nonlinear.Function;

import java.util.LinkedList;
import java.util.List;

public class ShturmaTable {

    private static final String PLUS = "+";
    private static final String MINUS = "-";

    private final double x;
    private final Function function;

    public ShturmaTable(double x, Function function) {
        this.x = x;
        this.function = function;
    }

    public int getSignChange() {
        final List<String> signs = new LinkedList<>();

        signs.add(0, getSign(function.f(x)));
        signs.add(1, getSign(function.dF(x)));
        signs.add(2, getSign(function.dF2(x)));
        signs.add(3, getSign(function.dF3(x)));

        int signsChange = 0;

        for (int i=1; i<signs.size(); i++) {
            if(!signs.get(i-1).equals(signs.get(i))) {
                signsChange++;
            }
        }

        return signsChange;
    }

    public double getX() {
        return x;
    }

    public String getFSign() {
        return getSign(function.f(x));
    }

    public String getDFSign() {
        return getSign(function.dF(x));
    }

    public String getDF2Sign() {
        return getSign(function.dF2(x));
    }

    public String getDF3Sign() {
        return getSign(function.dF3(x));
    }

    private String getSign(double val) {
        if(val < 0) {
            return MINUS;
        }
        return PLUS;
    }

}
