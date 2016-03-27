package app.controller.impl;


import app.calculations.linear.system.calculator.impl.CholeskyLinearSystemCalculator;
import app.calculations.linear.system.calculator.impl.GaussianLinearSystemCalculator;
import app.calculations.linear.system.calculator.impl.SimpleIterationLinearSystemCalculator;
import app.calculations.linear.system.calculator.impl.SquareRootLinearSystemCalculator;
import app.dto.SystemSolutionDto;
import app.util.data.linear.system.LinearSystem;
import app.util.data.linear.system.impl.FirstSystem;
import app.util.data.linear.system.impl.FirstSystemDiagonalDomination;
import app.util.data.linear.system.impl.SecondSystem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LinearSystemController extends AbstractFxmlController implements Initializable {


    @FXML
    private TableView<SystemSolutionDto> solutionsTable;

    @FXML
    private TableColumn<SystemSolutionDto, String> columnFunction;
    @FXML
    private TableColumn<SystemSolutionDto, Integer> functionNumber;

    @FXML
    private TableColumn<SystemSolutionDto, Double> columnX1;
    @FXML
    private TableColumn<SystemSolutionDto, Double> columnX2;
    @FXML
    private TableColumn<SystemSolutionDto, Double> columnX3;

    @FXML
    private TableColumn<SystemSolutionDto, Double> columnSigma1;
    @FXML
    private TableColumn<SystemSolutionDto, Double> columnSigma2;
    @FXML
    private TableColumn<SystemSolutionDto, Double> columnSigma3;

    @FXML
    private TableColumn<SystemSolutionDto, String> columnIterations;

    @FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;


    private final LinearSystem system1 = new FirstSystem();
    private final LinearSystem system1Dom = new FirstSystemDiagonalDomination();

    private final LinearSystem system2 = new SecondSystem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnFunction.setCellValueFactory(new PropertyValueFactory<>("function"));
        functionNumber.setCellValueFactory(new PropertyValueFactory<>("functionNumber"));

        columnX1.setCellValueFactory(new PropertyValueFactory<>("x1"));
        columnX2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        columnX3.setCellValueFactory(new PropertyValueFactory<>("x3"));

        columnSigma1.setCellValueFactory(new PropertyValueFactory<>("sigma1"));
        columnSigma2.setCellValueFactory(new PropertyValueFactory<>("sigma2"));
        columnSigma3.setCellValueFactory(new PropertyValueFactory<>("sigma3"));

        columnIterations.setCellValueFactory(new PropertyValueFactory<>("iterations"));

        firstLabel.setText("Система 1:\n" + system1.toString());
        secondLabel.setText("Система 2:\n" + system2.toString());
    }

    public void calculateSystem() {
        solutionsTable.getItems().clear();

        solutionsTable.getItems().add(new GaussianLinearSystemCalculator(system1).calculate());
        solutionsTable.getItems().add(new CholeskyLinearSystemCalculator(system1).calculate());
        solutionsTable.getItems().add(new SimpleIterationLinearSystemCalculator(system1Dom).calculate());

        solutionsTable.getItems().add(new SquareRootLinearSystemCalculator(system2).calculate());
    }

}
