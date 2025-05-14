package com.bitguard.controllers.checksum;

import com.bitguard.helper.BinaryCheck;
import com.bitguard.helper.DataTypeChanged;
import com.bitguard.models.checksum.ChecksumModel;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChecksumController implements Initializable {


    public TextField checksumDataTF;
    public JFXCheckBox senderErrorCB;
    public TextField receiverDataTF;
    public Label errorMessage;
    public TextField segmentSizeTF;
    public Pane errorPicPane;

    private String actualData;
    private String errorIndex;

    private String encodeMessage;
    private boolean verifyMessage;
    private int segmentSize;


    private void collectData(){
        actualData = checksumDataTF.getText().trim();
        segmentSize = DataTypeChanged.stringToInt(segmentSizeTF.getText().trim());
    }

    private void dataCorrection(){
        int dataLength = actualData.length();

        if((dataLength%segmentSize) == 0){
            System.out.println("All are Okay.. "  + actualData);
            return;
        }

        StringBuffer str = new StringBuffer(actualData);
        int missingDataLength = segmentSize-(dataLength%segmentSize);

        for (int i=0; i<missingDataLength; i++){
            str.insert(0, '0');
        }
        System.out.println("Input Data: " + actualData);
        actualData = str.toString();
        System.out.println("Converted Data: " + actualData);
        System.out.println("Missing Length: " + missingDataLength);
    }

//    for troubleshooting
    private void displayData(){
        System.out.println(actualData);
        System.out.println(errorIndex);
        System.out.println(segmentSize);
    }

    private void setData(){
        receiverDataTF.setText(actualData+encodeMessage);

        errorPicPane.getStyleClass().removeAll();
        errorPicPane.getStyleClass().remove("typing-image");

        if (verifyMessage){
            errorMessage.setText("No Error Detected😣");
            errorPicPane.getStyleClass().remove("slap-image");
            errorPicPane.getStyleClass().add("kiss-image");

        }else {
            errorMessage.setText("Error Detected...!😏");
            errorPicPane.getStyleClass().remove("kiss-image");
            errorPicPane.getStyleClass().add("slap-image");
        }
    }

    private void applyChecksum(){

        encodeMessage = "NaN";
        if(!BinaryCheck.validBinary(actualData)){
            encodeMessage = "Not a Valid Input";
            verifyMessage = false;
            return;
        }

        encodeMessage = ChecksumModel.calculateChecksum(actualData, segmentSize);

        if (senderErrorCB.isSelected()){
            char[] tampered = actualData.toCharArray();
            tampered[2] = (tampered[2] == '1') ? '0' : '1';
            String received = new String(tampered);

            encodeMessage = received;
        }

        verifyMessage = ChecksumModel.verifyData(actualData+encodeMessage, segmentSize);
    }

    public void sendButton(ActionEvent actionEvent) {

        collectData();
        dataCorrection();
        displayData();
        applyChecksum();
        setData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorPicPane.getStyleClass().add("typing-image");
    }
}
