package app.service.linearsystem.calculator.generic;

public abstract class GenericLinearSystemCalculator {
    public final int MATRIX_SIZE = 3;


    public abstract String calculate();

    protected double mul(double[][] a, double[] x, int k) {
        double res = 0;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            res += a[k][i] * x[i];
        }
        return res;
    }




    protected String buildResult(String methodName, double[][] matrix_A, double[] matrix_F, double[] x) {
        String result;
        result = methodName + "\n";
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result += "x[" + i + "]=" + round(x[i]) + "\n";
        }
        double[] sig = new double[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            sig[i] = mul(matrix_A, x, i) - matrix_F[i];
        }
        result += "Отклонение:" + "\n";
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result += "sig[" + i + "]=" + round(sig[i]) + "\n";
        }
        return result;
    }


    private String round(double value) {
        return String.format("%.4f", value);
    }
}
