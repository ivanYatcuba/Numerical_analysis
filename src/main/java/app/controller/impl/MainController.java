package app.controller.impl;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController extends AbstractFxmlController implements Initializable {
    @Autowired
    private NonlinearEquationsController nonlinearEquationsController;
    @Autowired
    private LinearSystemController linearSystemController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
