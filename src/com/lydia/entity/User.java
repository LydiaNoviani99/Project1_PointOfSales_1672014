
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
public class User {

    private final IntegerProperty kd_User = new SimpleIntegerProperty();
    private final StringProperty nm_User = new SimpleStringProperty();
    private final StringProperty jenis_kelamin = new SimpleStringProperty();
    private final StringProperty alamat = new SimpleStringProperty();
    private final StringProperty agama = new SimpleStringProperty();
    private final StringProperty no_Hp = new SimpleStringProperty();
    private final StringProperty username_access = new SimpleStringProperty();
    private final StringProperty password_access = new SimpleStringProperty();
    private final ObjectProperty<Role> role_Id_Role
            = new SimpleObjectProperty<>();

    public User(String kd_User) {
        this.setKd_User(Integer.valueOf(kd_User));

    }

    public User() {

    }

    public int getKd_User() {
        return kd_User.get();
    }

    public void setKd_User(int value) {
        kd_User.set(value);
    }

    public IntegerProperty kd_UserProperty() {
        return kd_User;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin.get();
    }

    public void setJenis_kelamin(String value) {
        jenis_kelamin.set(value);
    }

    public StringProperty jenis_kelaminProperty() {
        return jenis_kelamin;
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String value) {
        alamat.set(value);
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public String getAgama() {
        return agama.get();
    }

    public void setAgama(String value) {
        agama.set(value);
    }

    public StringProperty agamaProperty() {
        return agama;
    }

    public String getNo_Hp() {
        return no_Hp.get();
    }

    public void setNo_Hp(String value) {
        no_Hp.set(value);
    }

    public StringProperty no_HpProperty() {
        return no_Hp;
    }

    public String getUsername_access() {
        return username_access.get();
    }

    public void setUsername_access(String value) {
        username_access.set(value);
    }

    public StringProperty username_accessProperty() {
        return username_access;
    }

    public String getPassword_access() {
        return password_access.get();
    }

    public void setPassword_access(String value) {
        password_access.set(value);
    }

    public StringProperty password_accessProperty() {
        return password_access;
    }

    public Role getRole_Id_Role() {
        return role_Id_Role.get();
    }

    public void setRole_Id_Role(Role value) {
        role_Id_Role.set(value);
    }

    public ObjectProperty role_Id_RoleProperty() {
        return role_Id_Role;
    }

    public String getNm_User() {
        return nm_User.get();
    }

    public void setNm_User(String value) {
        nm_User.set(value);
    }

    public StringProperty nm_UserProperty() {
        return nm_User;
    }

    @Override
    public String toString() {
        return String.valueOf(getKd_User());
    }

}
