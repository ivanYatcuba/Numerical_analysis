package app.service;

import java.util.ArrayList;
import java.util.List;

public class LinearSystemService {
    int n = 3;

    public String Gauss() {
        double[][] matrix_A = {{3, 1, -1},
                {1, 1, -4},
                {-2, 4, -1}};
        double[] matrix_F = {2, -6, 0};
        double[] x = new double[n];
        PryamoiHod(n, matrix_A, matrix_F);
        ObratniHod(n, matrix_A, matrix_F, x);


        // вывод решений
        return buildResult("Метод Гаусса:", matrix_A, matrix_F, x);
    }

    private String buildResult(String methodName, double[][] matrix_A, double[] matrix_F, double[] x) {
        String result;
        result = methodName + "\n";
        for (int i = 0; i < n; i++) {
            result += "x[" + i + "]=" + x[i] + "\n";
        }
        double[] sig = new double[n];
        for (int i = 0; i < n; i++) {
            sig[i] = mul(matrix_A, x, i) - matrix_F[i];
        }
        result += "Отклонение:" + "\n";
        for (int i = 0; i < n; i++) {
            result += "sig[" + i + "]=" + sig[i] + "\n";
        }
        return result;
    }


    public String Kv_korn() {
        double[] x;
        double[][] matrix_A = {{1.0, 3.0, -3.0},
                {3.0, 10.0, -11.0},
                {-3.0, -11.0, 22.0}};
        double[] matrix_B = {11.0, -2.0, 10.0};
        double[][] matrix_S = new double[n][n];
        double[][] matrix_ST = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_S[i][j] = 0;
                matrix_ST[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix_A[i], i, matrix_S[i], i, n - i);
        }

        for (int i = 0; i < n; i++) {
            double buf_l = 0;
            for (int l = 0; l < i; ++l)
                buf_l += matrix_S[l][i] * matrix_S[l][i];
            matrix_S[i][i] = Math.sqrt(matrix_A[i][i] - buf_l);
            //for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (j > i) {
                    double buf_l2 = 0;
                    for (int l = 0; l < i; ++l)
                        buf_l2 = matrix_S[l][i] * matrix_S[l][j];
                    matrix_S[i][j] = (matrix_A[i][j] - buf_l2); // matrix_S[i][ i];
                }

                if (i > j)
                    matrix_S[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_ST[i][j] = matrix_S[j][i];
            }
        }


        double[] y = St_y(matrix_ST, matrix_B);
        x = S_x(matrix_S, y);

        // вывод решений
        return buildResult("Метод квадратного корня:", matrix_A, matrix_B, x);
    }

    public String Holetskiy() {
        double[][] matrix_A = {{3, 1, -1},
                {1, 1, -4},
                {-2, 4, -1}};
        double[] matrix_F = {2, -6, 0};
        double[] x;
        double[][] B = new double[n][n];
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            B[i][0] = matrix_A[i][0];
            C[0][i] = matrix_A[0][i] / B[0][0];
            C[i][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                double sum = 0;
                if (i >= j) {
                    for (int k = 0; k < j; k++) {
                        sum += B[i][k] * C[k][j];
                    }
                    B[i][j] = matrix_A[i][j] - sum;

                }
                if (i < j) {
                    for (int k = 0; k < i; k++) {
                        sum += B[i][k] * C[k][j];
                    }
                    B[i][j] = 0;
                    C[i][j] = (1 / B[i][i]) * (matrix_A[i][j] - sum);
                }
            }
        }

        double[] y = St_y(B, matrix_F);
        x = S_x(C, y);
        // вывод решений
        return buildResult("Метод Холецкого:", matrix_A, matrix_F, x);
    }

    public String Iter() {
        double[][] matrix_A = {{3, 1, -1},
                {-2, 4, -1},
                {1, 1, -4},};
        double[] matrix_F = {2, 0, -6};
        double[][] B = new double[n][n];
        double[] x = new double[n];
        double[] x_k = new double[n];
        double[] g = new double[n];
        List<Double> norm_B = new ArrayList<>();
        double eps = 0.00001;
        double sum;
        double max;
        int k = 0;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    B[i][j] = 0;
                } else {
                    B[i][j] = -(matrix_A[i][j] / matrix_A[i][i]);
                }
            }
            g[i] = matrix_F[i] / matrix_A[i][i];
        }

        max = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(B[i][j]);
            }
            if ((sum > max) && (sum < 1))
                max = sum;

        }
        norm_B.add(max);
        max = 0;
        for (int j = 0; j < n; j++) {
            sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.abs(B[i][j]);
            }
            if ((sum > max) && (sum < 1)) {
                max = sum;

            }
        }
        norm_B.add(max);

        sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += B[i][j] * B[i][j];
            }
        }
        if (sum < 1)
            norm_B.add(Math.sqrt(sum));

        System.arraycopy(g, 0, x, 0, n);

        double norm_x = 0;


        do {
            for (int i = 0; i < n; i++) {
                x_k[i] = mul(B, x, i) + g[i];
            }
            max = 0;
            for (int i = 0; i < n; i++) {
                if (Math.abs(x_k[i] - x[i]) > max)
                    max = Math.abs(x_k[i] - x[i]);
            }
            norm_x = max;
            System.arraycopy(x_k, 0, x, 0, n);
            k++;
        } while ((norm_B.get(0) / (1 - norm_B.get(0)) * norm_x) > eps);

        // вывод решений
        return buildResult("Метод простой итерации:", matrix_A, matrix_F, x);
    }













































    private void PryamoiHod(int n, double[][] a, double[] b) {
        double v;
        for (int k = 0, i, j, im; k < n - 1; k++) {
            im = k;
            for (i = k + 1; i < n; i++) {
                if (Math.abs(a[im][k]) < Math.abs(a[i][k])) {
                    im = i;
                }
            }
            if (im != k) {
                for (j = 0; j < n; j++) {
                    v = a[im][j];
                    a[im][j] = a[k][j];
                    a[k][j] = v;
                }
                v = b[im];
                b[im] = b[k];
                b[k] = v;
            }
            for (i = k + 1; i < n; i++) {
                v = 1.0 * a[i][k] / a[k][k];
                a[i][k] = 0;
                b[i] = b[i] - v * b[k];
                if (v != 0)
                    for (j = k + 1; j < n; j++) {
                        a[i][j] = a[i][j] - v * a[k][j];
                    }
            }
        }
    }

    private void ObratniHod(int n, double[][] a, double[] b, double[] x) {
        x[n - 1] = 1.0 * b[n - 1] / a[n - 1][n - 1];
        for (int i = n - 2, j; 0 <= i; i--) {
            double s = 0;
            for (j = i + 1; j < n; j++) {
                s = s + a[i][j] * x[j];
            }
            x[i] = 1.0 * (b[i] - s) / a[i][i];
        }
    }

    private double[] St_y(double[][] mat, double[] d) {
        double[] res = new double[n]; // решение
        double sum;
        int j;

        for (int i = 0; i < n; i++) {
            res[i] = 0;
        }
        for (int z = 0; z < n; ++z) {
            sum = 0;
            for (j = 0; j < n; j++) {
                sum = sum + mat[z][j] * res[j];
            }
            res[z] = (d[z] - sum) / mat[z][z];
        }
        return res;
    }

    private double[] S_x(double[][] mat, double[] d) {
        double[] res = new double[n]; // решение
        double sum;
        int j;

        for (int i = 0; i < n; i++) {
            res[i] = 0;
        }
        for (int z = n - 1; z > -1; --z) {
            sum = 0;
            for (j = 0; j < n; j++) {
                sum = sum + mat[z][j] * res[j];
            }
            res[z] = (d[z] - sum) / mat[z][z];
        }
        return res;
    }

    private double mul(double[][] a, double[] x, int k) {
        double res = 0;

        for (int i = 0; i < n; i++) {
            double tmp = a[k][i];
            res += tmp * x[i];
        }
        return res;
    }
}
