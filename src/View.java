import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class View extends Application {

    private ModelTwo mdl;

    private String street_number;

    public View() {
    }


    @Override
    public void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        TextField tf = new TextField();

        bp.setTop(tf);
        bp.setBottom(new TextField(street_number));
        bp.setLeft(new TextField(""));
        bp.setRight(new TextField("Right"));
        bp.setCenter(new TextField("Center"));

        Scene scene = new Scene(bp, 700, 550);

        primaryStage.setTitle("Faktura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void launchGui(String[] args) {
        launch(args);
    }
}