/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import com.lydia.dao.BarangDaoImpl;
import com.lydia.dao.Detail_transaksiDaoImpl;
import com.lydia.dao.TransaksiDaoImpl;
import com.lydia.entity.Barang;
import com.lydia.entity.Carts;
import com.lydia.entity.Detail_transaksi;
import com.lydia.entity.Transaksi;
import com.lydia.entity.User;
import com.lydia.utility.Koneksi;
import com.lydia.utility.Utility;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Lydia (1672014)
 */
public class I_TransaksiController implements Initializable {

    @FXML
    private Button btnAddToCart;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancelCart;
    @FXML
    private Button btnHapusCart;

    @FXML
    private TableView<Carts> tableTransaksi;
    @FXML
    private TableColumn<Carts, String> colKd_Barang;
    @FXML
    private TableColumn<Carts, String> colNm_Barang;
    @FXML
    private TableColumn<Carts, String> colJumlah;
    @FXML
    private TableColumn<Carts, String> colHarga;
    @FXML
    private TableColumn<Carts, String> colTotal;
    @FXML
    private ComboBox<Barang> comboListBarang;

    @FXML
    private TextField txtTglTransaksi;
    @FXML
    private TextField txtNoTransaksi;
    @FXML
    private TextField txtJumlah;
    @FXML
    private TextField txtKembalian;
    @FXML
    private TextField txtTotalBelanja;
    @FXML
    private TextField txtNamaKasir;
    @FXML
    private BorderPane bpTransaksi;
    @FXML
    private TextField txtPembayaran;
    @FXML
    private TextField txtIdKasir;

    /**
     * Initializes the controller class.
     */
    private I_HomeController i_homeController;

    public void setHomeController(
            I_HomeController i_homeController) {
        this.i_homeController = i_homeController;
        System.out.println(this.i_homeController);
        tableTransaksi.setItems(getCarts());
        comboListBarang.setItems(getBarangs());
        txtIdKasir.setText(String.valueOf(i_homeController.getSelectedUser().
                getKd_User()));
        txtNamaKasir.setText(i_homeController.getSelectedUser().
                getNm_User());

        txtNoTransaksi.setText(String.valueOf(getTransaksis().size() + 1));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        colKd_Barang.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Carts, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getKd_Barang())));
//                        + param.getValue().getBarang_kd_Barang().getNm_Barang()));
        colNm_Barang.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Carts, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getNm_Barang())));
        colJumlah.
                setCellValueFactory((
                        TableColumn.CellDataFeatures<Carts, String> param)
                        -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getJumlah())));
        colHarga.setCellValueFactory((
                TableColumn.CellDataFeatures<Carts, String> param)
                -> new SimpleStringProperty(String.valueOf(param.
                        getValue().getSaling_Price())));
        colTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<Carts, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getSaling_Price() * param.getValue().getJumlah())));

        txtTglTransaksi.setText(dateFormat.format(date));
        txtNoTransaksi.setText("");
        btnHapusCart.setDisable(true);
    }

    @FXML
    private void btnAddToCartOnAction(ActionEvent event) {
        if (!Utility.isEmptyField(txtTglTransaksi, txtIdKasir, txtNamaKasir,
                txtNoTransaksi,
                txtJumlah) && comboListBarang != null) {
            if (Utility.isNumber(txtJumlah.getText())) {
                Carts cart = new Carts();

                cart.setKd_Barang(comboListBarang.getValue().getKd_Barang());
                cart.setNm_Barang(comboListBarang.getValue().getNm_Barang());
                cart.setJumlah(Integer.valueOf(txtJumlah.getText().trim()));
                cart.setSaling_Price(Integer.valueOf(comboListBarang.getValue().
                        getHrg_Jual()));

                boolean duplikat = false;

                for (Carts c : carts) {
                    if (comboListBarang.getValue().getNm_Barang().equals(c.
                            getNm_Barang())) {
                        duplikat = true;
                        c.setJumlah(c.getJumlah() + Integer.valueOf(txtJumlah.
                                getText()));
                        tableTransaksi.refresh();
                    }
                }
                if (!duplikat) {
                    getCarts().add(cart);
                }
                getTransaksi().setPembayaran(0);
                for (Carts c : carts) {

                    getTransaksi().setPembayaran(getTransaksi().getPembayaran()
                            + c.getJumlah() * c.getSaling_Price());
                }
                txtTotalBelanja.setText(String.valueOf(getTransaksi().
                        getPembayaran()));

                txtJumlah.setText("");
                comboListBarang.setValue(null);

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Isi semua terlebih dahulu");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSubmitOnAction(ActionEvent event) {
        if (!carts.isEmpty()) {
            if (Utility.isNumber(txtPembayaran.getText())) {
                if (Integer.valueOf(txtPembayaran.getText()) >= Integer.valueOf(
                        txtTotalBelanja.getText())) {

                    Timestamp t = new Timestamp(System.currentTimeMillis());

                    Transaksi transaksi = new Transaksi();

//                    transaksi.setTgl_Transaksi(String.valueOf(t));
                    getTransaksi().setKd_Transaksi(Integer.valueOf(
                            txtNoTransaksi.
                                    getText().trim()));
                    getTransaksi().setPembayaran(Integer.valueOf(
                            txtPembayaran.getText().trim()));
                    getTransaksi().setUser_Kd_User(new User(
                            txtIdKasir.getText().
                                    trim()));

                    getTransaksiDao().addData(getTransaksi());

                    for (Carts cart : carts) {
                        Detail_transaksi detail_transaksi
                                = new Detail_transaksi();

                        detail_transaksi.setTransaksi_kd_Transaksi(
                                getTransaksi());
                        detail_transaksi.setBarang_kd_Barang(new Barang(cart.
                                getKd_Barang()));

                        detail_transaksi.setJml(cart.getJumlah());
                        detail_transaksi.setSaling_price(cart.getSaling_Price());

                        getDetail_transaksiDao().addData(detail_transaksi);

                    }

                    txtKembalian.setText(String.valueOf((Utility.StoI(
                            txtPembayaran.getText()) - Utility.StoI(
                            txtTotalBelanja.getText()))));

                    Utility.showAlert("Infomasi", "Charge : Rp" + (Utility.StoI(
                            txtPembayaran.getText()) - Utility.StoI(
                            txtTotalBelanja.getText())),
                            Alert.AlertType.INFORMATION);

                    System.out.println("haha" + txtTotalBelanja.getText());
                    int duitmasuk = Integer.valueOf(txtTotalBelanja.getText());
                    int kembalian = (Utility.StoI(
                            txtPembayaran.getText()) - Utility.StoI(
                            txtTotalBelanja.getText()));

                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                HashMap parameters = new HashMap();
                                parameters.put("duitMasuk", duitmasuk);
                                parameters.put("kembalian", kembalian);
                                JasperPrint jasperPrint = JasperFillManager.
                                        fillReport(
                                                "report/report_kuitansi.jasper",
                                                parameters, Koneksi.
                                                        createConnection());
                                JasperViewer jasperViewer = new JasperViewer(
                                        jasperPrint, false);
                                jasperViewer.setVisible(true);
                            } catch (JRException ex) {
                                System.out.println(ex);
                            }
                            return null;
                        }
                    };
                    ExecutorService service = Executors.newCachedThreadPool();
                    service.execute(task);
                    service.shutdown();

                    setTransaksi(null);

                    carts.clear();

                    txtTotalBelanja.setText("");
                    txtJumlah.setText("");
                    txtKembalian.setText("");
                    txtPembayaran.setText("");

                    comboListBarang.setValue(null);

                    txtIdKasir.setText(String.valueOf(
                            i_homeController.getSelectedUser().
                                    getKd_User()));
                    txtNamaKasir.setText(i_homeController.getSelectedUser().
                            getNm_User());

                    getTransaksis().clear();
                    getTransaksis().addAll(getTransaksiDao().showAllData());

                    txtNoTransaksi.setText(String.
                            valueOf(getTransaksis().size() + 1));

                    DateFormat dateFormat = new SimpleDateFormat(
                            "dd/MM/yyyy HH:mm");
                    Date date = new Date();

                    txtTglTransaksi.setText(dateFormat.format(date));

                } else {
                    Utility.showAlert("Error", "Uang tidak cukup",
                            Alert.AlertType.ERROR);
                }
            }

        }
    }

    @FXML
    private void btnCancelCartOnAction(ActionEvent event) {
        setTransaksi(null);

        carts.clear();

        txtTotalBelanja.setText("");
        txtJumlah.setText("");
        txtKembalian.setText("");
        txtPembayaran.setText("");

        comboListBarang.setValue(null);

        txtIdKasir.setText(String.valueOf(
                i_homeController.getSelectedUser().
                        getKd_User()));
        txtNamaKasir.setText(i_homeController.getSelectedUser().
                getNm_User());

        getTransaksis().clear();
        getTransaksis().addAll(getTransaksiDao().showAllData());

        txtNoTransaksi.setText(String.
                valueOf(getTransaksis().size() + 1));

        DateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm");
        Date date = new Date();

        txtTglTransaksi.setText(dateFormat.format(date));

    }

    @FXML
    private void btnHapusCartOnAction(ActionEvent event) {
        if (selectedCart != null) {
            carts.remove(selectedCart);
            getTransaksi().setPembayaran(0);
            for (Carts c : carts) {

                getTransaksi().setPembayaran(getTransaksi().getPembayaran()
                        + c.getJumlah() * c.getSaling_Price());
            }
            txtTotalBelanja.setText(String.valueOf(getTransaksi().
                    getPembayaran()));

            btnHapusCart.setDisable(true);

            selectedCart = null;

        }

    }

    private ObservableList<Barang> barangs;
    private BarangDaoImpl barangDao;

    public BarangDaoImpl getBarangDao() {
        if (barangDao == null) {
            barangDao = new BarangDaoImpl();
        }
        return barangDao;
    }

    public ObservableList<Barang> getBarangs() {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
            barangs.addAll(getBarangDao().showAllData());
        }
        return barangs;
    }

    private ObservableList<Transaksi> transaksis;
    private TransaksiDaoImpl transaksiDao;

    public ObservableList<Transaksi> getTransaksis() {
        if (transaksis == null) {
            transaksis = FXCollections.observableArrayList();
            transaksis.addAll(getTransaksiDao().showAllData());
        }
        return transaksis;
    }

    public TransaksiDaoImpl getTransaksiDao() {
        if (transaksiDao == null) {
            transaksiDao = new TransaksiDaoImpl();
        }
        return transaksiDao;
    }

    private Detail_transaksiDaoImpl detail_transaksiDaoImpl;

    public Detail_transaksiDaoImpl getDetail_transaksiDao() {
        if (detail_transaksiDaoImpl == null) {
            detail_transaksiDaoImpl = new Detail_transaksiDaoImpl();
        }
        return detail_transaksiDaoImpl;
    }

    private Transaksi transaksi;

    public Transaksi getTransaksi() {
        if (transaksi == null) {
            transaksi = new Transaksi();
            transaksi.setPembayaran(0);
        }
        return transaksi;
    }

    private ObservableList<Carts> carts;

    public ObservableList<Carts> getCarts() {
        if (carts == null) {
            carts = FXCollections.observableArrayList();
        }
        return carts;
    }

    public Carts selectedCart;

    @FXML
    private void tableCartOnClicked(MouseEvent event) {
        selectedCart = tableTransaksi.getSelectionModel().getSelectedItem();
        if (selectedCart != null) {
            btnHapusCart.setDisable(false);
        }
    }

    private void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

}
