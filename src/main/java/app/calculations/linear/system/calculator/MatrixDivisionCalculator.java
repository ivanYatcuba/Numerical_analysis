package app.calculations.linear.system.calculator;

import app.util.data.linear.system.LinearSystem;

public abstract class MatrixDivisionCalculator extends AbstractLinearSystemCalculator {

    public MatrixDivisionCalculator(LinearSystem linearSystem) {
        super(linearSystem);
    }

    protected double[] St_y(double[][] matrix, double[] d) {
        double[] res = new double[equ_size];

        for (int i = 0; i < equ_size; ++i) {
            double sum = 0;
            for (int j = 0; j < equ_size; j++) {
                sum = sum + matrix[i][j] * res[j];
            }
            res[i] = (d[i] - sum) / matrix[i][i];
        }

        return res;
    }

    protected double[] S_x(double[][] mat, double[] d) {
        double[] res = new double[equ_size];

        for (int i = equ_size - 1; i > -1; --i) {
            double sum = 0;
            for (int j = 0; j < equ_size; j++) {
                sum = sum + mat[i][j] * res[j];
            }
            res[i] = (d[i] - sum) / mat[i][i];
        }
        return res;
    }
}
