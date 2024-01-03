package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import dto.Tm.customerTm;
import dto.customer;


import java.awt.*;
import java.io.IOException;
import java.sql.*;


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
    private TableView<customerTm> tblCustomer;

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

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(customerTm newValue) {
        if (newValue != null) {
            txtID.setEditable(false);
            txtID.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
        }

    }

    private void loadCustomerTable() {
        ObservableList<customerTm> tmList = FXCollections.observableArrayList();
        String sql = "select * FROM customer";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "shivika123");
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()) {
                Button btn = new Button("Delete");
                customerTm c = new customerTm(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        btn
                );

                btn.SetOnAction(actionEvent -> {
                    deleteCustomer(c.getId());
                });
                tmList.add(c);
            }
            connection.close();

            tblCustomer.setItems(tmList);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(String id) {
        String sql = "DELETE from customer WHERE id='" + id + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "shivika123");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                loadCustomerTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }

            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateButtonOnAction(javafx.event.ActionEvent actionEvent) {
        customer c = new customer(txtID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        String sql = "UPDATE customer SET name='" + c.getName() + "', address='" + c.getAddress() + "', salary=" + c.getSalary() + " WHERE id='" + c.getId() + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "shivika123");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Customer " + c.getId() + " Updated!").show();
                loadCustomerTable();
                clearFields();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(javafx.event.ActionEvent actionEvent) {
        customer c = new customer(txtID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        String sql = "INSERT INTO customer values('" + c.getId() + "','" + c.getName() + "','" + c.getAddress() + "'," + c.getSalary() + ")";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "shivika123");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                loadCustomerTable();
                clearFields();
            }
            connection.close();

        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void reloadButtonOnAction(javafx.event.ActionEvent actionEvent) {
        loadCustomerTable();
        tblCustomer.refresh();
        clearFields();
    }

    private void clearFields() {
        tblCustomer.refresh();
        txtSalary.clear();
        txtAddress.clear();
        txtName.clear();
        txtID.clear();
        txtID.setEditable(true);
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) tblCustomer.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
