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
public class Kategori {

    private final IntegerProperty id_Kategori = new SimpleIntegerProperty();

    public int getId_Kategori() {
        return id_Kategori.get();
    }

    public void setId_Kategori(int value) {
        id_Kategori.set(value);
    }

    public IntegerProperty id_KategoriProperty() {
        return id_Kategori;
    }

    private final StringProperty ket_Kategori = new SimpleStringProperty();

    public String getKet_Kategori() {
        return ket_Kategori.get();
    }

    public void setKet_Kategori(String value) {
        ket_Kategori.set(value);
    }

    public StringProperty ket_KategoriProperty() {
        return ket_Kategori;
    }

    @Override
    public String toString() {
        return getKet_Kategori();
    }

}
