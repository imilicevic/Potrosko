package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ivan on 29.10.2015..
 */

@Table(name = "Categories")
public class Category extends Model {

    @Column(name="name", index=true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="categoryImg")
    private String categoryImg;

    @Column(name = "TransactionType")
    private TransactionType transactionType;

    public Category(){
        super();
    }

    public Category(String name, String description, TransactionType transactionType) {
        super();
        this.name = name;
        this.description = description;
        this.transactionType = transactionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryImg(String description) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setTransactionType(TransactionType transactionType){
        this.transactionType = transactionType;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }

    // popis svih kategorija
    public static List<Category> getAll() {
        List<Category> categories = new Select().from(Category.class).orderBy("name ASC").execute();
        return categories;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Name: "
                + getName()
                + " Description: "
                + getDescription() +
                " Transaction type: "
                + getTransactionType()
                + "\n";
    }

    public static Category getCategory(String name){
        return new Select()
                .from(Category.class)
                .where("name = ?", name)
                .executeSingle();
    }



}
