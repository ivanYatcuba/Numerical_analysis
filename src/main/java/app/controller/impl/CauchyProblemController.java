package app.controller.impl;

import app.dto.CauchyModel;
import app.service.CauchyProblemService;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class CauchyProblemController extends AbstractFxmlController implements Initializable {

    public LineChart<Number, Number> chart;


    public TableView<CauchyModel> tableView;
    public TableColumn<CauchyModel, String> colX;
    public TableColumn<CauchyModel, String> colYe;
    public TableColumn<CauchyModel, String> colYrk;
    public TableColumn<CauchyModel, String> colYa;
    public TableColumn<CauchyModel, String> colYDot;

    CauchyProblemService service = new CauchyProblemService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colX.setCellValueFactory(new PropertyValueFactory<>("x"));
        colYe.setCellValueFactory(new PropertyValueFactory<>("Ye"));
        colYrk.setCellValueFactory(new PropertyValueFactory<>("Yrk"));
        colYa.setCellValueFactory(new PropertyValueFactory<>("Ya"));
        colYDot.setCellValueFactory(new PropertyValueFactory<>("YDot"));
    }


    public void calculate() {
        ArrayList<CauchyModel> items = new ArrayList<>();
        double[] xSteps = service.getXSteps();

        XYChart.Series<Number, Number> series0 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            items.add(new CauchyModel(String.format("%.2f", xSteps[i])));
        }

        service.initStartValue();

        service.eulerMethod();
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            items.get(i).setYe(str(service.y[i]));
        }

        series0.setName("Ye");
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            series0.getData().add(new XYChart.Data<>(xSteps[i], service.y[i]));

        }
        service.rungeKuttaMethod();
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            items.get(i).setYrk(str(service.y[i]));
        }
        series1.setName("Yrk");
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            series1.getData().add(new XYChart.Data<>(xSteps[i], service.y[i]));

        }


        service.adamsMethod();
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            items.get(i).setYa(str(service.y[i]));
        }

        series2.setName("Ya");
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            series2.getData().add(new XYChart.Data<>(xSteps[i], service.y[i]));

        }
        service.exactCalculate();
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            items.get(i).setYDot(str(service.y[i]));
        }

        series3.setName("Y*");
        for (int i = 0; i < CauchyProblemService.N + 1; i++) {
            series3.getData().add(new XYChart.Data<>(xSteps[i], service.y[i]));
        }

        tableView.getItems().addAll(items);

        chart.getData().add(series0);
        chart.getData().add(series1);
        chart.getData().add(series2);
        chart.getData().add(series3);
    }



    private String str(double x) {
        return String.format("%.4f", x);
    }
}
