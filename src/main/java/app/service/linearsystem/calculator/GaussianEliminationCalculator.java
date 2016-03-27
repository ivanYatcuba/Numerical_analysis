package app.service.linearsystem.calculator;

import app.data.LinearSystem;
import app.service.linearsystem.calculator.generic.GenericLinearSystemCalculator;

public class GaussianEliminationCalculator extends GenericLinearSystemCalculator {
    int gaussianMatrixSize = 3;
    public String calculate() {
        double[][] matrix_A = LinearSystem.FIRST_SYSTEM.newInstanceAMatrix();
        double[] matrix_F = LinearSystem.FIRST_SYSTEM.newInstanceFMatrix();


        rightCourse(matrix_A, matrix_F);
        double[] x = inverseCourse(matrix_A, matrix_F);


        // вывод решений
        return buildResult("Метод Гаусса:", matrix_A, matrix_F, x);
    }


    public double[] krivoCalculate(double[][] matrix_A, double[] matrix_F, int matrixSize) {
        gaussianMatrixSize = matrixSize;
        rightCourse(matrix_A, matrix_F);
        return inverseCourse(matrix_A, matrix_F);
    }



    private void rightCourse(double[][] matrixA, double[] matrixF) {

        for (int k = 0, j; k < gaussianMatrixSize - 1; k++) {
            int im = getMaxValueRow(matrixA, k);

            if (im != k) {
                swapTwoCollumn(matrixA, matrixF, k, im);
            }
            for (int i = k + 1; i < gaussianMatrixSize; i++) {
                double dividedCoef = 1.0 * matrixA[i][k] / matrixA[k][k];
                matrixA[i][k] = 0;
                matrixF[i] = matrixF[i] - dividedCoef * matrixF[k];
                if (dividedCoef != 0)
                    for (j = k + 1; j < gaussianMatrixSize; j++) {
                        matrixA[i][j] = matrixA[i][j] - dividedCoef * matrixA[k][j];
                    }
            }
        }
    }
    private double[] inverseCourse(double[][] matrixA, double[] matrixB) {
        double[] x = new double[gaussianMatrixSize];
        x[gaussianMatrixSize - 1] = 1.0 * matrixB[gaussianMatrixSize - 1] / matrixA[gaussianMatrixSize - 1][gaussianMatrixSize - 1];
        for (int i = gaussianMatrixSize - 2, j; 0 <= i; i--) {
            double s = 0;
            for (j = i + 1; j < gaussianMatrixSize; j++) {
                s = s + matrixA[i][j] * x[j];
            }
            x[i] = 1.0 * (matrixB[i] - s) / matrixA[i][i];
        }

        return x;
    }





    private void swapTwoCollumn(double[][] matrixA, double[] matrixF, int i1, int i2) {
        int j;
        for (j = 0; j < gaussianMatrixSize; j++) {
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
        for (int i = coll + 1; i < gaussianMatrixSize; i++) {
            if (Math.abs(matrix[im][coll]) < Math.abs(matrix[i][coll])) {
                im = i;
            }
        }
        return im;
    }

}
