import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.Random;


public class Controller {
    @FXML

    private Stage stage;
    private Scene scene;
    public ImageView myImageView;
    public HBox playerHand;
    private Integer valorePuntata;
    private Integer contatoreCarte = 2;
    public HBox dealerHand;
    public Label manoDealerLabel;
    public ImageView cartaGirata = new ImageView(new Image("file:assets/img/carte/dorso/back.png"));

    public Pane puntataSelect;

    public Pane bottoni;
    public Button buttonCarta;
    public Button buttonRaddoppia;
    public Button buttonStai;

    public Label soldiLabel;

    public Label manoLabel;

    private ArrayList<ImageView> listaCarteDealer = new ArrayList<ImageView>();
    private ArrayList<ImageView> listaCarte = new ArrayList<ImageView>();

    private Integer soldiCorrenti = 1000; 

    boolean cartaDealerGirata;
    int somma = 0;
    int sommaDealer = 0;

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


    public void punta100(){
        valorePuntata = 100;
        disable(puntataSelect, true, 0);
        disable(manoLabel, false, 1);
        setPlayerHandVisible();
        setDealerHandVisibile();
    }

    public void punta250(){
        valorePuntata = 250;
        disable(puntataSelect, true, 0);
        disable(manoLabel, false, 1);
        setDealerHandVisibile();
        setPlayerHandVisible();
    }

    public void punta500(){
        valorePuntata = 500;
        disable(puntataSelect, true, 0);
        disable(manoLabel, false, 1);
        setDealerHandVisibile();
        setPlayerHandVisible();
    }

    private void setPlayerHandVisible(){
        disable(playerHand, false, 1);
        disable(bottoni, false, 1);
        soldiCorrenti -= valorePuntata;
        playerHand.setSpacing(-45);
        soldiLabel.setText(soldiCorrenti + "€");
        for (int i = 0; i < 2; i++) {
            ImageView carta = new ImageView(generaCarta());
            listaCarte.add(carta);
            carta.setFitHeight(125);
            carta.setPreserveRatio(true);
            playerHand.getChildren().add(carta);
        }
        
        contaCarte();
        if(somma == 21){
            manoLabel.setText("La tua mano e': " + somma + " (BlackJack)");
            manoLabel.setTextFill(Color.LIGHTGREEN);
            stai();
        }
    }

    private void setDealerHandVisibile(){
        pescaCartaDealer();
        cartaGirata = new ImageView(new Image("file:assets/img/carte/dorso/back.png"));
        cartaDealerGirata = true;
        cartaGirata.setFitHeight(125);
        cartaGirata.setPreserveRatio(true);
        dealerHand.getChildren().add(cartaGirata);
        disable(manoDealerLabel, false, 1);
        
    }

    private void giraCarta(){
        cartaDealerGirata = false;
        cartaGirata.setImage(generaCarta());
        listaCarteDealer.add(cartaGirata);
        contaCarteDealer();
        while (sommaDealer < 17){
            pescaCartaDealer();
        }
    }

    private void calcolaValoreCartaDealer(ImageView carta){
        String immagine = carta.getImage().getUrl();
        String nomeFile = immagine.substring(immagine.lastIndexOf("/") + 1);
        String[] parti = nomeFile.split("@");
        String numeroTesto = parti[0].substring(0, 1);
        Integer numeroCarta = 0;
        System.out.println("Il numero del testo e' " + numeroTesto);
        if(numeroTesto.equals("A") && sommaDealer + 11 > 21){
            numeroCarta = 1;
        }else if(numeroTesto.equals("A") && sommaDealer + 11 <= 21){
            numeroCarta = 11;
        } else if(numeroTesto.equals("T") || numeroTesto.equals("J")  || numeroTesto.equals("Q")  || numeroTesto.equals("K")){
            numeroCarta = 10;
        } else{
            numeroCarta = Integer.parseInt(numeroTesto);
        }
        System.out.println(numeroCarta);
        sommaDealer += numeroCarta;
    }

    public void pescaCartaDealer(){
        dealerHand.setSpacing(-45);
        ImageView carta = new ImageView(generaCarta());
        listaCarteDealer.add(carta);
        carta.setFitHeight(125);
        carta.setPreserveRatio(true);
        dealerHand.getChildren().add(carta);
        contaCarteDealer();
        
        if (sommaDealer > 21) {
            manoDealerLabel.setText("Mano dealer: " + sommaDealer + " (Ha sballato!)");
            manoDealerLabel.setTextFill(Color.LIGHTGREEN);
        }
    }

    public void pescaCarta(){
        if(contatoreCarte == 2){
            buttonRaddoppia.setDisable(true);
        }
        
        contatoreCarte++;
        if(contatoreCarte == 7){
            buttonCarta.setDisable(true);
            buttonStai.setDisable(true);
        }
        playerHand.setSpacing(-45);
        ImageView carta = new ImageView(generaCarta());
        listaCarte.add(carta);
        carta.setFitHeight(125);
        carta.setPreserveRatio(true);
        playerHand.getChildren().add(carta);
        contaCarte();
        if(somma > 21){
            manoLabel.setText("La tua mano: " + somma + " (Hai sballato!)");
            manoLabel.setTextFill(Color.RED);
            stai();
        }
        if(somma == 21){
            stai();
        }
    }

    public void pescaCartaRaddoppia(){
        contatoreCarte++;
        soldiCorrenti -= valorePuntata;
        soldiLabel.setText(soldiCorrenti + "€");
        stai();
        playerHand.setSpacing(-45);
        ImageView carta = new ImageView(generaCarta());
        carta.setFitHeight(125);
        carta.setPreserveRatio(true);
        playerHand.getChildren().add(carta);
        listaCarte.add(carta);
        contaCarte();
        if(somma > 21){
            manoLabel.setText("La tua mano: " + somma + " (Hai sballato!)");
            manoLabel.setTextFill(Color.RED);
            stai();
        }
    }

    public void stai(){
        buttonRaddoppia.setDisable(true);
        buttonCarta.setDisable(true);
        buttonStai.setDisable(true);
        giraCarta();
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

    private void calcolaValoreCarta(ImageView carta){
        String immagine = carta.getImage().getUrl();
        String nomeFile = immagine.substring(immagine.lastIndexOf("/") + 1);
        String[] parti = nomeFile.split("@");
        String numeroTesto = parti[0].substring(0, 1);
        Integer numeroCarta = 0;
        //System.out.println("Il numero del testo e' " + numeroTesto);
        if(numeroTesto.equals("A") && somma + 11 > 21){
            numeroCarta = 1;
        }else if(numeroTesto.equals("A") && somma + 11 <= 21){
            numeroCarta = 11;
        } else if(numeroTesto.equals("T") || numeroTesto.equals("J")  || numeroTesto.equals("Q")  || numeroTesto.equals("K")){
            numeroCarta = 10;
        } else{
            numeroCarta = Integer.parseInt(numeroTesto);
        }
        //System.out.println(numeroCarta);
        somma += numeroCarta;
    }

    private void contaCarteDealer(){
        sommaDealer = 0;
        for (ImageView carta : listaCarteDealer) {          
            calcolaValoreCartaDealer(carta);
        }
        manoDealerLabel.setText("Mano dealer: " + sommaDealer);
    }

    private void contaCarte(){
        somma = 0;
        for (ImageView carta : listaCarte) {          
            calcolaValoreCarta(carta);
        }
        manoLabel.setText("La tua mano: " + somma);
    }

    public void esci() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Esci");
        alert.setHeaderText("Stai per abbandonare il gioco");
        alert.setContentText("Vuoi davvero uscire?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    private void disable(Node node, boolean disable, int opacity){
        node.setDisable(disable);
        node.setOpacity(opacity);
    }
}