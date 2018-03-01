/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.dao;

import com.lydia.entity.Barang;
import com.lydia.entity.Detail_transaksi;
import com.lydia.entity.Transaksi;
import com.lydia.entity.User;
import com.lydia.utility.DaoService;
import com.lydia.utility.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lydia (1672014)
 */
public class Detail_transaksiDaoImpl implements
        DaoService<Detail_transaksi> {

    @Override
    public int addData(Detail_transaksi object) {
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO detail_transaksi(transaksi_kd_Transaksi,barang_kd_Barang,jml,saling_price) VALUES (?,?,?,?)";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getTransaksi_kd_Transaksi().
                        getKd_Transaksi());
                ps.setInt(2, object.getBarang_kd_Barang().getKd_Barang());
                ps.setInt(3, object.getJml());
                ps.setInt(4, object.getSaling_price());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Detail_transaksiDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Detail_transaksi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Detail_transaksi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Detail_transaksi> showAllData() {
        ObservableList<Detail_transaksi> relasiPenjualans = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT t.tgl_Transaksi, t.kd_Transaksi, b.kd_Barang, b.nm_Barang, dt.jml, dt.saling_price,u.kd_User FROM detail_transaksi dt JOIN transaksi t ON dt.transaksi_kd_Transaksi = t.kd_Transaksi JOIN User u ON t.user_kd_User = u.kd_User JOIN Barang b ON b.kd_Barang = dt.barang_kd_Barang";

                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Detail_transaksi detail_transaksi = new Detail_transaksi();
                    Transaksi transaksi1 = new Transaksi();
                    User user = new User();
                    Barang barang = new Barang();

                    transaksi1.setKd_Transaksi(rs.getInt("t.kd_Transaksi"));
                    user.setKd_User(rs.getInt("u.kd_User"));
                    barang.setKd_Barang(rs.getInt("b.kd_Barang"));
                    barang.setNm_Barang(rs.getString("b.nm_Barang"));

                    detail_transaksi.setJml(rs.getInt("dt.jml"));
                    detail_transaksi.setSaling_price(rs.
                            getInt("dt.saling_price"));

                    detail_transaksi.setTgl_Transaksi(rs.getTimestamp(
                            "t.tgl_Transaksi"));

                    detail_transaksi.setBarang_kd_Barang(barang);
                    transaksi1.setUser_Kd_User(user);
                    detail_transaksi.setTransaksi_kd_Transaksi(transaksi1);
                    relasiPenjualans.add(detail_transaksi);

                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return relasiPenjualans;
    }

    @Override
    public Detail_transaksi getData(Detail_transaksi id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Detail_transaksi> showData(String value) {
        System.out.println("asdasjkb");
        ObservableList<Detail_transaksi> relasiPenjualans = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT t.tgl_Transaksi, t.kd_Transaksi, b.kd_Barang, b.nm_Barang, dt.jml, dt.saling_price,u.kd_User FROM detail_transaksi dt JOIN transaksi t ON dt.transaksi_kd_Transaksi = t.kd_Transaksi JOIN User u ON t.user_kd_User = u.kd_User JOIN Barang b ON b.kd_Barang = dt.barang_kd_Barang WHERE LEFT(t.tgl_Transaksi,4) = LEFT(?,4) ORDER BY dt.transaksi_kd_Transaksi ASC ";

                PreparedStatement ps = connection.prepareStatement(query);

                ps.setString(1, value);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Detail_transaksi detail_transaksi = new Detail_transaksi();
                    Transaksi transaksi1 = new Transaksi();
                    User user = new User();
                    Barang barang = new Barang();

                    transaksi1.setKd_Transaksi(rs.getInt("t.kd_Transaksi"));
                    user.setKd_User(rs.getInt("u.kd_User"));
                    barang.setKd_Barang(rs.getInt("b.kd_Barang"));
                    barang.setNm_Barang(rs.getString("b.nm_Barang"));
                    detail_transaksi.setJml(rs.getInt("dt.jml"));
                    detail_transaksi.setSaling_price(rs.
                            getInt("dt.saling_price"));
                    detail_transaksi.setTgl_Transaksi(rs.getTimestamp(
                            "t.tgl_Transaksi"));
                    detail_transaksi.setBarang_kd_Barang(barang);
                    transaksi1.setUser_Kd_User(user);
                    detail_transaksi.setTransaksi_kd_Transaksi(transaksi1);
                    relasiPenjualans.add(detail_transaksi);

                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return relasiPenjualans;
    }

}
