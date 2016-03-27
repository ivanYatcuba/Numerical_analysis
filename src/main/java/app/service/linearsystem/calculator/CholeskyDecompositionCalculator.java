package app.service.linearsystem.calculator;

import app.data.LinearSystem;
import app.service.linearsystem.calculator.generic.HaveSxSyGenericCalculator;

public class CholeskyDecompositionCalculator extends HaveSxSyGenericCalculator {
    @Override
    public String calculate() {
        double[][] matrix_A = LinearSystem.FIRST_SYSTEM.newInstanceAMatrix();
        double[] matrix_F = LinearSystem.FIRST_SYSTEM.newInstanceFMatrix();

        BC bc = createBandCMatrix(matrix_A);
        double[][] B = bc.getB();
        double[][] C = bc.getC();

        double[] y = St_y(B, matrix_F);
        double[] x = S_x(C, y);

        return buildResult("Метод Холецкого:", matrix_A, matrix_F, x);
    }


    public BC createBandCMatrix(double[][] matrix_a) {
        double[][] b = new double[MATRIX_SIZE][MATRIX_SIZE];
        double[][] c = new double[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            b[i][0] = matrix_a[i][0];
            c[0][i] = matrix_a[0][i] / b[0][0];
            c[i][i] = 1;
        }

        for (int i = 1; i < MATRIX_SIZE; i++) {
            for (int j = 1; j < MATRIX_SIZE; j++) {
                double sum = 0;
                if (i >= j) {
                    for (int k = 0; k < j; k++) {
                        sum += b[i][k] * c[k][j];
                    }
                    b[i][j] = matrix_a[i][j] - sum;

                }
                if (i < j) {
                    for (int k = 0; k < i; k++) {
                        sum += b[i][k] * c[k][j];
                    }
                    b[i][j] = 0;
                    c[i][j] = (1 / b[i][i]) * (matrix_a[i][j] - sum);
                }
            }
        }
        return new BC(b, c);
    }


    private static class BC {
        public final double[][] b;
        public final double[][] c;

        private BC(double[][] b, double[][] c) {
            this.b = b;
            this.c = c;
        }

        public double[][] getB() {
            return b;
        }

        public double[][] getC() {
            return c;
        }
    }
}
