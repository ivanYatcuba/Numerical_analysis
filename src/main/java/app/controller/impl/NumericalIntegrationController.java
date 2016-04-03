package app.controller.impl;

import app.calculations.integral.IntegralCalculator;
import app.calculations.integral.impl.*;
import app.util.data.integration.NumericalIntegration;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class NumericalIntegrationController extends AbstractFxmlController implements Initializable {

    public ListView<String> lv;
    public AreaChart<Number, Number> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void calculate() {

        double a = NumericalIntegration.A;
        double b = NumericalIntegration.B;
        int n = NumericalIntegration.N;

        chart.getData().clear();

        final List<IntegralCalculator> integralCalculators = new LinkedList<>();
        integralCalculators.add(new RectangleLeft());
        integralCalculators.add(new RectangleMiddle());
        integralCalculators.add(new RectangleRight());
        integralCalculators.add(new GaussianCalculator());
        integralCalculators.add(new SimpsonCalculator());
        integralCalculators.add(new TrapezoidsCalculator());

        lv.getItems().clear();

        for (IntegralCalculator integralCalculator: integralCalculators) {
            lv.getItems().add(integralCalculator.methodName() + ": \t" + integralCalculator.calculate(a, b, n));
        }

        drawGraph(a, b, n);
    }


    private void drawGraph(double a, double b, int n) {
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
