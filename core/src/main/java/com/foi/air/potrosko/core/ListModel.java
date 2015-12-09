package com.foi.air.potrosko.core;

/**
 * Created by Marko Plaftarić on 02-Dec-15.
 */

public class ListModel {
    //private String image="";
    private String category="";
    private String note="";
    private double amount=0;
    private String date="";

    //public String getImage() {return image;}

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountString() {
        return String.valueOf(amount);
    }

    public String getDate() {
        return date;
    }

    //public void setImage(String image) {this.image = image;}

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
