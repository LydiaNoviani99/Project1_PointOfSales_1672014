/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.entity;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Lydia (1672014)
 */
public class Detail_transaksi {

    private final ObjectProperty<Date> tgl_Transaksi
            = new SimpleObjectProperty<>();

    public Date getTgl_Transaksi() {
        return tgl_Transaksi.get();
    }

    public void setTgl_Transaksi(Date value) {
        tgl_Transaksi.set(value);
    }

    public ObjectProperty tgl_TransaksiProperty() {
        return tgl_Transaksi;
    }

    private final ObjectProperty<Transaksi> transaksi_kd_Transaksi
            = new SimpleObjectProperty<>();
    private final ObjectProperty<Barang> barang_kd_Barang
            = new SimpleObjectProperty<>();
    private final IntegerProperty jml = new SimpleIntegerProperty();
    private final IntegerProperty saling_price = new SimpleIntegerProperty();

    public Transaksi getTransaksi_kd_Transaksi() {
        return transaksi_kd_Transaksi.get();
    }

    public void setTransaksi_kd_Transaksi(Transaksi value) {
        transaksi_kd_Transaksi.set(value);
    }

    public ObjectProperty transaksi_kd_TransaksiProperty() {
        return transaksi_kd_Transaksi;
    }

    public Barang getBarang_kd_Barang() {
        return barang_kd_Barang.get();
    }

    public void setBarang_kd_Barang(Barang value) {
        barang_kd_Barang.set(value);
    }

    public ObjectProperty barang_kd_BarangProperty() {
        return barang_kd_Barang;
    }

    public int getJml() {
        return jml.get();
    }

    public void setJml(int value) {
        jml.set(value);
    }

    public IntegerProperty jmlProperty() {
        return jml;
    }

    public int getSaling_price() {
        return saling_price.get();
    }

    public void setSaling_price(int value) {
        saling_price.set(value);
    }

    public IntegerProperty saling_priceProperty() {
        return saling_price;
    }

    public Detail_transaksi() {
    }

    public Detail_transaksi(Integer jml, Integer saling_price,
            Barang kdBarang) {

        this.setJml(jml);
        this.setSaling_price(saling_price);
        this.setBarang_kd_Barang(kdBarang);

    }

}
