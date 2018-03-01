/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.dao;

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
public class KategoriDaoImpl implements DaoService<Kategori> {

    @Override
    public int addData(Kategori object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteData(Kategori object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Kategori object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Kategori> showAllData() {
        ObservableList<Kategori> kategori = FXCollections.observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT * FROM kategori";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Kategori kategoriObject = new Kategori();

                    kategoriObject.setId_Kategori(rs.getInt("id_Kategori"));
                    kategoriObject.setKet_Kategori(rs.getString("ket_Kategori"));
                    kategori.add(kategoriObject);

                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return kategori;
    }

    @Override
    public Kategori getData(Kategori id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
