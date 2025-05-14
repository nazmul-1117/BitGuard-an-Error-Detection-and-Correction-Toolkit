package com.bitguard.controllers.crc;

import com.bitguard.helper.BinaryCheck;
import com.bitguard.models.crcModel.CRCModel;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class crcController implements Initializable {
    public TextField crcDataTF;
    public TextField crcKeyTF;
    public JFXCheckBox senderErrorCB;
    public JFXRadioButton encodingRB;
    public JFXRadioButton decodingRB;
    public TextField receiverDataTF;
    public Label errorMessage;
    public Pane errorPicPane;

    private String data, key, codingType;
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
        codingType = ((JFXRadioButton) codingTypeToggleGroup.getSelectedToggle()).getText();
    }

    private void printData(){
        System.out.println("Data: " + data);
        System.out.println("Key: " + key);
        System.out.println("Coding Type: " + codingType);
    }

    private void crcAlgorithmCall(){

        encodedData = "NaN";
        if(!BinaryCheck.validBinary(data)){
            encodedData = "Not a Valid Input";
            return;
        }

         if (codingType.equalsIgnoreCase("encoding")){
             encodedData = CRCModel.encodeData(data, key);

         }else {
             encodedData = "Error 404.\n Decoding Coming Soon...";
             validEncode = false;
             return;
         }

         if (senderErrorCB.isSelected()){
             char[] tampered = data.toCharArray();
             tampered[2] = (tampered[2] == '1') ? '0' : '1';
             String received = new String(tampered);

             encodedData = received;
         }

         validEncode = CRCModel.checkData(encodedData, key);
    }

    private void setData(){
        errorPicPane.getStyleClass().removeAll();
        errorPicPane.getStyleClass().remove("typing-image");

        receiverDataTF.setText(encodedData);
        errorMessage.setText(validEncode ? "No error detected. 😘" : "Error detected! 😑");

        if (validEncode){
            errorPicPane.getStyleClass().remove("slap-image");
            errorPicPane.getStyleClass().add("kiss-image");
        }else {
            errorPicPane.getStyleClass().remove("kiss-image");
            errorPicPane.getStyleClass().add("slap-image");
        }
    }

    public void sendButton(ActionEvent actionEvent) {
        collectData();
        printData();
        crcAlgorithmCall();
        setData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggle();
        errorPicPane.getStyleClass().add("typing-image");
    }
}
