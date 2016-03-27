package app.data;

public abstract class LinearSystem {
    public static LinearSystem FIRST_SYSTEM = new LinearSystem() {

        @Override
        public double[] newInstanceFMatrix() {
            return new double[]{1, 0, 0.5};
        }

        @Override
        public double[][] newInstanceAMatrix() {
            return new double[][]
                    {{6, -3, 2},
                            {1, 1, -3},
                            {-7, 3, 2}};
        }

        @Override
        public String toString() {
            return "6*x1 - 3*x2 + 2*x3 = 1\n" +
                    "x1 + x2 - 3*x3 = 0\n" +
                    "-7*x1 + 3*x2 + 2*x3 = 0.5";
        }
    };

    /**
     * this is recombine FIRST_SYSTEM
     * NOT FIXME important for fixed-point iteration algorithm
     */
    public static LinearSystem FIRST_DIAGONAL_DOMINATION = new LinearSystem() {

        @Override
        public double[] newInstanceFMatrix() {
            return new double[]{1, 1.5, 1.5};
        }

        @Override
        public double[][] newInstanceAMatrix() {
            return new double[][]
                            {{6,-3, 2},
                            {0, 1, 1},
                            {-1, 0, 4}};
        }

        @Override
        public String toString() {
            return "6*x1 - 3*x2 + 2*x3 = 1\n" +
                    "x1 + x2 - 3*x3 = 0\n" +
                    "-7*x1 + 3*x2 + 2*x3 = 0.5";
        }
    };

    public static LinearSystem SECOND_SYSTEM = new LinearSystem() {

        @Override
        public double[] newInstanceFMatrix() {
            return new double[]{16.0, -16.0, 8.0};
        }

        @Override
        public double[][] newInstanceAMatrix() {
            return new double[][]
                    {{1.0, 3.0, -4.0},
                            {3.0, 13.0, -18.0},
                            {-4.0, -18.0, 29.0}};
        }

        @Override
        public String toString() {
            return "x1 + 3*x2 - 4*x3 = 16\n" +
                    "3*x1 + 13*x2 - 18*x3 = -16\n" +
                    "-4*x1 - 18*x2 + 29*x3 = 8\n";
        }
    };


    public abstract double[] newInstanceFMatrix();

    public abstract double[][] newInstanceAMatrix();

    public abstract String toString();
}
