package tn.edu.esprit.gui;
/*
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;

import java.net.URL;
import java.util.ResourceBundle;

public class FaceController implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Button capturer;

    private VideoCapture capture;
    private Mat currentFrame;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Assurez-vous de charger la bibliothèque OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Initialise la capture vidéo depuis la caméra (utilisez la caméra 0 par défaut)
        capture = new VideoCapture(0);
        currentFrame = new Mat();
        capture.read(currentFrame);
    }

    @FXML
    private void capturer(ActionEvent event) {
        if (capture.isOpened()) {
            Mat frame = new Mat();
            capture.read(frame);

            if (!frame.empty()) {
                // Sauvegarde l'image capturée (vous pouvez enregistrer où vous le souhaitez)
                String imageFileName = "captured_image.png";
                Highgui.imwrite(imageFileName, frame);

                // Affiche l'image dans l'ImageView
                Image capturedImage = new Image("file:" + imageFileName);
                image.setImage(capturedImage);
            }
        }
    }

    public void stop() {
        // Libère les ressources de la capture vidéo avant de fermer l'application
        if (capture.isOpened()) {
            capture.release();
        }
    }
}
*/