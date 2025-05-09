package com.bitguard.controllers.checksum;

import com.bitguard.helper.BinaryCheck;
import com.bitguard.helper.DataTypeChanged;
import com.bitguard.models.checksum.ChecksumModel;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChecksumController implements Initializable {


    public TextField checksumDataTF;
    public JFXCheckBox senderErrorCB;
    public TextField senderErrorIndexTF;
    public TextField receiverDataTF;
    public Label errorMessage;
    public TextField segmentSizeTF;

    private String actualData;
    private String errorIndex;

    private String encodeMessage;
    private boolean verifyMessage;
    private int segmentSize;


    private void collectData(){
        actualData = checksumDataTF.getText().trim();
        errorIndex = senderErrorIndexTF.getText().trim();
        segmentSize = DataTypeChanged.stringToInt(segmentSizeTF.getText().trim());
        dataCorrection();
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
//        errorMessage.setText(errorIndex);
        if (verifyMessage){
            errorMessage.setText("No error😣");
        }else {
            errorMessage.setText("Error...!😏");
        }
    }

    private void applyChecksum(){

        encodeMessage = ChecksumModel.calculateChecksum(actualData, segmentSize);
        verifyMessage = ChecksumModel.verifyData(actualData+encodeMessage, segmentSize);
    }

    public void sendButton(ActionEvent actionEvent) {
        collectData();
        displayData();
        applyChecksum();
        setData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
