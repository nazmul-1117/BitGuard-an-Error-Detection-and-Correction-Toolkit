package com.bitguard.controllers.hamming;

import com.bitguard.models.hammingModel.HammingModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;

public class hammingController implements Initializable {

    public TextField senderDataTF;
    public JFXRadioButton senderParityEvenRB;
    public JFXRadioButton senderParityOddRB;
    public JFXCheckBox senderErrorCB;
    public TextField senderErrorIndexTF;
    public TextField receiverDataTF;
    public Label errorMessage;
    public JFXRadioButton encodingRB;
    public JFXRadioButton decodingRB;

    private String data, errorIndex, parity, codingType;
    private String encodedData, decodedData;
    private final ToggleGroup parityToggleGroup = new ToggleGroup();
    private final ToggleGroup codingTypeToggleGroup = new ToggleGroup();

    private void initToggle() {
        senderParityEvenRB.setToggleGroup(parityToggleGroup);
        senderParityOddRB.setToggleGroup(parityToggleGroup);

        encodingRB.setToggleGroup(codingTypeToggleGroup);
        decodingRB.setToggleGroup(codingTypeToggleGroup);
    }

    private void collectData(){
        data = senderDataTF.getText();
        parity = ((JFXRadioButton) parityToggleGroup.getSelectedToggle()).getText();
        errorIndex = senderErrorIndexTF.getText();
        codingType = ((JFXRadioButton) codingTypeToggleGroup.getSelectedToggle()).getText();

        printData();
    }

    private void setData(){

//        receiverDataTF.setStyle("-fx-font-size: 24px;");
        receiverDataTF.setText(encodedData);
        receiverDataTF.setFont(new Font(36));

        errorMessage.setFont(new Font(36));
        errorMessage.setText("No Error Founded..! 🥰");
    }

    private void algorithmRun(){
        encodedData = HammingModel.encodeHammingCode(data, parity.toLowerCase());
        decodedData = HammingModel.detectAndCorrectErrors(data, parity.toLowerCase());
    }

    private void printData(){
        System.out.println("Data: " + data);
        System.out.println("Parity: " + parity);
        System.out.println("Error Index: " + errorIndex);
        System.out.println("Coding Type: " + codingType);

        algorithmRun();

        System.out.println("Encode: " + encodedData);
        System.out.println("Decode: " + decodedData);

        setData();
    }


    public void sendButton(ActionEvent actionEvent) {

        collectData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggle();
    }
}
