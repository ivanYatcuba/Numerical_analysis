package app.calculations.linear.system.calculator.impl;

import app.calculations.linear.system.calculator.MatrixDivisionCalculator;
import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;

public class SquareRootLinearSystemCalculator extends MatrixDivisionCalculator {

    public SquareRootLinearSystemCalculator(LinearSystem linearSystem) {
        super(linearSystem);
    }

    @Override
    protected String getMethodName() {
        return "Метод квадратного кореня";
    }

    @Override
    public SystemSolutionDto calculate() {
        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_B = getLinearSystem().newInstanceFMatrix();

        double[][] matrix_S = new double[EQU_SIZE][EQU_SIZE];
        double[][] matrix_ST = new double[EQU_SIZE][EQU_SIZE];


        matrix_S[0][0] = matrix_A[0][0];
        matrix_S[0][1] = matrix_A[0][1] / matrix_A[0][0];
        matrix_S[0][2] = matrix_A[0][2] / matrix_A[0][0];


        matrix_S[1][1] = Math.sqrt(matrix_A[1][1] - matrix_S[0][1] * matrix_S[0][1]);
        matrix_S[1][2] = (matrix_A[1][2] - matrix_S[0][1]*matrix_S[0][2]) / matrix_S[1][1];

        matrix_S[2][2] = Math.sqrt(matrix_A[2][2] - matrix_S[0][2] * matrix_S[0][2] - matrix_S[1][2] * matrix_S[1][2]);


        setAsTransposeMatrix(matrix_S, matrix_ST);


        double[] y = St_y(matrix_ST, matrix_B);
        double[] x = S_x(matrix_S, y);

        return buildResult(x);
    }

    private void setAsTransposeMatrix(double[][] src, double[][] transResult) {
        for (int i = 0; i < EQU_SIZE; i++) {
            for (int j = 0; j < EQU_SIZE; j++) {
                transResult[i][j] = src[j][i];
            }
        }
    }
}
