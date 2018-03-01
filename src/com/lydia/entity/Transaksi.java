/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.entity;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Lydia (1672014)
 */
public class Transaksi {

    private final IntegerProperty kd_Transaksi = new SimpleIntegerProperty();
    private final ObjectProperty<Timestamp> tgl_Transaksi
            = new SimpleObjectProperty<>();
    private final IntegerProperty pembayaran = new SimpleIntegerProperty();
    private final ObjectProperty<User> user_Kd_User
            = new SimpleObjectProperty<>();

    public int getKd_Transaksi() {
        return kd_Transaksi.get();
    }

    public void setKd_Transaksi(int value) {
        kd_Transaksi.set(value);
    }

    public IntegerProperty Kd_TransaksiProperty() {
        return kd_Transaksi;
    }

    public Timestamp getTgl_Transaksi() {
        return tgl_Transaksi.get();
    }

    public void setTgl_Transaksi(Timestamp value) {
        tgl_Transaksi.set(value);
    }

    public ObjectProperty tgl_TransaksiProperty() {
        return tgl_Transaksi;
    }

    public int getPembayaran() {
        return pembayaran.get();
    }

    public void setPembayaran(int value) {
        pembayaran.set(value);
    }

    public IntegerProperty pembayaranProperty() {
        return pembayaran;
    }

    public User getUser_Kd_User() {
        return user_Kd_User.get();
    }

    public void setUser_Kd_User(User value) {
        user_Kd_User.set(value);
    }

    public ObjectProperty user_Kd_UserProperty() {
        return user_Kd_User;
    }

    @Override
    public String toString() {
        return String.valueOf(getKd_Transaksi());
    }

}
