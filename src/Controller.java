
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.sql.*;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class Controller implements Initializable {

    private ModelTwo mdl;

    //Address input fields
    @FXML TextField addAddresId;
    @FXML TextField addStreetNumber;
    @FXML TextField addStreetName;
    @FXML TextField addPostCode;
    @FXML TextField addPostTown;

    //Category input fields
    @FXML TextField addCategoryId;
    @FXML TextField addCategoryName;

    //Customer input fields
    @FXML TextField addCustomerId;
    @FXML TextField addCustomerName;
    @FXML TextField addAddress;
    @FXML TextField addPhoneNumber;
    @FXML TextField addBillingAccount;

    //Invoice input fields
    @FXML TextField addInvoiceId;
    @FXML TextField addCustomer;
    @FXML TextField addDato;

    //Invoice Items input fields
    @FXML TextField addInvoice;
    @FXML TextField addProduct;

    //Product input fields
    @FXML TextField addProductId;
    @FXML TextField addProductName;
    @FXML TextField addDescription;
    @FXML TextField addPrice;
    @FXML TextField addCategory;

    //Invoice tab labels
    @FXML Label customerNameLabel;

    @FXML Label customerPhoneNumberLabel;
    @FXML Label customerAccountLabel;
    @FXML Label customerIdLabel;

    @FXML Label totalPriceLabel;
    @FXML Label addressLabel;
    @FXML Label datoLabel;
    @FXML Label productIdLabel;
    @FXML Label productNameLabel;
    @FXML Label descriptionLabel;
    @FXML Label priceLabel;


    //Table
    @FXML TableView table;
    @FXML TableView invoiceTable;
    @FXML TableColumn col1;
    @FXML TableColumn col2;
    @FXML TableColumn col3;
    @FXML TableColumn col4;
    @FXML TableColumn col5;

    //ListViews
    @FXML ListView productIdListView;
    @FXML ListView productDescriptionListView;
    @FXML ListView productNameListView;
    @FXML ListView productCategoryListView;
    @FXML ListView productPriceListView;

    @FXML MenuItem invdropdown;



    public Controller() {
        DBConnection dbconn = new DBConnection();
        mdl = new ModelTwo(dbconn);
    }


    public void insertAddress() {
        mdl.insert("address", addAddresId.getText() + ", " + addStreetNumber.getText() + ", " +
                addStreetName.getText() + ", " + addPostCode.getText() + ", " + addPostTown.getText());
    }

    public void insertCategory() {
        mdl.insert("category", addCategoryId.getText() + ", " + addCategoryName.getText());
    }

    public void insertCustomer() {
        mdl.insert("customer", addCustomerId.getText() + ", " + addCustomerName.getText() + ", " +
                addAddress.getText() + ", " + addPhoneNumber.getText() + ", " + addBillingAccount.getText());
    }

    public void insertInvoice() {
        mdl.insert("invoice", addInvoiceId.getText() + ", " + addCustomer.getText() +
                ", " + addDato.getText());

    }

    public void insertInvoiceItems() {
        mdl.insert("invoice_items", addInvoice.getText() + ", " + addProduct.getText());
    }

    public void insertProduct() {
        mdl.insert("product", addProductId.getText() + ", " + addProductName.getText() + ", " +
                addDescription.getText() + ", " + addPrice.getText() + ", " + addCategory.getText());
    }

    public void updateAddress() {
        mdl.update("address", "street_number='" + addStreetNumber.getText() + "', street_name='" +
                addStreetName.getText() + "', postal_code='" + addPostCode.getText() + "', postal_town='" +
                addPostTown.getText() + "'", "address_id=" + addAddresId.getText());
    }

    public void updateCategory() {
        mdl.update("category", "category_name = '" + addCategoryName.getText() + "'", "category_id = "
                + addCategoryId.getText());
    }

    public void updateCustomer() {
        mdl.update("customer", "customer_name = '" + addCustomerName.getText() + "', address = " +
                addAddress.getText() + ", phone_number = '" + addPhoneNumber.getText() + "', billing_account = '" +
                addBillingAccount.getText() + "'", "customer_id = " + addCustomerId.getText());
    }

    public void updateInvoice() {
        mdl.update("invoice", "customer = " + addCustomer.getText() + ", dato = '" + addDato.getText()
                + "'", "invoice_id = " + addInvoiceId.getText());

    }

    public void updateInvoiceItems() {
        mdl.update("invoice_items", "product = " + addProduct.getText(), "invoice = " +
                addInvoice.getText());
    }

    public void updateProduct() {
        mdl.update("product", "product_name = '" + addProductName.getText() + "', description = '" +
                addDescription.getText() + "', price = " + addPrice.getText() + ", category = " +
                addCategory.getText(), "product_id = " + addProductId.getText());
    }

    public void getInvoiceData(int id) {

        Integer[] custo = mdl.getCustomerId();
        customerIdLabel.setText("CustomerID : " + custo[id]);
        String[] names = mdl.getCustomerName();
        customerNameLabel.setText(names[id]);
        Integer[] adr = mdl.getCustomerAddress();
        addressLabel.setText("address : " + adr[id]);
        String[] rsPN = mdl.getPhoneNumber();
        customerPhoneNumberLabel.setText(rsPN[id]);
        String[] rsBA = mdl.getBillingAccount();
        customerAccountLabel.setText("Account : " + rsBA[id]);

        String[] da = mdl.getDate();
        datoLabel.setText(da[id]);


        Integer[] pid = mdl.getProductId();
        productIdLabel.setText("productID: " + pid[id]);
        String[] pnm = mdl.getProductName();
        productNameLabel.setText(pnm[id]);
        String[] des = mdl.getDescription();
        descriptionLabel.setText(des[id]);
        Float[] pr = mdl.getPrice();
        Float total = 0.0f;
        Float fin = new Float(0);
        priceLabel.setText("Price : " + pr);
        for (Float f : pr) {
            fin = f + total;
            Label label = new Label("" + f);
        }
        totalPriceLabel.setText("Total: " + fin);


        /*
        TableView myTableView = new TableView();
        ComboBox myComboBox = new ComboBox();

        try {
            mdl.populateTable(myTableView,
                    "product.product_name, product.price",
                    "product inner join invoice_items on product.product_id=invoice_items.product",
                    "invoice_items.invoice=" + myComboBox.getValue().toString(), "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        /*
        String[] rsStreetNames = mdl.getStreetName();
        String[] rsStreetNums = mdl.getStreetNumber();
        customerStreetNameLabel.setText(rsStreetNames[id] + " " + rsStreetNums[id]);
        String[] rsPC = mdl.getPostalCode();
        String[] rsPT = mdl.getPostalTown();
        customerPostalCodeLabel.setText(rsPC[id]);
        customerPostalTownLabel.setText(rsPT[id]);
        Integer[] rsCId = mdl.getCustomerId();
        customerIdLabel.setText("Customer: " + rsCId[id]);
        Integer[] rsIId = mdl.getInvoiceId();
        invoiceIdLabel.setText("Invoice: " + rsIId[id]);
        String[] rsD = mdl.getDate();
        invoiceDateLabel.setText("Date: " + rsD[id]);
        Float[] nums = mdl.getPrice();
        Float total = 0.0f;
        for (Float f : nums) {
            total = f + total;
            Label label = new Label("" + f);
            productPriceListView.getItems().add(label);
        }
        totalPriceLabel.setText("Total: " + total);
        Integer[] ids = mdl.getProductId();
        for (Integer i : ids) {
            Label label = new Label("" + i);
            productIdListView.getItems().add(label);
        }
        String[] desc = mdl.getDescription();
        for (String s : desc) {
            Label label = new Label(s);
            productDescriptionListView.getItems().add(label);
        }
        String[] prodNames = mdl.getProductName();
        for (String s : prodNames) {
            Label label = new Label(s);
            productNameListView.getItems().add(label);
        }
        String[] catNames = mdl.getCategoryName();
        for (String s : catNames) {
            Label label = new Label(s);
            productCategoryListView.getItems().add(label);
        }
        */
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invdropdown.setOnAction((event) -> getInvoiceData(0));
    }

}

