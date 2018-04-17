/*
import java.sql.*;
import java.util.ArrayList;

public class Model {

    private DBConnection dbcon;


    private Address ad;

    private Category cat;

    private Customer custom;

    private Invoice inv;

    private Invoice_items invoice_items;

    private Product prod;



    public Model(DBConnection conn) {

        dbcon = new DBConnection();

        /*
        ad = new Address(getAddress_id(), getStreetNumber(), getStreetName(), getPostal_code(),
                getPostal_town());
        cat = new Category(getCategory_id(), getCategoryName());
        custom = new Customer(getCustomer_id(), getCustomer_name(), getAddress(), getPhone_number(),
                getBilling_account());
        inv = new Invoice(getInvoice_id(), getCustomer(), getDato());
        invoice_items = new Invoice_items(getInvoice(), getProduct());
        prod = new Product(getProduct_id(), getProduct_name(), getDescription(),
                            getPrice(), getCategory());

     }




    public void setAddress_id(Integer[] address_id) {
        this.address_id = address_id;
    }

    public void setStreet_number(String[] street_number) {
        this.street_number = street_number;
    }

    public void setStreet_name(String[] street_name) {
        this.street_name = street_name;
    }

    public void setPostal_code(String[] postal_code) {
        this.postal_code = postal_code;
    }

    public void setPostal_town(String[] postal_town) {
        this.postal_town = postal_town;
    }

    public void setCategory_id(Integer[] category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String[] category_name) {
        this.category_name = category_name;
    }

    public void setCustomer_id(Integer[] customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_name(String[] customer_name) {
        this.customer_name = customer_name;
    }

    public void setAddress(Integer[] address) {
        this.address = address;
    }

    public void setPhone_number(String[] phone_number) {
        this.phone_number = phone_number;
    }

    public void setBilling_account(String[] billing_account) {
        this.billing_account = billing_account;
    }

    public void setInvoice_id(Integer[] invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setCustomer(Integer[] customer) {
        this.customer = customer;
    }

    public void setDato(String[] dato) {
        this.dato = dato;
    }

    public void setInvoice(Integer[] invoice) {
        this.invoice = invoice;
    }

    public void setProduct(Integer[] product) {
        this.product = product;
    }

    public void setProduct_id(Integer[] product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String[] product_name) {
        this.product_name = product_name;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public void setPrice(Float[] price) {
        this.price = price;
    }

    public void setCategory(Integer[] category) {
        this.category = category;
    }


    //fields for adress table
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

    //fields for Invoice_items table
    private Integer[] invoice;
    private Integer[] product;

    //fields for product table
    private Integer[] product_id;
    private String[] product_name;
    private String[] description;
    private Float[] price;
    private Integer[] category;


//    public Integer[] selectInts(String column, String table, Integer[] startingData) {
//
//        Statement stmt;
//        String sql = "SELECT " + column + " FROM " + table + ";";
//        ArrayList<Integer> temp = new ArrayList<>();
//
//        try {
//            Connection connection = dbcon.connect();
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                Integer integer = rs.getInt(column);
//                temp.add(integer);
//            }
//
//            Integer[] res = new Integer[temp.size()];
//
//            for(int i = 0; i < temp.size(); i++) {
//                res[i] = temp.get(i).intValue();
//            }
//
//            return res;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return startingData;
//    }


    public String[] selectStrings(String column, String table, String[] startingdata) {

        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<String> temp = new ArrayList<>();

        try {Connection con = dbcon.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String string = rs.getString(column);
                temp.add(string);
            }

            return temp.toArray(new String[temp.size()]);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return startingdata;
    }



    public Float[] selectFloats(String column, String table, Float[] startingData) {

        Statement stmt;
        String sql = "SELECT " + column + " FROM " + table + ";";
        ArrayList<Float> temp = new ArrayList<>();

        try {
            Connection conn = dbcon.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Float f = rs.getFloat(column);
                temp.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startingData;
    }


    public Integer[] getAddress_id() {
        Integer[] address_id = selectInts("address_id", "address", address_id);
        return address_id;
    }


    public String[] getStreetNumber() {
        String[] result = selectStrings("street_number", "address",
                ad.getStreet_number());
        ad.setStreet_number(result);
        return result;
    }

    public String[] getStreetName() {
        String[] result = selectStrings("street_name", "address",
                ad.getStreet_name());
        ad.setStreet_name(result);
        return result;
    }

    public String[] getPostal_code() {
        String[] result = selectStrings("postal_code", "address",
                ad.getPostal_code());
        ad.setPostal_code(result);
        return result;
    }

    public String[] getPostal_town() {
        String[] result = selectStrings("postal_town", "address",
                ad.getPostal_town());
        setPostal_town(result);
        return result;
    }

    public Integer[] getCategory_id() {
        Integer[] result = selectInts("category_id", "category",
                cat.getCategory_id());
        cat.setCategory_id(result);
        return result;
    }

    public String[] getCategoryName() {
        String[] result = selectStrings("category_name", "category",
                cat.getCategory_name());
        cat.setCategory_name(result);
        return result;
    }

    public Integer[] getCustomer_id() {
        Integer[] result = selectInts("customer_id", "customer",
                custom.getCustomer_id());
        custom.setCustomer_id(result);
        return result;
    }


    public String[] getCustomer_name() {
        String[] result = selectStrings("customer_name", "customer",
                custom.getCustomer_name());
        custom.setCustomer_name(result);
        return result;
    }

    public Integer[] getAddress() {
        Integer[] result = selectInts("address", "customer",
                custom.getAddress());
        custom.setAddress(result);
        return result;
    }

    public String[] getPhone_number() {
        String[] result = selectStrings("phone_number", "customer",
                custom.getPhone_number());
        custom.setPhone_number(result);
        return result;
    }

    public String[] getBilling_account () {
        String[] result = selectStrings("billing_account", "customer",
                custom.getBilling_account());
        custom.setBilling_account(result);
        return result;
    }

    public Integer[] getInvoice_id () {
        Integer[] result = selectInts("invoice_id", "invoice",
                inv.getInvoice_id());
        inv.setInvoice_id(result);
        return result;
    }

    public Integer[] getCustomer () {
        Integer[] result = selectInts("customer", "invoice",
                inv.getCustomer());
        setCustomer(result);
        return result;
    }

    public String[] getDato () {
        String[] result = selectStrings("dato", "invoice", inv.getDato());
        inv.setDato(result);
        return result;
    }

    public Integer[] getInvoice () {
        Integer[] result = selectInts("invoice", "Invoice_items",
                invoice_items.getInvoice());
        invoice_items.setInvoice(result);
        return result;
    }

    public Integer[] getProduct () {
        Integer[] result = selectInts("product", "Invoice_items",
                invoice_items.getProduct());
        invoice_items.setProduct(result);
        return result;
    }

    public Integer[] getProduct_id () {
        Integer[] result = selectInts("product_id", "product",
                prod.getProduct_id());
        prod.setProduct_id(result);
        return result;
    }

    public String[] getProduct_name () {
        String[] result = selectStrings("product_name", "product",
                prod.getProduct_name());
        prod.setProduct_name(result);
        return result;
    }

    public String[] getDescription () {
        String[] result = selectStrings("description", "product",
                prod.getDescription());
        prod.setDescription(result);
        return result;
    }

    public Float[] getPrice () {
        Float[] result = selectFloats("price", "product", getPrice());
        prod.setPrice(result);
        return result;
    }

    public Integer[] getCategory () {
        Integer[] result = selectInts("category", "product", prod.getCategory());
        prod.setCategory(result);
        return result;
    }





}
*/