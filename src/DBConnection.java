import java.io.*;
import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:sqlite:src/oblig.db";
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to a sample database
     */
    public Connection connect() {
        try {
            conn = DriverManager.getConnection(url);
            DatabaseMetaData meta = conn.getMetaData();
            //System.out.println("The driver name is " + meta.getDriverName());
            //System.out.println("Connection to database established.");

            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public DatabaseMetaData getMetaData() {
        DatabaseMetaData md = null;
        try {
            md = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return md;
    }

    public void resetDatabase() throws SQLException {
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try
        {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet resultSet = md.getTables(null, null, "%", null);
            boolean addressExists = false;
            boolean categoryExists = false;
            boolean customerExists = false;
            boolean invoiceExists = false;
            boolean invoiceItemsExists = false;
            boolean productExists = false;
            while (resultSet.next()) {
                if (resultSet.getString(3).equals("address")) {
                    addressExists = true;
                } else if (resultSet.getString(3).equals("category")) {
                    categoryExists = true;
                } else if (resultSet.getString(3).equals("customer")) {
                    customerExists = true;
                } else if (resultSet.getString(3).equals("invoice")) {
                    invoiceExists = true;
                } else if (resultSet.getString(3).equals("invoice_items")) {
                    invoiceItemsExists = true;
                } else if (resultSet.getString(3).equals("product")) {
                    productExists = true;
                }
            }
            if (!addressExists && !categoryExists && !customerExists && !invoiceExists && !invoiceItemsExists && !productExists) {
                FileReader fr = new FileReader(new File("src/db/oblig3v1_database.sql"));
                // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

                BufferedReader br = new BufferedReader(fr);

                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
                br.close();

                // here is our splitter ! We use ";" as a delimiter for each request
                // then we are sure to have well formed statements
                String[] inst = sb.toString().split(";");

                Connection c = connect();
                Statement st = c.createStatement();

                for (int i = 0; i < inst.length; i++) {
                    // we ensure that there is no spaces before or after the request string
                    // in order to not execute empty statements
                    if (!inst[i].trim().equals("")) {
                        st.executeUpdate(inst[i]);
                        System.out.println(">>" + inst[i]);
                    }
                }
            } else {
                System.out.println("Tables already exist");
            }

        }
        catch(SQLException e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}