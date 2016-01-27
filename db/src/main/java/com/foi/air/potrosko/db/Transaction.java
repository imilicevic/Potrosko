package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 29.10.2015..
 */

@Table(name = "Transactions")
public class Transaction extends Model{

    @Column(name = "TransactionType")
    private TransactionType transactionType;

    @Column(name = "Category")
    public Category category;

    @Column(name = "name", index = true)
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "note")
    private String note;

    @Column(name = "amount")
    private double amount;

    public Transaction(){
        super();
    }

    public Transaction(TransactionType transactionType, Category category, String name, String date, String note, String attachment, double amount) {
        super();
        this.transactionType = transactionType;
        this.category = category;
        this.name = name;
        this.date = date;
        this.note = note;
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType){
        this.transactionType = transactionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() { return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountString() {
        return String.valueOf(amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // popis svih kategorija
    public static List<Transaction> getAll() {
        List<Transaction> transactions = new Select().from(Transaction.class).orderBy("date DESC").execute();
        return transactions;
    }



    @Override
    public String toString() {
        return "Name: "
                + getName()
                + " Amount: "
                + getAmount()
                + " Note: "
                + getNote()
                +" Transaction type: "
                + getTransactionType()
                + " Datum: " + getDate()
                + " Category" + Category.getCategory("car")
                + "\n";
    }
/*
    public static Transaction getTr(String textCategory, String textAmount, String textNote, String textDate){
        return new Select()
                .from(Transaction.class)
                .where("  category  = ?", textCategory)
                .and("  amount  = ?", textAmount)
                .and("  note  = ?", textNote)
                .and("  date  = ?",  textDate )
                .executeSingle();

    }
*/

}
