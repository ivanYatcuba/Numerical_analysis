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
import java.util.List;
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
        List<Float> xSteps = service.getXSteps();

        XYChart.Series<Number, Number> series0 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            items.add(new CauchyModel(xSteps.get(i)));
        }

        service.y[0] = -Math.log(2);

        service.CalcEller();
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            items.get(i).setYe(service.y[i]);
        }

        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            series0.getData().add(new XYChart.Data<>(xSteps.get(i), service.y[i]));

        }
        service.CalcRengeKut();
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            items.get(i).setYrk(service.y[i]);
        }
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            series1.getData().add(new XYChart.Data<>(xSteps.get(i), service.y[i]));

        }
        service.CalcAdams();
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            items.get(i).setYa(service.y[i]);
        }


        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            series2.getData().add(new XYChart.Data<>(xSteps.get(i), service.y[i]));

        }
        service.CalcToch();
        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            items.get(i).setYDot(service.y[i]);
        }


        for (int i = 0; i < CauchyProblemService.n + 1; i++) {
            series3.getData().add(new XYChart.Data<>(xSteps.get(i), service.y[i]));
        }

        tableView.getItems().addAll(items);

        chart.getData().add(series0);
        chart.getData().add(series1);
        chart.getData().add(series2);
        chart.getData().add(series3);
    }
}
