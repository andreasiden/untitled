
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

public class Model {
    private DBConnection con;

    public Model(DBConnection dba) {
        con = dba;
    }

    public String[] selectAllFromStringColumn(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<String> list = new ArrayList<>();

        try (Connection conn = con.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString(column));
            }

            return list.toArray(new String[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new String[1];
    }

    public Integer[] selectAllFromIntColumn(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<Integer> list = new ArrayList<>();

        try (Connection conn = con.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getInt(column));
            }

            return list.toArray(new Integer[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Integer[1];
    }

    public Float[] selectAllFromFloatColumn(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<Float> list = new ArrayList<>();

        try (Connection conn = con.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getFloat(column));
            }

            return list.toArray(new Float[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Float[1];
    }

    public ResultSet select(String clause, String tablename, String where, String orderBy) {


        ResultSet rs = null;
        try {
            Connection conn = con.connect();
            Statement stmt = conn.createStatement();
            rs = null;


            if (where.trim().length() > 0 && orderBy.trim().length() > 0) {//if both where and order is supplied
                rs = stmt.executeQuery("SELECT " + clause + " FROM " + tablename + " WHERE " + where + " ORDER BY " + orderBy);
            } else if (where.trim().length() > 0) {//where clause is supplied
                rs = stmt.executeQuery("SELECT " + clause + " FROM " + tablename + " WHERE " + where);
            } else if (orderBy.trim().length() > 0) {//order clause is specified
                rs = stmt.executeQuery("SELECT " + clause + " FROM " + tablename + " ORDER BY " + orderBy);
            } else {//neither where or order by is supplied
                rs = stmt.executeQuery("SELECT " + clause + " FROM " + tablename);
            }


            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
//

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public void update(String table, String updatetext, String condition){
        String sql = "UPDATE " + table + " SET(" + updatetext + ") WHERE " + condition + " ;";
        try(Connection conn = con.connect();
            Statement ustmt = conn.createStatement()) {
            ustmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable(TableView tableView, String s, String f, String w, String o) throws SQLException {
        ResultSet rs  = select(s, f,w,o);
        ResultSetMetaData rsmd = rs.getMetaData();
        ObservableList observableList = FXCollections.observableArrayList();



        /**********************************
         * TABLE COLUMN ADDED DYNAMICALLY *
         **********************************/
        tableView.getColumns().clear();

        for(int i=0 ; i<rsmd.getColumnCount(); i++){
            final int j = i;
            TableColumn col = new TableColumn(rsmd.getColumnName(i+1));//oppretter en ny col med navn
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tableView.getColumns().addAll(col);


        }

        /********************************
         * Data added to ObservableList *
         ********************************/
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            observableList.add(row);


        }
        if(!rs.isClosed()){
            rs.close();
        }
        tableView.setItems(observableList);

    }

    public void insert (String table, String insertedText) {
        String sql = "INSERT INTO " + table + " VALUES (" + insertedText + ");";

        try (Connection conn = con.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeCustomSQL (String sql) {
        try (Connection conn = con.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer[] getAddressId() {
        return selectAllFromIntColumn("address_id", "address");
    }

    public String[] getStreetNumber() {
        return selectAllFromStringColumn("street_number", "address");
    }

    public String[] getStreetName() {
        return selectAllFromStringColumn("street_name", "address");
    }

    public String[] getPostalCode() {
        return selectAllFromStringColumn("postal_code", "address");
    }

    public String[] getPostalTown() {
        return selectAllFromStringColumn("postal_town", "address");
    }

    public Integer[] getCategoryId() {
        return selectAllFromIntColumn("category_id", "category");
    }

    public String[] getCategoryName() {
        return selectAllFromStringColumn("category_name", "category");
    }

    public Integer[] getCustomerId() {
        return selectAllFromIntColumn("customer_id", "customer");
    }

    public String[] getCustomerName() {
        return selectAllFromStringColumn("customer_name", "customer");
    }

    public Integer[] getCustomerAddress() {
        return selectAllFromIntColumn("customer_name", "customer");
    }

    public String[] getPhoneNumber() {
        return selectAllFromStringColumn("phone_number", "customer");
    }

    public String[] getBillingAccount() {
        return selectAllFromStringColumn("billing_account", "customer");
    }

    public Integer[] getInvoiceId() {
        return selectAllFromIntColumn("invoice_id", "invoice");
    }

    public String[] getDate() {
        return selectAllFromStringColumn("dato", "invoice");
    }

    public Integer[] getInvoiceCustomer() {
        return selectAllFromIntColumn("customer", "invoice");
    }

    public Integer[] getInvoiceItemsInvoice() {
        return selectAllFromIntColumn("invoice", "invoice_items");
    }

    public Integer[] getInvoiceItemsProduct() {
        return selectAllFromIntColumn("product", "invoice_items");
    }

    public Integer[] getProductId() {
        return selectAllFromIntColumn("product_id", "product");
    }

    public String[] getProductName() {
        return selectAllFromStringColumn("product_name", "product");
    }

    public String[] getDescription() {
        return selectAllFromStringColumn("description", "product");
    }

    public Float[] getPrice() {
        return selectAllFromFloatColumn("price", "product");
    }

    public Integer[] getProductCategory() {
        return selectAllFromIntColumn("category", "product");
    }
}