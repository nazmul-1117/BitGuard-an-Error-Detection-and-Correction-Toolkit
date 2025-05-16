package com.bitguard.controllers.hamming;

import com.bitguard.helper.BinaryCheck;
import com.bitguard.models.hammingModel.HammingModel;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;

public class hammingController implements Initializable {

    public TextField senderDataTF;
    public JFXRadioButton senderParityEvenRB;
    public JFXRadioButton senderParityOddRB;
    public TextField receiverDataTF;
    public Label errorMessage;
    public JFXRadioButton encodingRB;
    public JFXRadioButton decodingRB;
    public Pane errorPic;

    private String data, errorType, parity, transferType;
    private String resultData;
    private final ToggleGroup parityToggleGroup = new ToggleGroup();
    private final ToggleGroup transferTypeToggleGroup = new ToggleGroup();

    private void initToggle() {
        senderParityEvenRB.setToggleGroup(parityToggleGroup);
        senderParityOddRB.setToggleGroup(parityToggleGroup);

        encodingRB.setToggleGroup(transferTypeToggleGroup);
        decodingRB.setToggleGroup(transferTypeToggleGroup);
    }

    private void collectData(){
        data = senderDataTF.getText();
        parity = ((JFXRadioButton) parityToggleGroup.getSelectedToggle()).getText().toLowerCase();
        transferType = ((JFXRadioButton) transferTypeToggleGroup.getSelectedToggle()).getText().toLowerCase();
    }

    private void setData(){

        errorPic.getStyleClass().remove("typing-image");

        receiverDataTF.setText(resultData);
        receiverDataTF.setFont(new Font(36));

        errorMessage.setFont(new Font(36));
        errorMessage.setText("No Error Founded..!😍");

        if (resultData.equals("NULL")){
            errorMessage.setText("Please Enter Valid Input");
        }

        errorPic.getStyleClass().add("kiss-image");;
    }

    private void algorithmRun(){

        resultData = "NaN";

        if(!BinaryCheck.validBinary(data)){
            resultData = "Not Valid Input";
            return;
        }

        if(transferType.equals("encoding")){
            resultData = HammingModel.encodeHammingCode(data, parity);
        }else {
            if(data.length() < 7){
                resultData = "NULL";
                return;
            }
            resultData = HammingModel.detectAndCorrectErrors(data, parity);
            resultData = HammingModel.extractDataBits(resultData);
        }
    }

    private void printData(){
        System.out.println("Data: " + data);
        System.out.println("Parity Type: " + parity);
        System.out.println("Error Index: " + errorType);
        System.out.println("Transfer Type: " + transferType);
    }

    public void sendButton(ActionEvent actionEvent) {
        collectData();
        printData();
        algorithmRun();
        setData();

        System.out.println("Result: " + resultData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggle();
        errorPic.getStyleClass().add("typing-image");
    }
}
