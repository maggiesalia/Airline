package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.awt.*;

public class Controller {
    @FXML

    private Pane pane;

    private TextField textFieldLocation;
    private TextField textFieldDestination;
    private DatePicker datePickerDate;
    private int textFieldSeats;
    private int textFieldPrice;
    private TableView<Flight> tableView;

    public Controller(){

    }

    public void setTextFieldLocation(TextField textFieldLocation) {this.textFieldLocation = textFieldLocation;}
    public void setTextFieldDestination(TextField textFieldDestination){
        this.textFieldDestination = textFieldDestination;
    }
    public void setDatePickerDate(DatePicker datePickerDate) {this.datePickerDate = datePickerDate;}


    public String getTextFieldLocation() {return textFieldLocation.getText();}
    public String getTextFieldDestination() {return textFieldDestination.getText();}
    public DatePicker getDatePickerDate() {return datePickerDate;}
    public int getTextFieldSeats() {return textFieldSeats;}
    public int getTextFieldPrice() {return textFieldPrice;}

    public void setTextFieldSeats(int textFieldSeats) {this.textFieldSeats = textFieldSeats;}
    public void setTextFieldPrice(int textFieldPrice) {this.textFieldPrice = textFieldPrice;}

//    @FXML
//    protected void onAddButtonClick() {
//
//        setTextFieldLocation(textFieldLocation);
//        setTextFieldDestination(textFieldDestination);
//        setDatePickerDate(datePickerDate);
//        setTextFieldSeats(textFieldSeats);
//        setTextFieldPrice(textFieldPrice);
//
//        Flight flight = new Flight(getTextFieldLocation().toString(), getTextFieldDestination().toString(),
//                getDatePickerDate(), getTextFieldSeats(), getTextFieldPrice());
//
//        tableView.getItems().add(flight);
//    }
}
