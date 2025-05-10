package com.bitguard.controllers.manual;

import com.bitguard.models.manual.Manual;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class manualController implements Initializable {
    public Label hammingCodeLB;
    public Label crcLB;
    public Label checksumLB;
    public Label stuffLB;


    private String hammingManual, crcManual, checksumManual, stuffManual;

    private void setData(){

        hammingManual = Manual.hammingCode();
        hammingCodeLB.setText(hammingManual);

        crcManual = Manual.crc();
        crcLB.setText(crcManual);

        checksumManual = Manual.checksum();
        checksumLB.setText(checksumManual);

        stuffManual = Manual.stuffing();
        stuffLB.setText(stuffManual);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();

    }
}
