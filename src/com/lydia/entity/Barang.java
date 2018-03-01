/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lydia (1672014)
 */
public class Barang {

    private final IntegerProperty kd_Barang = new SimpleIntegerProperty();
    private final StringProperty nm_Barang = new SimpleStringProperty();
    private final IntegerProperty hrg_Jual = new SimpleIntegerProperty();
    private final IntegerProperty hrg_Beli = new SimpleIntegerProperty();
    private final ObjectProperty<Kategori> kategori_Id_Kategori
            = new SimpleObjectProperty<>();
    private final IntegerProperty stock = new SimpleIntegerProperty();

    public Barang(int kd_Barang) {
        this.setKd_Barang(kd_Barang);

    }

    public Barang() {
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int value) {
        stock.set(value);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getKd_Barang() {
        return kd_Barang.get();
    }

    public void setKd_Barang(int value) {
        kd_Barang.set(value);
    }

    public IntegerProperty kd_BarangProperty() {
        return kd_Barang;
    }

    public String getNm_Barang() {
        return nm_Barang.get();
    }

    public void setNm_Barang(String value) {
        nm_Barang.set(value);
    }

    public StringProperty nm_BarangProperty() {
        return nm_Barang;
    }

    public int getHrg_Jual() {
        return hrg_Jual.get();
    }

    public void setHrg_Jual(int value) {
        hrg_Jual.set(value);
    }

    public IntegerProperty hrg_JualProperty() {
        return hrg_Jual;
    }

    //
    public int getHrg_Beli() {
        return hrg_Beli.get();
    }

    public void setHrg_Beli(int value) {
        hrg_Beli.set(value);
    }

    public IntegerProperty hrg_BeliProperty() {
        return hrg_Beli;
    }

    //
    public Kategori getKategori_Id_Kategori() {
        return kategori_Id_Kategori.get();
    }

    public void setKategori_Id_Kategori(Kategori value) {
        kategori_Id_Kategori.set(value);
    }

    public ObjectProperty kategori_Id_KategoriProperty() {
        return kategori_Id_Kategori;
    }

    @Override
    public String toString() {
        return String.valueOf(getKd_Barang()) + "-" + getNm_Barang();
    }

}
