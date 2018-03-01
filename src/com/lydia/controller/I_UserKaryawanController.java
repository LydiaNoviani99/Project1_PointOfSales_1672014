/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.controller;

import com.lydia.entity.Role;
import com.lydia.entity.User;
import com.lydia.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lydia (1672014)
 */
public class I_UserKaryawanController implements Initializable {

    private I_HomeController i_homeController;

    public void setHomeController(
            I_HomeController i_homeController) {
        this.i_homeController = i_homeController;
        tableUser.setItems(i_homeController.getUsers());
        comboJabatanUser.setItems(i_homeController.getRoles());
    }

    @FXML
    private BorderPane bpUser;
    @FXML
    private TextField txtCariUser;
    @FXML
    private Button btnCariUser;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> colNm_User;
    @FXML
    private TableColumn<User, String> colJK;
    @FXML
    private TableColumn<User, String> colAgama;
    @FXML
    private TableColumn<User, String> colAlamat;
    @FXML
    private TableColumn<User, String> colNo_HP;
    @FXML
    private TableColumn<User, String> colUsername_Access;
    @FXML
    private TableColumn<User, String> colJabatan;
    @FXML
    private TextField txtNamaUser;
    @FXML
    private TextField txtAgama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtNoHp;
    @FXML
    private TextField txtUsernameAccess;
    @FXML
    private TextField txtPasswordAccess;

    @FXML
    private TextField txtVerifyPassword;

    public User selectedUser;
    @FXML
    private ComboBox<Role> comboJabatanUser;
    @FXML
    private ToggleGroup jeniskelamin;
    @FXML
    private RadioButton radioPria;
    @FXML
    private RadioButton radioWanita;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNm_User.
                setCellValueFactory(data -> data.getValue().
                nm_UserProperty());
        colJK.
                setCellValueFactory(data -> data.getValue().
                jenis_kelaminProperty());
        colAgama.
                setCellValueFactory(data -> data.getValue().agamaProperty());
        colAlamat.
                setCellValueFactory(data -> data.getValue().alamatProperty());
        colNo_HP.
                setCellValueFactory(data -> data.getValue().no_HpProperty());
        colUsername_Access.
                setCellValueFactory(data -> data.getValue().
                username_accessProperty());
        colJabatan.
                setCellValueFactory(data -> data.getValue().
                getRole_Id_Role().ket_RoleProperty());
    }

    @FXML
    private void btnCariUserAction(ActionEvent event) {
    }

    @FXML
    private void btnSimpanUserAcction(ActionEvent event) {
        if (selectedUser == null) {
            if (!Utility.isEmptyField(txtNamaUser, txtAgama,
                    txtAlamat, txtNoHp, txtUsernameAccess, txtPasswordAccess,
                    txtVerifyPassword) && comboJabatanUser.getValue() != null) {
                User user = new User();
                user.setNm_User(txtNamaUser.getText().trim());
                if (radioPria.isSelected()) {
                    user.setJenis_kelamin("Pria");
                } else {
                    user.setJenis_kelamin("Wanita");
                }
                user.setAgama(txtAgama.getText().trim());
                user.setAlamat(txtAlamat.getText().trim());
                user.setNo_Hp(txtNoHp.getText().trim());
                user.setUsername_access(txtUsernameAccess.getText().trim());
                user.setPassword_access(txtPasswordAccess.getText().trim());

                Role role = new Role();
                role.setId_Role(comboJabatanUser.getValue().getId_Role());
                role.setKet_Role(comboJabatanUser.getValue().
                        getKet_Role());
                user.setRole_Id_Role(role);

                if (txtVerifyPassword.getText().equals(txtPasswordAccess.
                        getText())) {
                    if (i_homeController.getUserDao().addData(user) == 1) {
                        i_homeController.getUsers().clear();;
                        i_homeController.getUsers().addAll(i_homeController.
                                getUserDao().showAllData());

                        tableUser.refresh();

                        //mengkosongkan teks field setelah isi data
                        txtNamaUser.clear();
                        txtAgama.clear();
                        txtAlamat.clear();
                        txtNoHp.clear();
                        txtUsernameAccess.clear();
                        txtPasswordAccess.clear();
                        txtVerifyPassword.clear();
                        comboJabatanUser.setValue(null);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password Tidak Sama, Isi Lagi!");
                    alert.showAndWait();
                    txtPasswordAccess.clear();
                    txtVerifyPassword.clear();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Isi semua terlebih dahulu");
                alert.showAndWait();
            }
        } else {
            if (!Utility.isEmptyField(txtNamaUser, txtAgama,
                    txtAlamat, txtNoHp, txtUsernameAccess, txtPasswordAccess,
                    txtVerifyPassword) && comboJabatanUser.getValue() != null) {
//                User user = new User();
//                Role role = new Role();
                selectedUser.setNm_User(txtNamaUser.getText().trim());

                if (radioPria.isSelected()) {
                    selectedUser.setJenis_kelamin("Pria");
                } else {
                    selectedUser.setJenis_kelamin("Wanita");
                }

                selectedUser.setAgama(txtAgama.getText().trim());
                selectedUser.setAlamat(txtAlamat.getText().trim());
                selectedUser.setNo_Hp(txtNoHp.getText().trim());
                selectedUser.setUsername_access(txtUsernameAccess.getText().
                        trim());
                selectedUser.setPassword_access(txtPasswordAccess.getText().
                        trim());
                selectedUser.setRole_Id_Role(comboJabatanUser.getValue());

                if (txtVerifyPassword.getText().equals(txtPasswordAccess.
                        getText())) {
                    if (i_homeController.getUserDao().updateData(selectedUser)
                            == 1) {
                        i_homeController.getUsers().clear();
                        i_homeController.getUsers().addAll(i_homeController.
                                getUserDao().showAllData());

                        tableUser.refresh();

                        //mengkosongkan teks field setelah isi data
                        txtNamaUser.clear();
                        txtAgama.clear();
                        txtAlamat.clear();
                        txtNoHp.clear();
                        txtUsernameAccess.clear();
                        txtPasswordAccess.clear();
                        txtVerifyPassword.clear();
                        comboJabatanUser.setValue(null);

                        selectedUser = null;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password Tidak Sama, Isi Lagi!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Isi semua terlebih dahulu");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnHapusUserAction(ActionEvent event
    ) {
        Utility utility = new Utility();
        if (!utility.isEmptyField(txtNamaUser, txtAgama,
                txtAlamat, txtNoHp, txtUsernameAccess, txtPasswordAccess,
                txtVerifyPassword)) {
//            User user = new User();
//            Role role = new Role();
            selectedUser.setNm_User(txtNamaUser.getText().trim());
            if (radioPria.isSelected()) {
                selectedUser.setJenis_kelamin("Pria");
            } else {
                selectedUser.setJenis_kelamin("Wanita");
            }
            selectedUser.setAgama(txtAgama.getText().trim());
            selectedUser.setAlamat(txtAlamat.getText().trim());
            selectedUser.setNo_Hp(txtNoHp.getText().trim());
            selectedUser.setUsername_access(txtUsernameAccess.getText().trim());
            selectedUser.setPassword_access(txtPasswordAccess.getText().trim());
            selectedUser.setRole_Id_Role(comboJabatanUser.getValue());

            if (i_homeController.getUserDao().deleteData(selectedUser) == 1) {
                i_homeController.getUsers().clear();;
                i_homeController.getUsers().addAll(
                        i_homeController.getUserDao().showAllData());

                tableUser.refresh();

//                    tableBarang.getSortOrder().add(colKd_Barang);
                //mengkosongkan teks field setelah isi data
                txtNamaUser.clear();
                txtAgama.clear();
                txtAlamat.clear();
                txtNoHp.clear();
                txtUsernameAccess.clear();
                txtPasswordAccess.clear();
                txtVerifyPassword.clear();
                comboJabatanUser.setValue(null);
            }

        }
    }

    @FXML
    private void tableUserOnClicked(MouseEvent event) {
        selectedUser = tableUser.getSelectionModel().getSelectedItem();
//        btnHapusUser.setDisable(false);

        if (selectedUser != null) {
            txtNamaUser.setText(selectedUser.getNm_User());
            txtAgama.setText(selectedUser.getAgama());
            txtAlamat.setText(selectedUser.getAlamat());
            txtNoHp.setText(selectedUser.getNo_Hp());
            txtUsernameAccess.setText(selectedUser.getUsername_access());
            txtPasswordAccess.setText(selectedUser.getPassword_access());
            if (selectedUser.getJenis_kelamin().equals("Pria")) {
                jeniskelamin.getToggles().clear();
                jeniskelamin.selectToggle(radioPria);
            } else {
                jeniskelamin.getToggles().clear();
                jeniskelamin.selectToggle(radioWanita);
            }
            txtVerifyPassword.setText(selectedUser.getPassword_access());
            comboJabatanUser.setValue(selectedUser.
                    getRole_Id_Role());
        }
    }

}
