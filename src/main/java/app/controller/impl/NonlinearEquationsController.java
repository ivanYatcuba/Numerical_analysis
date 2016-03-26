package app.controller.impl;

import app.service.NonlinearEquationsCalculator;
import app.util.lab1.IterationMethodResult;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

@Controller
public class NonlinearEquationsController extends AbstractFxmlController implements Initializable {

    public ListView<String> resultList;
    public Label funcLabel;

    NonlinearEquationsCalculator calculator = new NonlinearEquationsCalculator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcLabel.setText(calculator.func.toString());
    }


    public void calculate() {
        resultList.getItems().clear();

        LinkedList<String> resultedLog = new LinkedList<>();
        resultedLog.add(buildLogAboutResult("Метод половинного деления", calculator.Pol_del()));
        resultedLog.add(buildLogAboutResult("Метод итераций", calculator.Iter()));
        resultedLog.add(buildLogAboutResult("Метод Ньютоная", calculator.Nuton()));
        resultedLog.add(buildLogAboutResult("Метод хорд", calculator.Hord()));

        resultList.getItems().addAll(resultedLog);
    }

    private String buildLogAboutResult(String methodName, IterationMethodResult polDelResult) {
        return methodName +"\n"
           + "x= " + polDelResult.getResult() + "\n"
                + "Итераций: " + polDelResult.getNumberIteration();
    }
}
