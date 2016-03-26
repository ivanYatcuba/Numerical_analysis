package app.controller.impl;

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
        double a = 1, b = 2;
        int n = 10;
        chart.getData().clear();

        String s1 = "Метод прямоугольников: \n"
                + "Левых: \n"
                + service.CalcRectangleLeft(a, b, n)
                + "\nПравых: \n"
                + service.CalcRectangleRight(a, b, n)
                + "\nСредних: \n"
                + service.CalcRectangleMiddle(a, b, n);


        String s2 = "Метод Гаусса: \n"
                + service.CalcGauss(a, b, n);

        String s3 = "Метод Симпсона: \n"
                + service.CalcSimpson(a, b, n);

        String s4 = "Метод трапеций: \n"
                + service.CalcTrapezoidal(a, b, n);

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


        //chart1.Series["series1"].Points.AddXY(x[0], 0);
        //chart1.Series["series2"].Points.AddXY(x[n], 0);
        //chart1.Series["series1"].Points.AddXY(x[0], F(x[0]));
        //chart1.Series["series2"].Points.AddXY(x[n], F(x[n]));
        XYChart.Series<Number, Number> series0 = new XYChart.Series<>();
        series0.setName("function y=xxcxxc");
        for (int i = 0; i < n + 1; i++) {
            series0.getData().add(new XYChart.Data<>(x[i], service.F(x[i])));

        }

        chart.getData().add(series0);

    }
}
