package app.service.linearsystem.calculator;

import app.data.LinearSystem;
import app.service.linearsystem.calculator.generic.GenericLinearSystemCalculator;

import java.util.ArrayList;
import java.util.List;

public class FixedPointIterationCalculator extends GenericLinearSystemCalculator {
    double EPS = 0.00001;

    @Override
    public String calculate() {
        double[][] matrix_A = LinearSystem.FIRST_DIAGONAL_DOMINATION.newInstanceAMatrix();
        double[] matrix_F = LinearSystem.FIRST_DIAGONAL_DOMINATION.newInstanceFMatrix();

        double[][] B = new double[MATRIX_SIZE][MATRIX_SIZE];
        double[] g = new double[MATRIX_SIZE];



        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i == j) {
                    B[i][j] = 0;
                } else {
                    B[i][j] = -(matrix_A[i][j] / matrix_A[i][i]);
                }
            }
            g[i] = matrix_F[i] / matrix_A[i][i];
        }

        List<Double> norm_B = createNormB(B);




        double norm_x;
        double[] previousX = new double[MATRIX_SIZE];
        System.arraycopy(g, 0, previousX, 0, MATRIX_SIZE);

        int iteration = 0;
        do {
            double[] x_k = calculateXk(B, g, previousX);

            norm_x = calculateDifference(previousX, x_k);

            System.arraycopy(x_k, 0, previousX, 0, MATRIX_SIZE);
            iteration++;
        } while ((norm_B.get(0) / (1 - norm_B.get(0)) * norm_x) > EPS);


        return buildResult("Метод простой итерации:", matrix_A, matrix_F, previousX) + "Ітерацій = " + iteration;
    }

    private double calculateDifference(double[] previousX, double[] x_k) {
        double max = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (Math.abs(x_k[i] - previousX[i]) > max) {
                max = Math.abs(x_k[i] - previousX[i]);
            }
        }
        return max;
    }

    private double[] calculateXk(double[][] b, double[] g, double[] x) {
        double[] x_k = new double[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            x_k[i] = mul(b, x, i) + g[i];
        }
        return x_k;
    }

    private List<Double> createNormB(double[][] b) {
        List<Double> norm_B = new ArrayList<>();
        norm_B.add(calc1(b));

        norm_B.add(calc2(b));

        double sum3 = calc3(b);
        if (sum3 < 1) {
            norm_B.add(Math.sqrt(sum3));
        }
        return norm_B;
    }

    private double calc3(double[][] b) {
        double sum3 = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                sum3 += b[i][j] * b[i][j];
            }
        }
        return sum3;
    }

    private double calc2(double[][] b) {
        double max;
        max = 0;
        for (int j = 0; j < MATRIX_SIZE; j++) {
            double sum2 = 0;
            for (int i = 0; i < MATRIX_SIZE; i++) {
                sum2 += Math.abs(b[i][j]);
            }
            if ((sum2 > max) && (sum2 < 1)) {
                max = sum2;

            }
        }
        return max;
    }

    private double calc1(double[][] b) {
        double max;
        max = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            double sum1 = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                sum1 += Math.abs(b[i][j]);
            }
            if ((sum1 > max) && (sum1 < 1))
                max = sum1;

        }
        return max;
    }
}
