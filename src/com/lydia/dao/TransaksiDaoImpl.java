/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.dao;

import com.lydia.entity.Transaksi;
import com.lydia.entity.User;
import com.lydia.utility.DaoService;
import com.lydia.utility.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lydia (1672014)
 */
public class TransaksiDaoImpl implements DaoService<Transaksi> {

    @Override
    public int addData(Transaksi object) {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO transaksi(kd_Transaksi, pembayaran, user_kd_User) VALUES (?,?,?)";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKd_Transaksi());
                ps.setInt(2, object.getPembayaran());
                ps.setInt(3, object.getUser_Kd_User().getKd_User());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Transaksi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Transaksi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaksi> showAllData() {
        ObservableList<Transaksi> transaksi = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT t.kd_Transaksi, t.pembayaran,t.tgl_Transaksi,t.user_Kd_User FROM transaksi t JOIN user u ON t.user_Kd_User = u.kd_User";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Transaksi transaksiObject = new Transaksi();
                    transaksiObject.setKd_Transaksi(rs.getInt("t.kd_Transaksi"));
                    transaksiObject.setPembayaran(rs.getInt("t.pembayaran"));
                    transaksiObject.setTgl_Transaksi(rs.getTimestamp(
                            "tgl_Transaksi"));
                    User user = new User();
                    user.setKd_User(rs.getInt("t.user_Kd_User"));
                    transaksiObject.setUser_Kd_User(user);
                    transaksi.add(transaksiObject);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return transaksi;
    }

    @Override
    public Transaksi getData(Transaksi id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
