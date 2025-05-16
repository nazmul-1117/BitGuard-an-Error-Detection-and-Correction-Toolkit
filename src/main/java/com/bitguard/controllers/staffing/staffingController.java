package com.bitguard.controllers.staffing;

import com.bitguard.models.stuffDeStuff.BitStuffingDeStuffing;
import com.bitguard.models.stuffDeStuff.ByteStuffDeStuff;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class staffingController implements Initializable {
    public JFXRadioButton bitRB;
    public JFXRadioButton byteRB;
    public JFXRadioButton stuffingRB;
    public JFXRadioButton deStuffingRB;
    public JFXTextArea inputDataTA;
    public JFXTextArea resultDataTA;
    public Label errorMessageLB;
    public Pane errorPicPane;

    private String actualData, stuffType, method;
    private String resultData;

    private final ToggleGroup stuffTypeToggleGroup = new ToggleGroup();
    private final ToggleGroup methodToggleGroup = new ToggleGroup();

    private void initToggle() {

        bitRB.setToggleGroup(stuffTypeToggleGroup);
        byteRB.setToggleGroup(stuffTypeToggleGroup);

        stuffingRB.setToggleGroup(methodToggleGroup);
        deStuffingRB.setToggleGroup(methodToggleGroup);
    }



    private void collectData(){

        actualData = inputDataTA.getText().trim().toUpperCase();
        stuffType = ((JFXRadioButton) stuffTypeToggleGroup.getSelectedToggle()).getText();
        method = ((JFXRadioButton) methodToggleGroup.getSelectedToggle()).getText();

    }

    private void displayData(){
        System.out.println("Actual Data: " + actualData);
        System.out.println("Stuff Type: " + stuffType);
        System.out.println("Method: " + method);
    }

    private void setData(){

        errorPicPane.getStyleClass().removeAll();
        errorPicPane.getStyleClass().remove("typing-image");

        String errorMessage = stuffType + " " + method + " " + "Successfully Completed 😋";

        resultDataTA.setText(resultData);
        errorMessageLB.setText(errorMessage);
        errorPicPane.getStyleClass().add("kiss-image");

    }


    private void algorithmApply(){
        resultData = "NULL DATA";

        if(stuffType.toLowerCase().equals("bit")){
            if (method.toLowerCase().equals("stuffing")){
                resultData = BitStuffingDeStuffing.stuff(actualData);
                System.out.println("Welcome to Bit Stuffing: " + resultData);

            }else {
                System.out.println("Welcome to Bit De-Stuffing");
                resultData = BitStuffingDeStuffing.destuff(actualData);
            }

        }else {
            if (method.toLowerCase().equals("stuffing")){
                System.out.println("Welcome to Byte Stuffing");
                resultData = ByteStuffDeStuff.stuff(actualData);

            }else {
                System.out.println("Welcome to Byte De-Stuffing");
                resultData = ByteStuffDeStuff.destuff(actualData);
            }
        }
    }


    public void sendButton(ActionEvent actionEvent) {
        collectData();
        displayData();
        algorithmApply();
        setData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggle();
        errorPicPane.getStyleClass().add("typing-image");

    }
}
