import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.Random;


public class Controller {
    @FXML

    private Stage stage;
    private Scene scene;
    private Parent root;

    public ImageView myImageView;

    public HBox playerHand;

    private Integer valorePuntata;

    private Integer contatoreCarte = 0;

    public Pane puntataSelect;

    public Pane bottoni;

    public Button buttonCarta;

    public void vaiAScena1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void vaiAScena2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pescaCarta(){
        contatoreCarte++;
        if(contatoreCarte == 7){
            buttonCarta.setDisable(true);
        }
        playerHand.setSpacing(10);
        ImageView carta = new ImageView(generaCarta());
        carta.setFitHeight(125);
        carta.setPreserveRatio(true);
        playerHand.getChildren().add(carta);
        
    }

    private Image generaCarta(){
        Random random = new Random();
        
        int numeroN = random.nextInt(13) + 1;
        String numeroCarta = numeroN == 1 ? "A" : numeroN == 10 ? "T" : numeroN == 11 ? "J" : numeroN == 12 ? "Q" : numeroN == 13 ? "K" : String.valueOf(numeroN);
        int semeN = random.nextInt(3) + 1;
        
        String semeCarta = semeN == 1 ? "S" : semeN == 2 ? "C" : semeN == 3 ? "H" : "D";
        String fileCarta = "file:assets/img/carte/fronte/" + numeroCarta + semeCarta + "@1x.png";
        
        Image carta = new Image(fileCarta);
        return carta;
    }

    public void punta100(){
        valorePuntata = 100;
        puntataSelect.setDisable(true);
        puntataSelect.setOpacity(0);
        setPlayerHandVisible();
    }

    public void punta250(){
        valorePuntata = 250;
        puntataSelect.setDisable(true);
        puntataSelect.setOpacity(0);
        setPlayerHandVisible();
    }

    public void punta500(){
        valorePuntata = 500;
        puntataSelect.setDisable(true);
        puntataSelect.setOpacity(0);
        setPlayerHandVisible();
    }

    private void setPlayerHandVisible(){
        playerHand.setDisable(false);
        playerHand.setOpacity(1);
        bottoni.setDisable(false);
        bottoni.setOpacity(1);
        
    }

    public void esci() {
        System.exit(0);
    }
}