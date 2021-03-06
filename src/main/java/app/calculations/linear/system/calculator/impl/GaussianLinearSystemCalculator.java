package app.calculations.linear.system.calculator.impl;

import app.calculations.linear.system.calculator.AbstractLinearSystemCalculator;
import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;

public class GaussianLinearSystemCalculator extends AbstractLinearSystemCalculator {

    public GaussianLinearSystemCalculator(LinearSystem linearSystem) {
        super(linearSystem);
    }

    public GaussianLinearSystemCalculator(LinearSystem linearSystem, int equ_size) {
        super(linearSystem, equ_size);
    }


    @Override
    protected String getMethodName() {
        return "Метод Гаусса";
    }

    @Override
    public SystemSolutionDto calculate() {

        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_F = getLinearSystem().newInstanceFMatrix();

        goForward(matrix_A, matrix_F);

        double[] x = goBackward(matrix_A, matrix_F);

        return buildResult(x);
    }

    public double[] calculateRaw() {

        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_F = getLinearSystem().newInstanceFMatrix();

        goForward(matrix_A, matrix_F);

        return goBackward(matrix_A, matrix_F);
    }

    private void goForward(double[][] matrixA, double[] matrixF) {

        for (int k = 0, j; k < equ_size - 1; k++) {
            int im = getMaxValueRow(matrixA, k);

            if (im != k) {
                swapTwoCollumn(matrixA, matrixF, k, im);
            }
            for (int i = k + 1; i < equ_size; i++) {
                double dividedCoef = 1.0 * matrixA[i][k] / matrixA[k][k];
                matrixA[i][k] = 0;
                matrixF[i] = matrixF[i] - dividedCoef * matrixF[k];
                if (dividedCoef != 0)
                    for (j = k + 1; j < equ_size; j++) {
                        matrixA[i][j] = matrixA[i][j] - dividedCoef * matrixA[k][j];
                    }
            }
        }
    }
    private double[] goBackward(double[][] matrixA, double[] matrixB) {
        double[] x = new double[equ_size];
        x[equ_size - 1] = 1.0 * matrixB[equ_size - 1] / matrixA[equ_size - 1][equ_size - 1];
        for (int i = equ_size - 2, j; 0 <= i; i--) {
            double s = 0;
            for (j = i + 1; j < equ_size; j++) {
                s = s + matrixA[i][j] * x[j];
            }
            x[i] = 1.0 * (matrixB[i] - s) / matrixA[i][i];
        }

        return x;
    }

    private void swapTwoCollumn(double[][] matrixA, double[] matrixF, int i1, int i2) {
        int j;
        for (j = 0; j < equ_size; j++) {
            swap(matrixA, i1, i2, j);
        }
        swap(matrixF, i1, i2);
    }


    private void swap(double[][] matrix, int i1, int i2, int j) {
        double tmp = matrix[i2][j];
        matrix[i2][j] = matrix[i1][j];
        matrix[i1][j] = tmp;
    }

    private void swap(double[] matrix, int i1, int i2) {
        double tmp = matrix[i2];
        matrix[i2] = matrix[i1];
        matrix[i1] = tmp;
    }

    private int getMaxValueRow(double[][] matrix, int coll) {
        int im = coll;
        for (int i = coll + 1; i < equ_size; i++) {
            if (Math.abs(matrix[im][coll]) < Math.abs(matrix[i][coll])) {
                im = i;
            }
        }
        return im;
    }

}
