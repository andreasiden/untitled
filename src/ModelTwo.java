import java.net.Inet4Address;
import java.sql.*;
import java.util.ArrayList;


public class ModelTwo {

    //fields for address table

    private Integer[] address_id;
    private String[] street_number;
    private String[] street_name;
    private String[] postal_code;
    private String[] postal_town;

    //fields for category table
    private Integer[] category_id;
    private String[] category_name;

    //fields for customer table
    private Integer[] customer_id;
    private String[] customer_name;
    private Integer[] address;
    private String[] phone_number;
    private String[] billing_account;

    //fields for invoice table
    private Integer[] invoice_id;
    private Integer[] customer;
    private String[] dato;

    //fields for invoice_items table
    private Integer[] invoice;
    private Integer[] product;

    //fields for product table
    private Integer[] product_id;
    private String[] product_name;
    private String[] description;
    private Float[] price;
    private Integer[] category;


    private DBConnection dbcon;

    public ModelTwo() {

        /*
        address_id = selectInts("address_id", "address");
        street_number = selectStrings("street_number", "address");
        street_name = selectStrings("street_name", "address");
        postal_code = selectStrings("postal_code", "address");
        postal_town = selectStrings("postal_town", "address");

        category_id = selectInts("category_id", "category");
        category_name = selectStrings("category_name", "category");

        customer_id = selectInts("customer_id", "customer");
        customer_name = selectStrings("customer_name", "customer");
        address = selectInts("address", "customer");
        phone_number = selectStrings("phone_number", "customer");
        billing_account = selectStrings("billing_account", "customer");

        invoice_id = selectInts("invoice_id", "invoice");
        customer = selectInts("customer", "invoice");
        dato = selectStrings("dato", "invoice");

        invoice = selectInts("invoice", "invoice_items");
        product = selectInts("product", "invoice_items");

        product_id = selectInts("product_id", "product");
        product_name = selectStrings("product_name", "product");
        description = selectStrings("description", "product");
        price = selectFloats("price", "product");
        category = selectInts("category", "product");
*/

    }

    public void refreshAll() {
        getAddress_id();
        getStreet_number();
        getStreet_name();
        getPostal_code();
        getPostal_town();

        getCategory_id();
        getCategory_name();

        getCustomer_id();
        getCustomer_name();
        getPhone_number();
        getBilling_account();

        getInvoice_id();
        getCustomer();
        getDato();

        getInvoice();
        getProduct();

        getProduct_id();
        getProduct_name();
        getDescripion();
        getPrice();
        getCategory();
    }

    private Connection connect() {
        String url = "jdbc:sqlite:src/oblig.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String[] selectStrings(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<String> list = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getString(column));
            }

            return list.toArray(new String[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new String[1];
    }


    public Float[] selectFloats(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<Float> list = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getFloat(column));
            }

            return list.toArray(new Float[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Float[1];
    }

    public Integer[] selectInts(String column, String table){
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<Integer> list = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getInt(column));
            }

            return list.toArray(new Integer[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Integer[1];
    }

    public void insert (String table, String text) {
        String sql = "INSERT INTO " + table + " VALUES (" + text + ");";

        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeCustomSQL (String sql) {
        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer[] getAddress_id() {
        return selectInts("address_id", "address");

    }

    public String[] getStreet_number() {
        return selectStrings("street_number", "address");
    }

    public String[] getStreet_name() {
        return selectStrings("street_name", "address");
    }


    public String[] getPostal_code() {
        return selectStrings("postal_code", "address");
    }


    public String[] getPostal_town() {
        return selectStrings("postal_town", "address");
    }

    public Integer[] getCategory_id() {
        return selectInts("category_id", "category");

    }

    public String[] getCategory_name() {
        return selectStrings("category_name", "category");
    }

    public String[] getCustomer_id() {
        return selectStrings("customer_id", "customer");
    }

    public String[] getCustomer_name() {
        return selectStrings("customer_name", "customer");
    }


    public Integer[] getAddress() {
        return selectInts("address", "customer");

    }


    public String[] getPhone_number() {
        return selectStrings("phone_number", "customer");

    }

    public String[] getBilling_account() {
        return selectStrings("billing_account", "customer");

    }

    public Integer[] getInvoice_id() {
        return selectInts("invoice_id", "invoice");

    }

    public Integer[] getCustomer() {
        return selectInts("customer", "invoice");

    }

    public String[] getDato() {
        return selectStrings("dato", "invoice");

    }

    public Integer[] getInvoice() {
        return selectInts("invoice", "invoice_items");
    }

    public Integer[] getProduct() {
        return selectInts("product", "invoice_items");

    }

    public Integer[] getProduct_id() {
        return selectInts("product_id", "product");

    }

    public String[] getProduct_name() {
        return selectStrings("product_name", "product");

    }

    public String[] getDescripion() {
        return selectStrings("description", "product");

    }

    public Float[] getPrice() {
        return selectFloats("price", "product");

    }

    public Integer[] getCategory() {

        return selectInts("category", "product");

    }
}
