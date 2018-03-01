/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.dao;

import com.lydia.entity.Barang;
import com.lydia.entity.Kategori;
import com.lydia.utility.DaoService;
import com.lydia.utility.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lydia (1672014)
 */
public class BarangDaoImpl implements DaoService<Barang> {

    @Override
    public int addData(Barang object) {
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO barang(nm_Barang, hrg_Beli, hrg_Jual, stock, kategori_id_Kategori) VALUES (?,?,?,?,?) ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getNm_Barang());
                ps.setInt(2, object.getHrg_Beli());
                ps.setInt(3, object.getHrg_Jual());
                ps.setInt(4, object.getStock());
                ps.setInt(5, object.getKategori_Id_Kategori().getId_Kategori());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Barang object
    ) {
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM barang WHERE kd_Barang = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKd_Barang());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int updateData(Barang object
    ) {
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE barang SET nm_Barang = ?, hrg_Beli = ?, hrg_Jual = ?, stock = ?, kategori_id_Kategori = ? WHERE kd_Barang = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getNm_Barang());
                ps.setInt(2, object.getHrg_Beli());
                ps.setInt(3, object.getHrg_Jual());
                ps.setInt(4, object.getStock());
                ps.setInt(5, object.getKategori_Id_Kategori().
                        getId_Kategori());
                ps.setInt(6, object.getKd_Barang());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public List<Barang> showAllData() {
        ObservableList<Barang> barang = FXCollections.observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT * FROM barang b JOIN kategori k WHERE b.kategori_id_Kategori = k.id_Kategori";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Barang barangObject = new Barang();
                    Kategori kategoriObject = new Kategori();
                    barangObject.setKd_Barang(rs.getInt("kd_Barang"));
                    barangObject.setNm_Barang(rs.getString("nm_Barang"));
                    barangObject.setHrg_Beli(rs.getInt("hrg_Beli"));
                    barangObject.setHrg_Jual(rs.getInt("hrg_Jual"));
                    barangObject.setStock(rs.getInt("stock"));
                    kategoriObject.setId_Kategori(rs.getInt("id_Kategori"));
                    kategoriObject.setKet_Kategori(rs.getString(
                            "ket_Kategori"));
                    barangObject.setKategori_Id_Kategori(kategoriObject);
                    barang.add(barangObject);

                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return barang;
    }

    @Override
    public Barang getData(Barang id
    ) {
        try (Connection connection = Koneksi.createConnection()) {
            String query
                    = "SELECT hrg_Jual where kd_Barang=? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id.getKd_Barang());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Barang barang = new Barang();
                Kategori kategori = new Kategori();
                barang.setHrg_Jual(rs.getInt("hrg_Jual"));
                return barang;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return (null);
    }

    public ObservableList<Barang> showTopData(String object, String object2) {
        ObservableList<Barang> barangs = FXCollections.observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT b.kd_Barang,b.nm_Barang,SUM(dt.jml) AS 'Jumlah Terjual' ,((SUM(dt.jml) )*dt.saling_price) AS 'Total' from transaksi t JOIN detail_transaksi dt ON t.kd_Transaksi = dt.transaksi_kd_Transaksi JOIN Barang b ON b.kd_Barang = dt.barang_kd_Barang where t.tgl_Transaksi > '2018-02-27' AND t.tgl_Transaksi < '2018-03-01' GROUP BY b.kd_Barang, b.nm_Barang ORDER BY SUM(dt.jml) DESC";
                PreparedStatement ps = connection.prepareStatement(query);
//                ps.setString(1, object);
//                ps.setString(2, object2);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Barang barang = new Barang();

                    barang.setKd_Barang(rs.getInt("kd_Barang"));
                    barang.setNm_Barang(rs.getString("nm_Barang"));

                    barangs.add(barang);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return barangs;
    }

}
