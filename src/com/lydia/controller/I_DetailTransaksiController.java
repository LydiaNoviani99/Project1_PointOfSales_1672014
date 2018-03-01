/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class I_DetailTransaksiController implements Initializable {

    @FXML
    private TableView<?> tableTransaksi;
    @FXML
    private TableColumn<?, ?> colTgl_Transaksi;
    @FXML
    private TableColumn<?, ?> colKd_Transaksi;
    @FXML
    private TableColumn<?, ?> colKd_User;
    @FXML
    private TableColumn<?, ?> colNama_User;
    @FXML
    private TableColumn<?, ?> colKd_Barang;
    @FXML
    private TableColumn<?, ?> colNama_Barang;
    @FXML
    private TableColumn<?, ?> colJml;
    @FXML
    private TableColumn<?, ?> colHrg_Satuan;
    @FXML
    private TableColumn<?, ?> colTotal_Belanja;
    @FXML
    private TableColumn<?, ?> colPembayaran;
    @FXML
    private TableColumn<?, ?> colKembalian;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
