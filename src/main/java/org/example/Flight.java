package org.example;

import java.util.Date;

public class Flight {

    private String location;
    private String destination;
    private Date date;
    private int seat;
    private int price;

    public Flight(String location, String destination, java.sql.Date date, int seat, int price) {
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.seat = seat;
        this.price = price;
    }

    public Flight() {}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public int getSeat() {return seat;}
    public void setSeat(int seat) {this.seat = seat;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}



}
