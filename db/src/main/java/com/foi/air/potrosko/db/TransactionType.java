package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ivan on 29.10.2015..
 */

@Table(name="TransactionType")
public class TransactionType extends Model {

    @Column(name="name", index=true)
    private String name;

    @Column(name="description")
    private String description;

    public TransactionType(){
        super();
    }

    public TransactionType(String name, String description) {
        this.name = name;
        this.description = description;
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

    // popis svih kategorija
    public static List<TransactionType> getAll() {
        List<TransactionType> transactionTypes = new Select().from(Category.class).orderBy("name ASC").execute();
        return transactionTypes;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Name: "
                + getName()
                + " Description: "
                + getDescription()
                + "\n";
    }

    public static TransactionType getType(String name) {
        return new Select()
                .from(TransactionType.class)
                .where("name = ?", name)
                .executeSingle();
    }
}
