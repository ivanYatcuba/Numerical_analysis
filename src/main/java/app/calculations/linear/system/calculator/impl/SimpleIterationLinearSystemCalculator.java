package app.calculations.linear.system.calculator.impl;

import app.calculations.linear.system.calculator.AbstractLinearSystemCalculator;
import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;

import java.util.ArrayList;
import java.util.List;

public class SimpleIterationLinearSystemCalculator extends AbstractLinearSystemCalculator {

    double EPS = 0.00001;

    public SimpleIterationLinearSystemCalculator(LinearSystem linearSystem) {
        super(linearSystem);
    }

    @Override
    protected String getMethodName() {
        return "Метод простої ітерації";
    }

    @Override
    public SystemSolutionDto calculate() {

        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_F = getLinearSystem().newInstanceFMatrix();

        double[][] B = new double[EQU_SIZE][EQU_SIZE];
        double[] g = new double[EQU_SIZE];

        for (int i = 0; i < EQU_SIZE; i++) {
            for (int j = 0; j < EQU_SIZE; j++) {
                if (i == j) {
                    B[i][j] = 0;
                } else {
                    B[i][j] = -(matrix_A[i][j] / matrix_A[i][i]);
                }
            }
            g[i] = matrix_F[i] / matrix_A[i][i];
        }

        final List<Double> canonicalB = createCanonicalB(B);

        double norm_x;
        double[] previousX = new double[EQU_SIZE];
        System.arraycopy(g, 0, previousX, 0, EQU_SIZE);

        int iteration = 0;
        do {
            double[] x_k = calculateXk(B, g, previousX);

            norm_x = calculateDifference(previousX, x_k);

            System.arraycopy(x_k, 0, previousX, 0, EQU_SIZE);
            iteration++;
        } while ((canonicalB.get(0) / (1 - canonicalB.get(0)) * norm_x) > EPS);

        final SystemSolutionDto solutionDto = buildResult(previousX);
        solutionDto.setIterations(String.valueOf(iteration));
        return solutionDto;
    }

    private double calculateDifference(double[] previousX, double[] x_k) {
        double max = 0;
        for (int i = 0; i < EQU_SIZE; i++) {
            if (Math.abs(x_k[i] - previousX[i]) > max) {
                max = Math.abs(x_k[i] - previousX[i]);
            }
        }
        return max;
    }

    private double[] calculateXk(double[][] b, double[] g, double[] x) {
        double[] x_k = new double[EQU_SIZE];
        for (int i = 0; i < EQU_SIZE; i++) {
            x_k[i] = multiply(b, x, i) + g[i];
        }
        return x_k;
    }

    private List<Double> createCanonicalB(double[][] b) {
        List<Double> norm_B = new ArrayList<>();
        norm_B.add(B1(b));

        norm_B.add(B2(b));

        double sum3 = B3(b);
        if (sum3 < 1) {
            norm_B.add(Math.sqrt(sum3));
        }
        return norm_B;
    }

    private double B3(double[][] b) {
        double sum3 = 0;
        for (int i = 0; i < EQU_SIZE; i++) {
            for (int j = 0; j < EQU_SIZE; j++) {
                sum3 += b[i][j] * b[i][j];
            }
        }
        return sum3;
    }

    private double B2(double[][] b) {
        double max;
        max = 0;
        for (int j = 0; j < EQU_SIZE; j++) {
            double sum2 = 0;
            for (int i = 0; i < EQU_SIZE; i++) {
                sum2 += Math.abs(b[i][j]);
            }
            if ((sum2 > max) && (sum2 < 1)) {
                max = sum2;

            }
        }
        return max;
    }

    private double B1(double[][] b) {
        double max;
        max = 0;
        for (int i = 0; i < EQU_SIZE; i++) {
            double sum1 = 0;
            for (int j = 0; j < EQU_SIZE; j++) {
                sum1 += Math.abs(b[i][j]);
            }
            if ((sum1 > max) && (sum1 < 1))
                max = sum1;

        }
        return max;
    }
}
