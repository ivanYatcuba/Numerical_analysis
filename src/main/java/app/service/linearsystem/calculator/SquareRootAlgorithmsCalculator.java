package app.service.linearsystem.calculator;

import app.data.LinearSystem;
import app.service.linearsystem.calculator.generic.HaveSxSyGenericCalculator;

public class SquareRootAlgorithmsCalculator extends HaveSxSyGenericCalculator {
    @Override
    public String calculate() {
        double[][] matrix_A = LinearSystem.SECOND_SYSTEM.newInstanceAMatrix();
        double[] matrix_B = LinearSystem.SECOND_SYSTEM.newInstanceFMatrix();

        double[][] matrix_S = new double[MATRIX_SIZE][MATRIX_SIZE];
        double[][] matrix_ST = new double[MATRIX_SIZE][MATRIX_SIZE];


        matrix_S[0][0] = matrix_A[0][0];
        matrix_S[0][1] = matrix_A[0][1] / matrix_A[0][0];
        matrix_S[0][2] = matrix_A[0][2] / matrix_A[0][0];


        matrix_S[1][1] = Math.sqrt(matrix_A[1][1] - matrix_S[0][1] * matrix_S[0][1]);
        matrix_S[1][2] = (matrix_A[1][2] - matrix_S[0][1]*matrix_S[0][2]) / matrix_S[1][1];

        matrix_S[2][2] = Math.sqrt(matrix_A[2][2] - matrix_S[0][2] * matrix_S[0][2] - matrix_S[1][2] * matrix_S[1][2]);


        setAsTransposeMatrix(matrix_S, matrix_ST);


        double[] y = St_y(matrix_ST, matrix_B);
        double[] x = S_x(matrix_S, y);

        // вывод решений
        return buildResult("Метод квадратного корня:", matrix_A, matrix_B, x);
    }

    private void setAsTransposeMatrix(double[][] src, double[][] transResult) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                transResult[i][j] = src[j][i];
            }
        }
    }
}
