package app.util.data.linear.system.impl;

import app.util.data.linear.system.LinearSystem;

public class SecondSystem implements LinearSystem {

    @Override
    public double[] newInstanceFMatrix() {
        return new double[]{12, -24, 18};
    }

    @Override
    public double[][] newInstanceAMatrix() {
        return new double[][]
                        { { 1, -5, -6 },
                        {  -5, 29, 20 },
                        {  -6, 20, 70  } };
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public String toString() {
        return " x1 - 5*x2 - 6*x3 = 12\n" +
                "-5*x1 + 29*x2 + 20*x3 = -24\n" +
                "-6*x1 + 20*x2 + 70*x3 = 18\n";
    }

}
