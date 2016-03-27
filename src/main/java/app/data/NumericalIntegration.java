package app.data;

public class NumericalIntegration {
    public static double A = -0.5, B = 0.5;
    public static int N = 10;

    //Подынтегральная функция
    public static double F(double x) {
        return 3*x*x + Math.tan(x);
    }


    public static String asString() {
        return "y = 3*x^2 + tg(x)";
    }
}
