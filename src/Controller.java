import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.geometry.Bounds;
import javafx.animation.*;
import javafx.util.Duration;

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
    public Button buttonProssimaMano;
    public Button button100;
    public Button button250;
    public Button button500;

    public Label soldiLabel;

    public Label manoLabel;

    private ArrayList<ImageView> listaCarteDealer = new ArrayList<ImageView>();
    private ArrayList<ImageView> listaCarte = new ArrayList<ImageView>();

    private Integer soldiCorrenti = 1000; 

    public ImageView cartaMazzo1;

    boolean raddoppio;
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

    public void tornaAlMenu(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Torna al menù");
        alert.setHeaderText("Stai per tornare al menù");
        alert.setContentText("Vuoi davvero continuare?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            somma = 0;
            sommaDealer = 0;
            valorePuntata = 0;
            listaCarte.clear();
            listaCarteDealer.clear();
            playerHand.getChildren().clear();
            dealerHand.getChildren().clear();
            contatoreCarte = 2;
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        }
    }

    public void nuovaPartita(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Nuova partita");
        alert.setHeaderText("Stai per avviare una nuova partita, perderai tutti i tuoi progressi");
        alert.setContentText("Vuoi davvero continuare?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            somma = 0;
            sommaDealer = 0;
            valorePuntata = 0;
            listaCarte.clear();
            listaCarteDealer.clear();
            playerHand.getChildren().clear();
            dealerHand.getChildren().clear();
            contatoreCarte = 2;
            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        }
    }

    public void punta100(){
        if(soldiCorrenti >= 100){
            button100.setDisable(false);
            button500.setDisable(false);
            button250.setDisable(false);
            valorePuntata = 100;
            disable(puntataSelect, true, 0);
            disable(manoLabel, false, 1);
            setPlayerHandVisible();
            setDealerHandVisibile();
        } else {
            button100.setDisable(true);
            button500.setDisable(true);
            button250.setDisable(true);
        }
    }

    public void punta250(){
        if(soldiCorrenti >= 250){
            button500.setDisable(false);
            button250.setDisable(false);
            valorePuntata = 250;
            disable(puntataSelect, true, 0);
            disable(manoLabel, false, 1);
            setDealerHandVisibile();
            setPlayerHandVisible();
        } else {
            button500.setDisable(true);
            button250.setDisable(true);
        }
    }

    public void punta500(){
        if(soldiCorrenti >= 500){
            button500.setDisable(false);
            valorePuntata = 500;
            disable(puntataSelect, true, 0);
            disable(manoLabel, false, 1);
            setDealerHandVisibile();
            setPlayerHandVisible();
        } else {
            button500.setDisable(true);
        }
    }

    public void prossimaMano(ActionEvent event) throws IOException{
        int puntataReale = raddoppio ? valorePuntata * 2 : valorePuntata;

        if (somma > sommaDealer && somma <= 21) {
            soldiCorrenti += puntataReale * 2;
        } 
        else if (sommaDealer > 21 && somma <= 21) {
            soldiCorrenti += puntataReale * 2;
        } 
        else if (somma == sommaDealer && somma <= 21) {
            soldiCorrenti += puntataReale;
        }
        

        soldiLabel.setText(soldiCorrenti + "€");
        if (soldiCorrenti <= 0 || soldiCorrenti < 100) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Hai perso");
            alert.setHeaderText("Hai finito i soldi");
            alert.setContentText("Vuoi cominciare una nuova partita?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                somma = 0;
                sommaDealer = 0;
                valorePuntata = 0;
                listaCarte.clear();
                listaCarteDealer.clear();
                playerHand.getChildren().clear();
                dealerHand.getChildren().clear();
                contatoreCarte = 2;
                Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                somma = 0;
                sommaDealer = 0;
                valorePuntata = 0;
                listaCarte.clear();
                listaCarteDealer.clear();
                playerHand.getChildren().clear();
                dealerHand.getChildren().clear();
                contatoreCarte = 2;
                Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            disable(buttonProssimaMano, true, 0);
            somma = 0;
            sommaDealer = 0;
            valorePuntata = 0;
            listaCarte.clear();
            listaCarteDealer.clear();
            playerHand.getChildren().clear();
            dealerHand.getChildren().clear();
            contatoreCarte = 2;
            buttonCarta.setDisable(false);
            buttonStai.setDisable(false);
            buttonRaddoppia.setDisable(false);
            manoLabel.setTextFill(Color.WHITE);
            manoDealerLabel.setTextFill(Color.WHITE);
            raddoppio = false;
            disable(bottoni, true, 0);
            disable(manoLabel, true, 0);
            disable(manoDealerLabel, true, 0);
            disable(puntataSelect, false, 1);
            if(valorePuntata == 100 || valorePuntata == 250 || valorePuntata == 500){
                setPlayerHandVisible();
                setDealerHandVisibile();
            }
        }
    }


    private void animaCartaVersoPlayer(Image cartaImg) {
        buttonCarta.setDisable(true);
        ImageView cartaAnimata = new ImageView(cartaImg);
        cartaAnimata.setFitHeight(125);
        cartaAnimata.setPreserveRatio(true);

        Pane rootPane = (Pane) playerHand.getScene().getRoot();
        rootPane.getChildren().add(cartaAnimata);

        Bounds mazzoBounds = cartaMazzo1.localToScene(cartaMazzo1.getBoundsInLocal());
        Bounds mazzoRoot = rootPane.sceneToLocal(mazzoBounds);

        cartaAnimata.setLayoutX(mazzoRoot.getMinX());
        cartaAnimata.setLayoutY(mazzoRoot.getMinY());

        Bounds handBounds = playerHand.localToScene(playerHand.getBoundsInLocal());
        Bounds handRoot = rootPane.sceneToLocal(handBounds);

        double targetX = handRoot.getMinX() + handRoot.getWidth() / 2 - cartaAnimata.getFitWidth() / 2;
        double targetY = handRoot.getMinY() + handRoot.getHeight() / 2 - cartaAnimata.getFitHeight() / 2;

        TranslateTransition tt = new TranslateTransition(Duration.millis(500), cartaAnimata);
        tt.setToX(targetX - cartaAnimata.getLayoutX());
        tt.setToY(targetY - cartaAnimata.getLayoutY());

        ScaleTransition st = new ScaleTransition(Duration.millis(500), cartaAnimata);
        st.setFromX(0.3);
        st.setFromY(0.3);
        st.setToX(1);
        st.setToY(1);

        ParallelTransition pt = new ParallelTransition(tt, st);

        pt.setOnFinished(e -> {

            rootPane.getChildren().remove(cartaAnimata);

            ImageView finalCard = new ImageView(cartaImg);
            finalCard.setFitHeight(125);
            finalCard.setPreserveRatio(true);

            listaCarte.add(finalCard);
            playerHand.getChildren().add(finalCard);

            contaCarte();

            if (listaCarte.size() == 2 && somma == 21) {
                int bonus = (int)(valorePuntata * 2.5);
                soldiCorrenti += bonus;
                soldiLabel.setText(soldiCorrenti + "€");

                manoLabel.setText("La tua mano: 21 (BlackJack!)");
                manoLabel.setTextFill(Color.LIGHTGREEN);

                stai();
                return;
            }

            if (somma == 21) {
                manoLabel.setText("La tua mano: 21");
                manoLabel.setTextFill(Color.LIGHTGREEN);
                stai(); 
                return;
            }

            if (somma > 21) {
                manoLabel.setText("La tua mano: " + somma + " (Hai sballato!)");
                manoLabel.setTextFill(Color.RED);
                stai();
                return;
            }

            if (raddoppio) {
                stai();
            }

            if (somma < 21 && !raddoppio) {
                buttonCarta.setDisable(false);
            }
        });

        pt.play();
    }


    private void setPlayerHandVisible(){
        manoLabel.setText("La tua mano: ");
        disable(playerHand, false, 1);
        disable(bottoni, false, 1);
        soldiCorrenti -= valorePuntata;
        playerHand.setSpacing(-45);
        soldiLabel.setText(soldiCorrenti + "€");
        Image first = generaCarta();
        Image second = generaCarta();

        animaCartaVersoPlayer(first);

        PauseTransition pause = new PauseTransition(Duration.millis(300));
        pause.setOnFinished(e -> animaCartaVersoPlayer(second));
        pause.play();

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
            if(sommaDealer > 21){
                break;
            }
        }
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
        Image cartaImg = generaCarta();
        animaCartaVersoPlayer(cartaImg);
    }

    public void pescaCartaRaddoppia(){

        if (soldiCorrenti < valorePuntata) {
            buttonRaddoppia.setDisable(true);
            return;
        }

        raddoppio = true;
        contatoreCarte++;

        soldiCorrenti -= valorePuntata;
        soldiLabel.setText(soldiCorrenti + "€");

        Image cartaImg = generaCarta();
        animaCartaVersoPlayer(cartaImg);

        buttonCarta.setDisable(true);
        buttonRaddoppia.setDisable(true);
    }


    public void stai(){
        buttonRaddoppia.setDisable(true);
        buttonCarta.setDisable(true);
        buttonStai.setDisable(true);
        disable(buttonProssimaMano, false, 1);
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

    private void contaCarteDealer(){
        int numeroAssi = 0;
        sommaDealer = 0;
        for (ImageView carta : listaCarteDealer) {          
            String immagine = carta.getImage().getUrl();
            String nomeFile = immagine.substring(immagine.lastIndexOf("/") + 1);
            String[] parti = nomeFile.split("@");
            String numeroTesto = parti[0].substring(0, 1);
            Integer numeroCarta = 0;
            if(numeroTesto.equals("A")){
                numeroCarta = 11;
                numeroAssi++;
            } else if(numeroTesto.equals("T") || numeroTesto.equals("J")  || numeroTesto.equals("Q")  || numeroTesto.equals("K")){
                numeroCarta = 10;
            } else{
                numeroCarta = Integer.parseInt(numeroTesto);
            }
            sommaDealer += numeroCarta;
            while (sommaDealer > 21 && numeroAssi > 0) {
                sommaDealer -= 10;
                numeroAssi--;
            }
        }
        manoDealerLabel.setText("Mano dealer: " + sommaDealer);
    }

    private void contaCarte(){
        int numeroAssi = 0;
        somma = 0;
        for (ImageView carta : listaCarte) {          
            String immagine = carta.getImage().getUrl();
            String nomeFile = immagine.substring(immagine.lastIndexOf("/") + 1);
            String[] parti = nomeFile.split("@");
            String numeroTesto = parti[0].substring(0, 1);
            Integer numeroCarta = 0;
            if(numeroTesto.equals("A")){
                numeroCarta = 11;
                numeroAssi++;
            } else if(numeroTesto.equals("T") || numeroTesto.equals("J")  || numeroTesto.equals("Q")  || numeroTesto.equals("K")){
                numeroCarta = 10;
            } else{
                numeroCarta = Integer.parseInt(numeroTesto);
            }
            somma += numeroCarta;
            while(somma > 21 && numeroAssi > 0){
                somma -= 10;
                numeroAssi--;
            }
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