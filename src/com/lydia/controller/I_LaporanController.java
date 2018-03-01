/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import com.lydia.dao.BarangDaoImpl;
import com.lydia.dao.Detail_transaksiDaoImpl;
import com.lydia.entity.Barang;
import com.lydia.entity.Detail_transaksi;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lydia (1672014)
 */
public class I_LaporanController implements Initializable {

    @FXML
    private BorderPane bpLaporan;
    //Laporan berdasarkan tahun
    @FXML
    private ComboBox<String> cbTahun;
    @FXML
    private TableView<Detail_transaksi> tableLaporanPenjualan;
    @FXML
    private TableColumn<Detail_transaksi, Date> colTglLaporan;
    @FXML
    private TableColumn<Detail_transaksi, String> colNoTransaksiLaporan;
    @FXML
    private TableColumn<Detail_transaksi, String> colKdBarangLaporan;
    private TableColumn<Detail_transaksi, String> colNmBarangLaporan;
    @FXML
    private TableColumn<Detail_transaksi, String> colHargaLaporan;
    @FXML
    private TableColumn<Detail_transaksi, String> colJmlTerjualLaporan;
    @FXML
    private TableColumn<Detail_transaksi, String> colTotalLaporan;

    // Laporan Tertinggi
    @FXML
    private DatePicker dpDari;
    @FXML
    private DatePicker dpSampai;
    @FXML
    private Button btnLihatLaporanTertinggi;
    @FXML
    private TableView<Barang> tableLaporanPenjualanTertinggi;
    @FXML
    private TableColumn<Detail_transaksi, String> colKdBarangTertinggi;
    @FXML
    private TableColumn<Detail_transaksi, String> colNmBarangTertingi;
    @FXML
    private TableColumn<Detail_transaksi, String> colJmlTerjualTertinggi;
    @FXML
    private TableColumn<Detail_transaksi, String> colTotalTertinggi;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Detail_transaksi> notaPenjualans;
    private Detail_transaksiDaoImpl relasiDao;
    @FXML
    private Button btnReset;

    public ObservableList<Detail_transaksi> getNotaPenjualans() {
        if (notaPenjualans == null) {
            notaPenjualans = FXCollections.observableArrayList();
            notaPenjualans.addAll(getDetail_transaksiDao().showAllData());
        }
        return notaPenjualans;
    }

    public Detail_transaksiDaoImpl getDetail_transaksiDao() {
        if (relasiDao == null) {
            relasiDao = new Detail_transaksiDaoImpl();
        }
        return relasiDao;
    }

    private ObservableList<Detail_transaksi> notaPenjualansTahuns;

    public ObservableList<Detail_transaksi> getNotaPenjualansByYear() {
        if (notaPenjualansTahuns == null) {
            notaPenjualansTahuns = FXCollections.observableArrayList();

        }
        return notaPenjualansTahuns;
    }

    private ObservableList<String> tahuns;

    public ObservableList<String> getTahuns() {
        if (tahuns == null) {
            tahuns = FXCollections.observableArrayList();

            for (int i = 2018; i >= 2015; i--) {
                tahuns.add(String.valueOf(i));
            }
        }
        return tahuns;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbTahun.setValue("Pilih Tahun");
        cbTahun.setItems(getTahuns());
        relasiDao = new Detail_transaksiDaoImpl();

        notaPenjualans = FXCollections.observableArrayList();
        notaPenjualans = (ObservableList<Detail_transaksi>) relasiDao.
                showAllData();

        tableLaporanPenjualan.setItems(getNotaPenjualans());

        colTglLaporan.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, Date> param)
                -> new SimpleObjectProperty<Date>(param.
                        getValue().
                        getTgl_Transaksi()));

        colNoTransaksiLaporan.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getTransaksi_kd_Transaksi())));
        colKdBarangLaporan.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getBarang_kd_Barang())));

        colHargaLaporan.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getSaling_price())));

        colJmlTerjualLaporan.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getJml())));

        colTotalLaporan.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getSaling_price() * param.getValue().getJml())));

        //
//        tableLaporanPenjualanTertinggi.setItems(barangs);
        colKdBarangTertinggi.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getTransaksi_kd_Transaksi())));
        colNmBarangTertingi.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getBarang_kd_Barang().getNm_Barang())));

        colJmlTerjualTertinggi.setCellValueFactory((
                TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getJml())));
        colTotalTertinggi.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Detail_transaksi, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getBarang_kd_Barang()) + param.getValue().
                                getBarang_kd_Barang().getNm_Barang()));

    }

    @FXML
    private void cbTahunOnAction(ActionEvent event) {

        notaPenjualans.clear();
        notaPenjualans.addAll(this.relasiDao.showData(cbTahun.getValue()));
    }

    @FXML
    private void btnResetOnAction(ActionEvent event) {
        notaPenjualans.clear();
        notaPenjualans.addAll(relasiDao.showAllData());
        cbTahun.setValue("Pilih Tahun");
    }

    @FXML
    private void btnBackAction(ActionEvent event) {
    }

    @FXML
    private void btnLihatLaporanTertinggiOnAction(ActionEvent event) {
        if (dpDari.getValue().isBefore(dpSampai.getValue())) {
            if (barangs != null) {
                barangs.clear();
                barangs.addAll(getBarangDao().showTopData(dpDari.getValue().
                        toString(), dpSampai.getValue().toString()));
            } else {
//                getBarang(dpDari.getValue().
//                        toString(),
//                        dpSampai.getValue().toString());
                tableLaporanPenjualanTertinggi.refresh();
                tableLaporanPenjualanTertinggi.setItems(getBarang(dpDari.
                        getValue().toString(), dpSampai.getValue().toString()));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tanggal input tidak valid");
            alert.showAndWait();
        }
    }

    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;

    public BarangDaoImpl getBarangDao() {
        if (barangDao == null) {
            barangDao = new BarangDaoImpl();
        }
        return barangDao;
    }

    public ObservableList<Barang> getBarang(String dari, String sampai) {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
            barangs.addAll(getBarangDao().showTopData(dari, sampai));
        }
        return barangs;
    }

}
