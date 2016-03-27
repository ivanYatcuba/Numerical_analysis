package app.util.data.linear.system.impl;

import app.util.data.linear.system.LinearSystem;

public class FirstSystem implements LinearSystem {

    @Override
    public double[] newInstanceFMatrix() {
        return new double[]{-1, 1, 1};
    }

    @Override
    public double[][] newInstanceAMatrix() {
        return new double[][]
                        { { 1,   2,  -3 },
                        {   3,  -2,  -1 },
                        {   1,   4,  -2 } };
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String toString() {
        return  "x1 + 2*x2 - 3*x3 = -1\n" +
                "3*x1 - 2*x2 - 1*x3 = 1\n" +
                "x1 + 4*x2 - 2*x3 = 1";
    }

}
