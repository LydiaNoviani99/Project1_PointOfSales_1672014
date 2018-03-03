/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import com.lydia.entity.Barang;
import com.lydia.entity.Kategori;
import com.lydia.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lydia (1672014) a
 */
public class I_BarangController implements Initializable {

    private I_HomeController i_homeController;

    public void setHomeController(
            I_HomeController i_homeController) {
        this.i_homeController = i_homeController;
        tableBarang.setItems(i_homeController.getBarangs());
        comboKategoriBarang.setItems(i_homeController.getKategoris());
    }

    @FXML
    private TableView<Barang> tableBarang;
    @FXML
    private TextField txtCariBarang;
    private TextField txtKodeBarang;
    @FXML
    private TextField txtNamaBarang;
    @FXML
    private TextField txtHargaBeli;
    @FXML
    private TextField txtHargaJual;
    @FXML
    private TextField txtStock;
    @FXML
    private ComboBox<Kategori> comboKategoriBarang;
    @FXML
    private BorderPane bpBarang;

    private Stage homeStage;

    private TableColumn<Barang, Integer> colKd_Barang;
    @FXML
    private TableColumn<Barang, String> colNm_Barang;
    @FXML
    private TableColumn<Barang, Integer> colHrg_Beli;
    @FXML
    private TableColumn<Barang, Integer> colHrg_Jual;
    @FXML
    private TableColumn<Barang, Integer> colStock;
    @FXML
    private TableColumn<Barang, String> col_Kategori;

    public Barang selectedBarang;
    @FXML
    private Button btnSimpanBarang;
    @FXML
    private Button btnHapusBarang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNm_Barang.
                setCellValueFactory(data -> data.getValue().nm_BarangProperty());
        colHrg_Beli.
                setCellValueFactory(data -> data.getValue().
                hrg_BeliProperty().asObject());
        colHrg_Jual.
                setCellValueFactory(data -> data.getValue().
                hrg_JualProperty().asObject());

        colStock.
                setCellValueFactory(data -> data.getValue().stockProperty().
                asObject());
        col_Kategori.
                setCellValueFactory(data -> data.getValue().
                getKategori_Id_Kategori().ket_KategoriProperty());
    }

    @FXML
    private void btnCariBarangAction(ActionEvent event) {
    }

    @FXML
    private void btnSimpanBarangAction(ActionEvent event) {
        Utility utility = new Utility();
        if (selectedBarang == null) {
            if (!utility.isEmptyField(txtNamaBarang, txtHargaBeli,
                    txtHargaJual, txtStock)) {
                Barang barang = new Barang();
                barang.setNm_Barang(txtNamaBarang.getText().trim());
                barang.setHrg_Beli(Integer.
                        valueOf(txtHargaBeli.getText().trim()));
                barang.setHrg_Jual(Integer.
                        valueOf(txtHargaJual.getText().trim()));
                barang.setStock(Integer.
                        valueOf(txtStock.getText().trim()));
//                System.out.println(barang.getStock());

                Kategori kategori = new Kategori();
                kategori.setId_Kategori(comboKategoriBarang.getValue().
                        getId_Kategori());
                kategori.setKet_Kategori(comboKategoriBarang.getValue().
                        getKet_Kategori());
                barang.setKategori_Id_Kategori(kategori);
                if (i_homeController.getBarangDao().addData(barang) == 1) {
                    i_homeController.getBarangs().clear();;
                    i_homeController.getBarangs().addAll(i_homeController.
                            getBarangDao().showAllData());

                    tableBarang.refresh();

//                    tableBarang.getSortOrder().add(colKd_Barang);
                    //mengkosongkan teks field setelah isi data
                    txtNamaBarang.clear();
                    txtHargaBeli.clear();
                    txtHargaJual.clear();
                    txtStock.clear();
                    comboKategoriBarang.setValue(null);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Isi semua terlebih dahulu");
                alert.showAndWait();
            }
        } else {
            if (!utility.isEmptyField(txtNamaBarang, txtHargaBeli,
                    txtHargaJual, txtStock)) {
                selectedBarang.setKd_Barang(selectedBarang.getKd_Barang());
                selectedBarang.setNm_Barang(txtNamaBarang.getText().trim());
                selectedBarang.setHrg_Beli(Integer.
                        valueOf(txtHargaBeli.getText().trim()));
                selectedBarang.setHrg_Jual(Integer.
                        valueOf(txtHargaJual.getText().trim()));
                selectedBarang.setStock(Integer.
                        valueOf(txtStock.getText().trim()));
                selectedBarang.setKategori_Id_Kategori(comboKategoriBarang.
                        getValue());
                if (i_homeController.getBarangDao().updateData(selectedBarang)
                        == 1) {
                    i_homeController.getBarangs().clear();;
                    i_homeController.getBarangs().addAll(i_homeController.
                            getBarangDao().showAllData());

                    tableBarang.refresh();
                    //mengkosongkan teks field setelah isi data
                    txtNamaBarang.clear();
                    txtHargaBeli.clear();
                    txtHargaJual.clear();
                    txtStock.clear();
                    comboKategoriBarang.setValue(null);
                    selectedBarang = null;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Isi semua terlebih dahulu");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void btnHapusBarangAction(ActionEvent event
    ) {
        Utility utility = new Utility();
        if (!utility.isEmptyField(txtNamaBarang, txtHargaBeli,
                txtHargaJual, txtStock)) {
//            Barang barang = new Barang();
//            Kategori kategori = new Kategori();
            selectedBarang.setKd_Barang(selectedBarang.getKd_Barang());
            selectedBarang.setNm_Barang(txtNamaBarang.getText().trim());
            selectedBarang.setHrg_Beli(Integer.
                    valueOf(txtHargaBeli.getText().trim()));
            selectedBarang.setHrg_Jual(Integer.
                    valueOf(txtHargaJual.getText().trim()));
            selectedBarang.setStock(Integer.
                    valueOf(txtStock.getText().trim()));

            selectedBarang.setKategori_Id_Kategori(comboKategoriBarang.
                    getValue());
//            kategori.setId_Kategori(comboKategoriBarang.getValue().
//                    getId_Kategori());
//            kategori.setKet_Kategori(comboKategoriBarang.getValue().
//                    getKet_Kategori());
//            barang.setKategori_Id_Kategori(kategori);
            if (i_homeController.getBarangDao().deleteData(selectedBarang) == 1) {
                i_homeController.getBarangs().clear();;
                i_homeController.getBarangs().addAll(i_homeController.
                        getBarangDao().showAllData());
//
//                barangs.addAll(getBarangDao().showAllData());
                tableBarang.refresh();

            }

//            mengkosongkan teks field setelah isi data
            txtNamaBarang.clear();
            txtHargaBeli.clear();
            txtHargaJual.clear();
            txtStock.clear();
            selectedBarang = null;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Pilih barang terlebih dahulu");
            alert.showAndWait();
        }

    }

    @FXML
    private void tableBarangOnClicked(MouseEvent event) {
        selectedBarang = tableBarang.getSelectionModel().getSelectedItem();
        btnHapusBarang.setDisable(false);

        if (selectedBarang != null) {
            txtNamaBarang.setText(selectedBarang.getNm_Barang());
            txtHargaBeli.setText(String.valueOf(selectedBarang.getHrg_Beli()));
            txtHargaJual.setText(String.valueOf(selectedBarang.getHrg_Jual()));
            txtStock.setText(String.valueOf(selectedBarang.getStock()));
            comboKategoriBarang.setValue(selectedBarang.
                    getKategori_Id_Kategori());
        }

    }

}
