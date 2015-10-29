package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

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
    private Date date;

    @Column(name = "note")
    private String note;

    @Column(name = "attachment")
    private String attachment;

    public Transaction(){
        super();
    }

    public Transaction(TransactionType transactionType, Category category, String name, Date date, String note, String attachment) {
        this.transactionType = transactionType;
        this.category = category;
        this.name = name;
        this.date = date;
        this.note = note;
        this.attachment = attachment;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
