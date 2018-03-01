/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lydia (1672014)
 */
public class Role {

    private final IntegerProperty id_Role = new SimpleIntegerProperty();

    public int getId_Role() {
        return id_Role.get();
    }

    public void setId_Role(int value) {
        id_Role.set(value);
    }

    public IntegerProperty id_RoleProperty() {
        return id_Role;
    }

    private final StringProperty ket_Role = new SimpleStringProperty();

    public String getKet_Role() {
        return ket_Role.get();
    }

    public void setKet_Role(String value) {
        ket_Role.set(value);
    }

    public StringProperty ket_RoleProperty() {
        return ket_Role;
    }

    @Override
    public String toString() {
        return getKet_Role();
    }

}
