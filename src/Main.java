import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
 
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("BlackJack");
            stage.setScene(scene);
            stage.setResizable(false);
            Image icon = new Image("file:assets/img/altro/icon.png");
            stage.getIcons().add(icon);
            stage.show();

            stage.setOnCloseRequest(event -> {event.consume(); esci(stage);});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void esci(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Esci");
        alert.setHeaderText("Stai per abbandonare il gioco");
        alert.setContentText("Vuoi davvero uscire?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}