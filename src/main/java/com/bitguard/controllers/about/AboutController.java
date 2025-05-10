package com.bitguard.controllers.about;

import com.bitguard.models.aboutUs.AboutUs;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    public Label aboutUsIntroLabel;
    public Label nazmulIntro;
    public Label fuadIntro;


    private void setText(){
        String intro = AboutUs.aboutIntro();
        aboutUsIntroLabel.setText(intro);

        String aboutNazmul = AboutUs.aboutNazmul();
        nazmulIntro.setText(aboutNazmul);

        String aboutFuad = AboutUs.aboutFuad();
        fuadIntro.setText(aboutFuad);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setText();
    }
}
