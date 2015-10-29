package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Ivan on 29.10.2015..
 */

@Table(name = "Categories")
public class Category extends Model {

    @Column(name="name", index=true)
    private String name;

    @Column(name="description")
    private String description;

    public Category(){
        super();
    }

    public Category(String name, String description) {
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
}
