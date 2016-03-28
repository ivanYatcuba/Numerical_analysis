package app.calculations.linear.system.calculator.impl;

import app.calculations.linear.system.calculator.MatrixDivisionCalculator;
import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;

public class CholeskyLinearSystemCalculator extends MatrixDivisionCalculator {

    public CholeskyLinearSystemCalculator(LinearSystem linearSystem) {
        super(linearSystem);
    }

    @Override
    protected String getMethodName() {
        return "Метод Холецького";
    }

    @Override
    public SystemSolutionDto calculate() {
        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_F = getLinearSystem().newInstanceFMatrix();

        BC bc = buildBonCMatrix(matrix_A);
        double[][] B = bc.getB();
        double[][] C = bc.getC();

        double[] y = St_y(B, matrix_F);
        double[] x = S_x(C, y);

        return buildResult(x);
    }


    public BC buildBonCMatrix(double[][] matrix_a) {
        double[][] b = new double[equ_size][equ_size];
        double[][] c = new double[equ_size][equ_size];

        for (int i = 0; i < equ_size; i++) {
            b[i][0] = matrix_a[i][0];
            c[0][i] = matrix_a[0][i] / b[0][0];
            c[i][i] = 1;
        }

        for (int i = 1; i < equ_size; i++) {
            for (int j = 1; j < equ_size; j++) {
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
