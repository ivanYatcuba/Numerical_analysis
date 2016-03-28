package app.calculations.linear.system.calculator;

import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;

public abstract class AbstractLinearSystemCalculator implements LinearSystemCalculator {

    public int equ_size;

    private LinearSystem linearSystem;

    public AbstractLinearSystemCalculator(LinearSystem linearSystem, int equ_size) {
        this.linearSystem = linearSystem;
        this.equ_size = equ_size;
    }

    public AbstractLinearSystemCalculator(LinearSystem linearSystem) {
        this.linearSystem = linearSystem;
        this.equ_size = 3;
    }

    public LinearSystem getLinearSystem() {
        return linearSystem;
    }

    protected abstract String getMethodName();

    protected double multiply(double[][] a, double[] x, int k) {
        double res = 0;

        for (int i = 0; i < equ_size; i++) {
            res += a[k][i] * x[i];
        }
        return res;
    }


    protected SystemSolutionDto buildResult(double[] x) {
        double[][] matrix_A = getLinearSystem().newInstanceAMatrix();
        double[] matrix_F = getLinearSystem().newInstanceFMatrix();

        double[] sig = new double[equ_size];
        for (int i = 0; i < equ_size; i++) {
            sig[i] = multiply(matrix_A, x, i) - matrix_F[i];
        }

        final SystemSolutionDto solutionDto = new SystemSolutionDto();

        solutionDto.setFunction(getMethodName());

        solutionDto.setX1(round(x[0]));
        solutionDto.setX2(round(x[1]));
        solutionDto.setX3(round(x[2]));

        solutionDto.setSigma1(sig[0]);
        solutionDto.setSigma2(sig[1]);
        solutionDto.setSigma3(sig[2]);

        solutionDto.setFunctionNumber(getLinearSystem().getId());

        return solutionDto;
    }


    private Float round(double value) {
        return Float.parseFloat(String.format("%.4f", value).replace(",", "."));
    }
}
