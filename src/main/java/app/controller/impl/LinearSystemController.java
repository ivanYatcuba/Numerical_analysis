package app.controller.impl;


import app.data.LinearSystem;
import app.service.linearsystem.LinearSystemService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LinearSystemController extends AbstractFxmlController implements Initializable {

    public TextArea gaussView;
    public TextArea holetskiyView;
    public TextArea iterView;
    public TextArea kv_kornView;
    public TextFlow tf1;
    public TextFlow tf2;

    private LinearSystemService linearSystemService = new LinearSystemService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf1.getChildren().add(new Text(LinearSystem.FIRST_SYSTEM.toString()));

        tf2.getChildren().add(new Text(LinearSystem.SECOND_SYSTEM.toString()));
    }


    @FXML
    public void button1Click() {
        String gauss = linearSystemService.gaussianElimination();
        String holetskiy = linearSystemService.CholeskyDecomposition();
        String iter = linearSystemService.fixedPointIteration();

        String kv_korn = linearSystemService.squareRootAlgorithms();

        gaussView.setText(gauss);
        holetskiyView. setText(holetskiy);
        iterView.setText(iter);
        kv_kornView.setText(kv_korn);
    }
}
