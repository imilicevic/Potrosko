package com.foi.air.potrosko.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Ivan on 29.10.2015..
 */

@Table(name="User")
public class User extends Model {

    @Column(name="name", index=true)
    private String name;

    @Column(name="pin")
    private String pin;

    public User(){
        super();
    }

    public User(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
