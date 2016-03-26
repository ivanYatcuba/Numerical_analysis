package app.controller.impl;

import app.model.MyTableModel;
import app.service.FunctionApproximationService;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class FunctionApproximationController extends AbstractFxmlController implements Initializable {

    public TableView<MyTableModel> tableView;
    public ListView<String> lv;
    public LineChart<Number, Number> chart;
    public TableColumn<MyTableModel, String> colX;
    public TableColumn<MyTableModel, String> colL3;
    public TableColumn<MyTableModel, String> colP3;
    public TableColumn<MyTableModel, String> colQ0;
    public TableColumn<MyTableModel, String> colQ1;
    public TableColumn<MyTableModel, String> colQ2;
    public TableColumn<MyTableModel, String> colQ3;


    FunctionApproximationService service = new FunctionApproximationService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colX.setCellValueFactory(new PropertyValueFactory<>("xi"));
        colL3.setCellValueFactory(new PropertyValueFactory<>("L_coef"));
        colP3.setCellValueFactory(new PropertyValueFactory<>("P_coef"));
        colQ0.setCellValueFactory(new PropertyValueFactory<>("Q0_coef"));
        colQ1.setCellValueFactory(new PropertyValueFactory<>("Q1_coef"));
        colQ2.setCellValueFactory(new PropertyValueFactory<>("Q2_coef"));
        colQ3.setCellValueFactory(new PropertyValueFactory<>("Q3_coef"));
    }


    private void DrawGraph() {
        service.CalculateL();
        service.CalculateP();
        service.CalculateQ1();
        service.CalculateQ2();
        service.CalculateQ3();

        XYChart.Series<Number, Number> series0 = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesQ = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesP = new XYChart.Series<>();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();


        series0.setName("L");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            series0.getData().add(new XYChart.Data<>(service.x[i], service.L_coef.get(i)));
        }

        seriesQ.setName("Q3");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            seriesQ.getData().add(new XYChart.Data<>(service.x[i], service.Q3_coef.get(i)));
        }

        seriesP.setName("P");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            seriesP.getData().add(new XYChart.Data<>(service.x[i], service.P_coef.get(i)));
        }

        //series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Point;
        series1.setName("y");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            series1.getData().add(new XYChart.Data<>(service.x[i], service.y[i]));
        }

        series2.setName("Q0");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            series2.getData().add(new XYChart.Data<>(service.x[i], service.Q0_coef.get(i)));
        }

        series3.setName("Q1");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            series3.getData().add(new XYChart.Data<>(service.x[i], service.Q1_coef.get(i)));
        }

        series4.setName("Q2");
        for (int i = 0; i < FunctionApproximationService.SIZE; i++) {
            series4.getData().add(new XYChart.Data<>(service.x[i], service.Q2_coef.get(i)));
        }


        chart.getData().addAll(series0, seriesQ, seriesP, series1, series2, series3, series4);
    }

    public void calculate() {
        chart.getData().clear();
        service.CalculateXI();

        service.CalculateLi();
        service.CalculatePi();
        service.CalculateQ0();
        service.CalculateQ1i();
        service.CalculateQ2i();
        service.CalculateQ3i();
        tableView.getItems().clear();
        for (int i = 0; i < service.xi.length; i++) {

            MyTableModel tableModel = new MyTableModel(
                    service.xi[i],
                    service.L_coef.get(i),
                    service.P_coef.get(i),
                    service.Q0_coef.get(i),
                    service.Q1_coef.get(i),
                    service.Q2_coef.get(i),
                    service.Q3_coef.get(i)
            );

            tableView.getItems().add(tableModel);
        }

        DrawGraph();


        double[] det = service.CalculateDet();

        lv.getItems().clear();

        for (double aDet : det) {
            lv.getItems().add(String.valueOf(aDet));
        }
    }
}
