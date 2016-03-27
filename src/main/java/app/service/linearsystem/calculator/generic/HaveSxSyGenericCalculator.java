package app.service.linearsystem.calculator.generic;

public abstract class HaveSxSyGenericCalculator extends GenericLinearSystemCalculator {
    protected double[] St_y(double[][] matrix, double[] d) {
        double[] res = new double[MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; ++i) {
            double sum = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                sum = sum + matrix[i][j] * res[j];
            }
            res[i] = (d[i] - sum) / matrix[i][i];
        }

        return res;
    }

    protected double[] S_x(double[][] mat, double[] d) {
        double[] res = new double[MATRIX_SIZE];

        for (int i = MATRIX_SIZE - 1; i > -1; --i) {
            double sum = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                sum = sum + mat[i][j] * res[j];
            }
            res[i] = (d[i] - sum) / mat[i][i];
        }
        return res;
    }
}
