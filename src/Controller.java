
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


public class Controller implements Initializable {

    private ModelTwo mdl;

    //Fields for address
    @FXML TextField addId;
    @FXML TextField strtNumber;
    @FXML TextField strtName;
    @FXML TextField post_cd;
    @FXML TextField post_tw;


    //Fields for category
    @FXML TextField catId;
    @FXML TextField catName;

    //Fields for customer
    @FXML TextField custId;
    @FXML TextField custName;
    @FXML TextField add;
    @FXML TextField phNumber;
    @FXML TextField bill_acc;

    //fields for invoice
    @FXML TextField invId;
    @FXML TextField cust;
    @FXML TextField dato;

    //Fields for invoice_items
    @FXML TextField inv;
    @FXML TextField prod;

    //Fields for product
    @FXML TextField prodId;
    @FXML TextField prodName;
    @FXML TextField desc;
    @FXML TextField price;
    @FXML TextField cat;

    //
    @FXML Label customeridlbl;
    @FXML Label customerNamelbl;
    @FXML Label addresslbl;
    @FXML Label phonenumberlbl;
    @FXML Label billingaccountlbl;

    @FXML Label streetnumberlbl;
    @FXML Label streetnamelbl;
    @FXML Label postalcodelbl;
    @FXML Label postaltownlbl;

    @FXML MenuItem invdropdown;

    public Controller() {
        DBConnection dbconn = new DBConnection();
        mdl = new ModelTwo();
    }

    public void insertTables() {
        mdl.insert("address", addId.getText() + ", " + strtNumber.getText() + ", "
        + strtName.getText() + ", " + post_cd.getText() + ", " + post_tw.getText());
        mdl.insert("category", catId.getText() + ", " + catName.getText());
        mdl.insert("customer", custId.getText() + ", " + custName.getText() + ", "
        + add.getText() + ", " + phNumber.getText() + ", " + bill_acc.getText());
        mdl.insert("invoice", invId.getText() + ", " + cust.getText() + ", " +
                dato.getText());
        mdl.insert("invoice_items", inv.getText() + ", " + prod.getText());
        mdl.insert("product", prodId.getText() + ", " + prodName.getText() + ", " +
                desc.getText() + ", " + price.getText() + ", " + cat.getText());
        }

     public void invoiceData(int id) {
         String[] custname = mdl.getCustomer_name();
         customerNamelbl.setText(custname[id]);
         String [] streetname = mdl.getStreet_name();
         streetnamelbl.setText(streetname[id]);
         String [] streetnumber = mdl.getStreet_number();
         streetnumberlbl.setText(streetnumber[id]);
         String [] postalcode = mdl.getPostal_code();
         postalcodelbl.setText(postalcode[id]);
         String [] postaltown = mdl.getPostal_town();
         postaltownlbl.setText(postaltown[id]);
         String [] customerid = mdl.getCustomer_id();
         customeridlbl.setText(customerid[id]);
         Integer [] add = mdl.getAddress();
         addresslbl.setText(" " + add[id]);
         String[] phonenmbr = mdl.getPhone_number();
         phonenumberlbl.setText(phonenmbr[id]);
         String[] billingacc = mdl.getBilling_account();
         billingaccountlbl.setText(billingacc[id]);

     }

    public void insertAddress() {
        mdl.insert("address", addId.getText() + ", " + strtNumber.getText() + ", " +
                strtName.getText() + ", " + post_cd.getText() + ", " + post_tw.getText());
    }

    public void insertCategory() {
        mdl.insert("category", catId.getText() + ", " + catName.getText());
    }

    public void insertCustomer() {
        mdl.insert("customer", custId.getText() + ", " + custName.getText() + ", " +
                cust.getText() + ", " + phNumber.getText() + ", " + bill_acc.getText());
    }

    public void insertInvoice() {
        mdl.insert("invoice", invId.getText() + ", " + cust.getText() + ", " + dato.getText());

    }

    public void insertInvoiceItems() {
        mdl.insert("invoice_items", inv.getText() + ", " + prod.getText());
    }

    public void insertProduct() {
        mdl.insert("product", prodId.getText() + ", " + prodName.getText() + ", " +
                desc.getText() + ", " + price.getText() + ", " + cat.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invdropdown.setOnAction((event1) -> {
            invoiceData(0);
        });
    }



}

