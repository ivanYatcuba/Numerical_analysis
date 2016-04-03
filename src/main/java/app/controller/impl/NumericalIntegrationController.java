package app.controller.impl;

import app.data.NumericalIntegration;
import app.service.NumericalIntegrationService;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class NumericalIntegrationController extends AbstractFxmlController implements Initializable {

    public ListView<String> lv;
    public AreaChart<Number, Number> chart;

    NumericalIntegrationService service = new NumericalIntegrationService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void calculate() {
        double a = NumericalIntegration.A;
        double b = NumericalIntegration.B;
        int n = NumericalIntegration.N;

        chart.getData().clear();

        String s1 = "Метод прямоугольников: \n"
                + "Левых: \n"
                + str(service.calculateRectangleLeft(a, b, n))
                + "\nПравых: \n"
                + str(service.calculateRectangleRight(a, b, n))
                + "\nСредних: \n"
                + str(service.calculateRectangleMiddle(a, b, n));


        String s2 = "Метод Гаусса: \n"
                + str(service.calculateGaussian(a, b, n));

        String s3 = "Метод Симпсона: \n"
                + str(service.calculateSimpson(a, b, n));

        String s4 = "Метод трапеций: \n"
                + str(service.calculateTrapezoidal(a, b, n));

        lv.getItems().clear();
        lv.getItems().addAll(s1, s2, s3, s4);

        DrawGraph(a, b, n);
    }


    private void DrawGraph(double a, double b, int n) {
        double h;
        double[] x = new double[n + 1];


        h = (b - a) / n;
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }


        XYChart.Series<Number, Number> series0 = new XYChart.Series<>();
        series0.setName("function " + NumericalIntegration.asString());
        for (int i = 0; i < n + 1; i++) {
            series0.getData().add(new XYChart.Data<>(x[i], NumericalIntegration.F(x[i])));

        }

        chart.getData().add(series0);

    }

    private String str(double x) {
        return String.format("%.4f", x);
    }
}
