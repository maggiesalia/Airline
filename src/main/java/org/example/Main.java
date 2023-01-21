package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
     @Override
    public void start(Stage stage) throws IOException, RuntimeException, NullPointerException {

         javafx.scene.control.Label label = new Label("Create a new flight");
         label.setTranslateX(50);
         label.setTranslateY(100);

         javafx.scene.control.TextField textFieldLocation = new javafx.scene.control.TextField();
         textFieldLocation.setPromptText("Location");
         textFieldLocation.setMaxWidth(120);
         textFieldLocation.setTranslateX(70);
         textFieldLocation.setTranslateY(140);

         javafx.scene.control.TextField textFieldDestination = new javafx.scene.control.TextField();
         textFieldDestination.setPromptText("Destination");
         textFieldDestination.setMaxWidth(120);
         textFieldDestination.setTranslateX(200);
         textFieldDestination.setTranslateY(textFieldLocation.getTranslateY()-25);

         DatePicker datePicker = new DatePicker();
         datePicker.setPromptText("Date");
         datePicker.setMaxWidth(120);
         datePicker.setTranslateX(textFieldDestination.getTranslateX()+140);
         datePicker.setTranslateY(textFieldDestination.getTranslateY()-25);

         javafx.scene.control.TextField textFieldSeat = new javafx.scene.control.TextField();
         textFieldSeat.setPromptText("Num of Seat");
         textFieldSeat.setMaxWidth(100);
         textFieldSeat.setTranslateX(datePicker.getTranslateX()+150);
         textFieldSeat.setTranslateY(datePicker.getTranslateY()-25);

         javafx.scene.control.TextField textFieldPrice = new javafx.scene.control.TextField();
         textFieldPrice.setPromptText("Price");
         textFieldPrice.setMaxWidth(70);
         textFieldPrice.setTranslateX(textFieldSeat.getTranslateX()+150);
         textFieldPrice.setTranslateY(textFieldSeat.getTranslateY()-25);

         javafx.scene.control.Button addButton = new javafx.scene.control.Button("Add Flight");
         addButton.setTranslateX(textFieldPrice.getTranslateX()+160);
         addButton.setTranslateY(textFieldPrice.getTranslateY()-25);


// Table View //
         TableView<Flight> tableView;


         TableColumn<Flight,String> locationColumn = new TableColumn<>("Location");
         locationColumn.setMinWidth(60);
         locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

         TableColumn<Flight,String> destinationColumn = new TableColumn<>("Destination");
         destinationColumn.setMinWidth(60);
         destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

         TableColumn<Flight, Date> dateColumn = new TableColumn<>("Date");
         dateColumn.setMinWidth(60);
         dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

         TableColumn<Flight, Integer> seatColumn = new TableColumn<>("Number of seat");
         seatColumn.setMinWidth(60);
         seatColumn.setCellValueFactory(new PropertyValueFactory<>("seat"));

         TableColumn<Flight, Integer> priceColumn = new TableColumn<>("Price");
         priceColumn.setMinWidth(60);
         priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

         tableView = new TableView<>();
         tableView.setItems(getProduct());
         tableView.getColumns().addAll(locationColumn,destinationColumn,dateColumn,seatColumn,priceColumn);
         tableView.setMaxWidth(600);
         tableView.setTranslateX(50);
         tableView.setTranslateY(50);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("app-view.fxml"));
        Parent root = fxmlLoader.load();

         VBox vbox = new VBox();
         vbox.getChildren().addAll(tableView,label,textFieldLocation,textFieldDestination,datePicker,textFieldSeat,textFieldPrice,addButton);

         Scene scene = new Scene(vbox, 1200, 900);
         stage.setTitle("Book_AirLines");
         stage.setScene(scene);
         stage.show();

         addButton.setOnAction(e -> {
             Flight flight = new Flight();
             flight.setLocation(textFieldLocation.getText());
             flight.setDestination(textFieldDestination.getText());
             flight.setDate(Date.valueOf(datePicker.getValue()));
             flight.setSeat(Integer.parseInt(textFieldSeat.getText()));
             flight.setPrice(Integer.parseInt(textFieldPrice.getText()));

             textFieldLocation.setText("");
             textFieldDestination.setText("");
             textFieldSeat.setText("");
             textFieldPrice.setText("");

             insert(flight);
             try {
                 start(stage);
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             }
         });
     }

    public static void main(String[] args) {
        launch();
    }

    public void insert(Flight flight) {
        String insertsql = "INSERT INTO flight3(location, destination, date, seat, price) VALUES(" +
                "'" + flight.getLocation() + "', " +
                "'" + flight.getDestination() + "', " +
                "'" + flight.getDate() + "', " +
                "'" + flight.getSeat() + "', " +
                "" + flight.getPrice() + ")";

        try {
            Jdbc.getStatement().executeUpdate(insertsql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Flight> getProduct() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
//        flights.add(new Flight("Tbilisi","London",datePicker,20,40));
//        flights.add(new Flight("Tbilisi","Prague",datePicker,55,99));

        String selectsql = "SELECT * FROM flight3";

        try {
            ResultSet resultSet = Jdbc.getStatement().executeQuery(selectsql);

            while (resultSet.next()) {
                flights.add(new Flight(
                        resultSet.getString("location"),
                        resultSet.getString("destination"),
                        resultSet.getDate("date"),
                        resultSet.getInt("seat"),
                        resultSet.getInt("price")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flights;
    }

}