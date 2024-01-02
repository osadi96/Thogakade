package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class CustomerformController {

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private TableView tblCustomer;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableColumn colOption;

    public void initialize(){

    }

    public void updateButtonOnAction(javafx.event.ActionEvent actionEvent) {


    }

    public void saveButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }

    public void reloadButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }
}
