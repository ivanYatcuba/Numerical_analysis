package app.controller.impl;

import app.calculations.polynomial.AbstractLeastSquare;
import app.calculations.polynomial.AbstractPolinomialCalculator;
import app.calculations.polynomial.impl.*;
import app.dto.PolinomialAnswer;
import app.model.PolinomialTable;
import app.util.data.polinomial.PolinomialData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class PolinomialController extends AbstractFxmlController implements Initializable {

    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    private TableView<PolinomialTable> tableView;

    @FXML
    private TableColumn<PolinomialTable, String> colPlinomName;
    @FXML
    private TableColumn<PolinomialTable, String> colX1;
    @FXML
    private TableColumn<PolinomialTable, String> colX2;
    @FXML
    private TableColumn<PolinomialTable, String> colX3;
    @FXML
    private TableColumn<PolinomialTable, String> colX4;
    @FXML
    private TableColumn<PolinomialTable, String> colX5;
    @FXML
    private TableColumn<PolinomialTable, String> colSigma;

    private PolinomialData polinomialData = new PolinomialData(new double[]{1, 3, 4, 5},
                                                               new double[]{-1, 2, 0, 1});

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPlinomName.setCellValueFactory(new PropertyValueFactory<>("polinomialName"));
        colX1.setCellValueFactory(new PropertyValueFactory<>("x1"));
        colX2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        colX3.setCellValueFactory(new PropertyValueFactory<>("x3"));
        colX4.setCellValueFactory(new PropertyValueFactory<>("x4"));
        colX5.setCellValueFactory(new PropertyValueFactory<>("x5"));
        colSigma.setCellValueFactory(new PropertyValueFactory<>("sigma"));
    }



    public void calculate() {
        tableView.getItems().clear();
        chart.getData().clear();

        double xi[] = polinomialData.getXi();
        colX1.setText(String.valueOf(xi[0]));
        colX2.setText(String.valueOf(xi[1]));
        colX3.setText(String.valueOf(xi[2]));
        colX4.setText(String.valueOf(xi[3]));
        colX5.setText(String.valueOf(xi[4]));

        final XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("y");
        for (int i = 0; i < PolinomialData.DATA_SIZE; i++) {
            series1.getData().add(new XYChart.Data<>(polinomialData.getX()[i], polinomialData.getY()[i]));
        }
        chart.getData().add(series1);

        final List<TableChartData> data = new LinkedList<>();
        data.add(getPolinomialTable(new LagrangePolynomial(polinomialData)));
        data.add(getPolinomialTable(new NutonPolinomial(polinomialData)));
        data.add(getPolinomialTable(new LeastSquaresQ0(polinomialData)));
        data.add(getPolinomialTable(new LeastSquaresQ1(polinomialData)));
        data.add(getPolinomialTable(new LeastSquaresQ2(polinomialData)));
        data.add(getPolinomialTable(new LeastSquaresQ3(polinomialData)));

        for (TableChartData item: data) {
            tableView.getItems().add(item.polinomialTable);
            chart.getData().add(item.series);
        }


    }

    private TableChartData getPolinomialTable(AbstractPolinomialCalculator polinomialCalculator) {
        final PolinomialAnswer answer = polinomialCalculator.calculate();

        final PolinomialTable polinomialTable = new PolinomialTable();
        polinomialTable.setPolinomialName(answer.getPolinomName());
        polinomialTable.setX1(round(answer.getPAt(0)));
        polinomialTable.setX2(round(answer.getPAt(1)));
        polinomialTable.setX3(round(answer.getPAt(2)));
        polinomialTable.setX4(round(answer.getPAt(3)));
        polinomialTable.setX5(round(answer.getPAt(4)));

        if(polinomialCalculator instanceof AbstractLeastSquare) {
            polinomialTable.setSigma(String.valueOf(((AbstractLeastSquare) polinomialCalculator).calculateDeviation()));
        }

        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(answer.getPolinomName());

        for(int i=0; i < answer.getSize(); i++) {
            series.getData().add(new XYChart.Data<>(answer.getXiAt(i), answer.getPAt(i)));
        }

        return new TableChartData(polinomialTable, series);
    }

    private String round(double value) {
        return String.format("%.4f", value).replace(",", ".");
    }

    private static class TableChartData {
        public PolinomialTable polinomialTable;
        public XYChart.Series<Number, Number> series;

        public TableChartData(PolinomialTable polinomialTable, XYChart.Series<Number, Number> series) {
            this.polinomialTable = polinomialTable;
            this.series = series;
        }
    }
}
