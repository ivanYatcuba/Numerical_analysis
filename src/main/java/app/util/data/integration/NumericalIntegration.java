package app.util.data.integration;

public class NumericalIntegration {
    public static double A = 0, B = 1;
    public static int N = 10;

    //Подынтегральная функция
    public static double F(double x) {
        return Math.exp(x) + Math.pow(x, 2) - 1;
    }


    public static String asString() {
        return "y = e^x + x^2 - 1";
    }
}
