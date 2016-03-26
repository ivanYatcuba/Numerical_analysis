package app.controller.impl;

import app.calculations.equation.nonlinear.Result;
import app.calculations.equation.nonlinear.impl.*;
import app.util.data.Function;
import app.util.data.NonlinearFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

@Controller
public class NonlinearEquationsController extends AbstractFxmlController implements Initializable {

    @FXML
    private ListView<String> resultList;

    @FXML
    private Label funcLabel;

    @FXML
    private LineChart<Number, Number> functionChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    private static final double EPS = 0.00001;
    private static final double MIN = 1;
    private static final double MAX = 2;

    private  final Function function = new NonlinearFunction();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcLabel.setText(function.toString());
        functionChart.setLegendVisible(false);
    }

    public void calculate() {
        resultList.getItems().clear();

        final LinkedList<String> resultedLog = new LinkedList<>();

        resultedLog.add(buildLogAboutResult(new HalfDivisionEquationCalculator(EPS, MAX, MIN, function)));
        resultedLog.add(buildLogAboutResult(new SimpleIterationsEquationCalculator(EPS, MAX, MIN, function)));
        resultedLog.add(buildLogAboutResult(new NutonEquationCalculator(EPS, MAX, MIN, function)));
        resultedLog.add(buildLogAboutResult(new ChordEquationCalculator(EPS, MAX, MIN, function)));

        resultList.getItems().addAll(resultedLog);

        final XYChart.Series<Number, Number> series = new XYChart.Series<>();

        functionChart.getData().clear();
        for (float x = -2; x <= 2; x+=0.05f) {
            series.getData().add(new XYChart.Data<>(x, function.f(x)));
        }
        functionChart.getData().add(series);
    }

    private String buildLogAboutResult(AbstractEquationCalculator abstractEquationCalculator) {
        final Result result = abstractEquationCalculator.calculate();
        return abstractEquationCalculator.getName() +"\n" + "x= " + result.getResult() + "\n" + "ітерацій: " + result.getNumberIteration();
    }


}
