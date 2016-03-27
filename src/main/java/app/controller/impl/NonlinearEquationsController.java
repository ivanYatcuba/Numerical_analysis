package app.controller.impl;

import app.calculations.equation.nonlinear.Result;
import app.calculations.equation.nonlinear.impl.*;
import app.model.ShturmaTable;
import app.util.data.Function;
import app.util.data.Function22;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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


    @FXML
    private TableView<ShturmaTable> tableView;

    @FXML
    private TableColumn<ShturmaTable, String> colX;
    @FXML
    private TableColumn<ShturmaTable, String> colF;
    @FXML
    private TableColumn<ShturmaTable, String> colDf;
    @FXML
    private TableColumn<ShturmaTable, String> colDf2;
    @FXML
    private TableColumn<ShturmaTable, String> colDf3;
    @FXML
    private TableColumn<ShturmaTable, String> colSignChange;

    @FXML
    private TextArea additionalData;

    private static final double EPS = 0.00001;
    private static final double MIN = 1;
    private static final double MAX = 2;

    private  final Function function = new Function22();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcLabel.setText(function.toString());
        functionChart.setLegendVisible(false);

        colX.setCellValueFactory(new PropertyValueFactory<>("x"));
        colF.setCellValueFactory(new PropertyValueFactory<>("fSign"));
        colDf.setCellValueFactory(new PropertyValueFactory<>("dFSign"));
        colDf2.setCellValueFactory(new PropertyValueFactory<>("dF2Sign"));
        colDf3.setCellValueFactory(new PropertyValueFactory<>("dF3Sign"));
        colSignChange.setCellValueFactory(new PropertyValueFactory<>("signChange"));
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

        tableView.getItems().clear();

        final ShturmaTable nInf = new ShturmaTable(Double.NEGATIVE_INFINITY, function);
        final ShturmaTable zero = new ShturmaTable(0, function);
        final ShturmaTable pInf = new ShturmaTable(Double.POSITIVE_INFINITY, function);

        tableView.getItems().add(nInf);
        tableView.getItems().add(zero);
        tableView.getItems().add(pInf);

        final int N = Math.abs(pInf.getSignChange() - nInf.getSignChange());
        final int Nm = Math.abs(zero.getSignChange() - nInf.getSignChange());
        final int Np = Math.abs(pInf.getSignChange() - zero.getSignChange());

        additionalData.appendText("N=" + N + "\n");
        additionalData.appendText("N-=" + Nm + "\n");
        additionalData.appendText("N+=" + Np + "\n");
    }

    private String buildLogAboutResult(AbstractEquationCalculator abstractEquationCalculator) {
        final Result result = abstractEquationCalculator.calculate();
        return abstractEquationCalculator.getName() +"\n" + "x= " + result.getResult() + "\n" + "ітерацій: " + result.getNumberIteration();
    }


}
