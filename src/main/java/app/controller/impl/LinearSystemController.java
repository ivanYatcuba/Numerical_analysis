package app.controller.impl;


import app.service.LinearSystemService;
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
        tf1.getChildren().add(new Text(
                "3*x1+x2-x3=2\n" +
                "x1+x2-4*x3=-6\n" +
                "-2*x1+4*x2-x3=0\n"));

        tf2.getChildren().add(new Text(
                "x1+3*x2-3*x3=11\n" +
                "3*x1+10*x2-11*x3=-2\n" +
                "-3*x1-11*x2-x3=10\n"));
    }


    @FXML
    public void button1Click() {
        String gauss = linearSystemService.Gauss();
        String holetskiy = linearSystemService.Holetskiy();
        String iter = linearSystemService.Iter();

        String kv_korn = linearSystemService.Kv_korn();

        gaussView.setText(gauss);
        holetskiyView. setText(holetskiy);
        iterView.setText(iter);
        kv_kornView.setText(kv_korn);
    }
}
