import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;


public class App extends Application {


    public static void main(String[] args) {
        DBConnection conn = new DBConnection();

        try {
            conn.resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ModelTwo model = new ModelTwo(conn);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }


}
