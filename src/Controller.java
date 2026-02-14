import javafx.fxml.FXML;
import javafx.scene.image.*;

public class Controller {
    @FXML

    ImageView myImageView;

    Image myImage = new Image(getClass().getResourceAsStream("back.png"));
    Image myImage2 = new Image(getClass().getResourceAsStream("AS@1x.png"));

    public void displayImage() {
        if(myImageView.getImage().equals(myImage)){
            myImageView.setImage(myImage2);
        } else {
            myImageView.setImage(myImage);
        }
    }    
}
