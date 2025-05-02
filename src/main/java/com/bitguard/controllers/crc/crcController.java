package com.bitguard.controllers.crc;

import com.bitguard.models.crcModel.CRCModel;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class crcController implements Initializable {
    public TextField crcDataTF;
    public TextField crcKeyTF;
    public JFXCheckBox senderErrorCB;
    public TextField senderErrorIndexTF;
    public JFXRadioButton encodingRB;
    public JFXRadioButton decodingRB;
    public TextField receiverDataTF;
    public Label errorMessage;

    private String data, errorIndex, key, codingType;
    private String encodedData, decodedData;
    private boolean validEncode;


    private final ToggleGroup codingTypeToggleGroup = new ToggleGroup();



    private void initToggle() {

        encodingRB.setToggleGroup(codingTypeToggleGroup);
        decodingRB.setToggleGroup(codingTypeToggleGroup);
    }

    private void collectData(){
        data = crcDataTF.getText();
        key = crcKeyTF.getText();
        errorIndex = senderErrorIndexTF.getText();
        codingType = ((JFXRadioButton) codingTypeToggleGroup.getSelectedToggle()).getText();

        printData();
    }

    private void printData(){
        System.out.println("Data: " + data);
        System.out.println("Key: " + key);
        System.out.println("Error Index: " + errorIndex);
        System.out.println("Coding Type: " + codingType);

        crcAlgorithmCall();
    }

    private void crcAlgorithmCall(){
         encodedData = CRCModel.encodeData(data, key);
         validEncode = CRCModel.checkData(encodedData, key);

         setData();
    }

    private void setData(){
        receiverDataTF.setText("Encoded Data: " + encodedData);
        errorMessage.setText(validEncode ? "No error detected. 😘" : "Error detected! 😑");
    }

    public void sendButton(ActionEvent actionEvent) {
        collectData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggle();
    }
}
