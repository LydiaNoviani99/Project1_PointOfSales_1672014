/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import com.lydia.MainApp;
import com.lydia.dao.BarangDaoImpl;
import com.lydia.dao.KategoriDaoImpl;
import com.lydia.dao.RoleDaoImpl;
import com.lydia.dao.UserDaoImpl;
import com.lydia.entity.Barang;
import com.lydia.entity.Kategori;
import com.lydia.entity.Role;
import com.lydia.entity.User;
import com.lydia.utility.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lydia (1672014)
 */
public class I_HomeController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private MenuItem menuExit;
    @FXML
    private Menu menuDataBarang;
    @FXML
    private MenuItem menuKelolaDataBarang;
    @FXML
    private MenuItem menuCetakBarang;
    @FXML
    private Menu menuDataUser;
    @FXML
    private MenuItem menuKelolaDataUser;
    @FXML
    private MenuItem menuCetakUser;
    @FXML
    private Menu menuTransaksi;
    @FXML
    private MenuItem menuKelolaDataTransaksi;
    @FXML
    private MenuItem menuCetakTransaksi;
    @FXML
    private Menu menuLaporan;
    @FXML
    private MenuItem menuLaporanPenjualan;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Menu menuAbout;
    @FXML
    private BorderPane bpHome;

    private Stage homeStage;
    private Stage barangStage;
    private Stage userStage;
    private Stage transaksiStage;
    private Stage laporanStage;

    private I_LoginController i_LoginController;
    @FXML
    private MenuItem menuAboutMe;

    /**
     * 11 Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //FILE
    @FXML
    private void menuFileOnAction(ActionEvent event) {
    }

    @FXML
    private void menuLogoutOnAction(ActionEvent event) {
        try {
            homeStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/i_Login.fxml"));
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            I_LoginController i_LoginController = loader.getController();
            homeStage.setScene(scene);
            homeStage.showAndWait();

            bpHome.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(I_LoginController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuExitOnAction(ActionEvent event) {
        Platform.exit();
    }

    //DATA BARANG
    @FXML
    private void menuDataBarangOnAction(ActionEvent event) {
    }

    @FXML
    private void menuKelolaDataBarangOnAction(ActionEvent event) {
        try {
            barangStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/i_Barang.fxml"));
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            I_BarangController i_BarangController = loader.getController();
            i_BarangController.setHomeController(this);
            barangStage.initOwner(bpHome.getScene().getWindow());
            barangStage.initModality(Modality.WINDOW_MODAL);

            barangStage.setScene(scene);
            barangStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(I_LoginController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuCetakBarangOnAction(ActionEvent event) {
    }

    //USER
    @FXML
    private void menuDataUserOnAction(ActionEvent event) {
    }

    @FXML
    private void menuKelolaDataUserOnAction(ActionEvent event) {
        try {
            userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/i_UserKaryawan.fxml"));
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            I_UserKaryawanController i_UserKaryawanController = loader.
                    getController();
            i_UserKaryawanController.setHomeController(this);
            userStage.initOwner(bpHome.getScene().getWindow());
            userStage.initModality(Modality.WINDOW_MODAL);

            userStage.setScene(scene);
            userStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(I_LoginController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menuCetakUserOnAction(ActionEvent event) {
    }

    //DATA TRANSAKSI
    @FXML
    private void menuTransaksiOnAction(ActionEvent event) {
    }

    @FXML
    private void menuKelolaDataTransaksiOnAction(ActionEvent event) {
        try {
            transaksiStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.
                    setLocation(MainApp.class.
                            getResource("view/i_Transaksi.fxml"));
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            I_TransaksiController i_TransaksiController = loader.getController();
            i_TransaksiController.setHomeController(this);
            System.out.println(this);
            transaksiStage.initOwner(bpHome.getScene().getWindow());
            transaksiStage.initModality(Modality.WINDOW_MODAL);

            transaksiStage.setScene(scene);
            transaksiStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(I_LoginController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuCetakTransaksiOnAction(ActionEvent event) {
    }

    //LAPORAN
    @FXML
    private void menuLaporanOnAction(ActionEvent event) {
    }

    @FXML
    private void menuLaporanPenjualanOnAction(ActionEvent event) {
        try {
            laporanStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.
                    setLocation(MainApp.class.
                            getResource("view/i_Laporan.fxml"));
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            I_LaporanController i_LaporanController = loader.getController();
            System.out.println(this);
            laporanStage.initOwner(bpHome.getScene().getWindow());
            laporanStage.initModality(Modality.WINDOW_MODAL);

            laporanStage.setScene(scene);
            laporanStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(I_LoginController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

    }

    //ABOUT
    @FXML
    private void menuAboutOnAction(ActionEvent event) {
    }

    @FXML
    private void menuAboutMeOnAction(ActionEvent event) {

        Utility.showAlert("About", "Lydia - 1672014",
                Alert.AlertType.INFORMATION);
    }

    public void setLoginController(I_LoginController aThis, User user) {
        this.i_LoginController = aThis;
        this.selectedUser = user;
        System.out.println(selectedUser.getNm_User()
        );
        if (i_LoginController.getSelectedUser().getRole_Id_Role().getId_Role()
                == 1) {
            menuFile.setDisable(false);
            menuDataBarang.setDisable(false);
            menuDataUser.setDisable(false);
            menuTransaksi.setDisable(true);
            menuLaporan.setDisable(false);

        } else if (i_LoginController.getSelectedUser().getRole_Id_Role().
                getId_Role() == 2) {
            menuFile.setDisable(false);
            menuDataBarang.setDisable(true);
            menuDataUser.setDisable(true);
            menuTransaksi.setDisable(false);
            menuLaporan.setDisable(true);
        }
    }

    public void setListUserController(I_LoginController i_LoginController) {
        this.i_LoginController = i_LoginController;

    }

    private BarangDaoImpl barangDaoImpl;
    private ObservableList<Barang> barangs;

    private KategoriDaoImpl kategoriDaoImpl;
    private ObservableList<Kategori> kategoris;

    public ObservableList<Barang> getBarangs() {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
            barangs.addAll(getBarangDao().showAllData());
        }
        return barangs;
    }

    public BarangDaoImpl getBarangDao() {
        if (barangDaoImpl == null) {
            barangDaoImpl = new BarangDaoImpl();
        }
        return barangDaoImpl;
    }

    public ObservableList<Kategori> getKategoris() {
        if (kategoris == null) {
            kategoris = FXCollections.observableArrayList();
            kategoris.addAll(getKategoriDao().showAllData());
        }
        return kategoris;
    }

    public KategoriDaoImpl getKategoriDao() {
        if (kategoriDaoImpl == null) {
            kategoriDaoImpl = new KategoriDaoImpl();
        }
        return kategoriDaoImpl;
    }

    private UserDaoImpl userDaoImpl;
    private ObservableList<User> users;

    private RoleDaoImpl roleDaoImpl;
    private ObservableList<Role> roles;

    public UserDaoImpl getUserDao() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public ObservableList<User> getUsers() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }

    public ObservableList<Role> getRoles() {
        if (roles == null) {
            roles = FXCollections.observableArrayList();
            roles.addAll(getRoleDao().showAllData());
        }
        return roles;
    }

    public RoleDaoImpl getRoleDao() {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }

    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

}
