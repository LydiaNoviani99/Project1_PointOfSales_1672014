/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.dao;

import com.lydia.entity.Role;
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
public class RoleDaoImpl implements DaoService<Role> {

    @Override
    public int addData(Role object) {
        int result = 0;
        try {
            try (Connection connection = Koneksi.createConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO role(id_Role, ket_Role) VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getId_Role());
                ps.setString(2, object.getKet_Role());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Role object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Role object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> showAllData() {
        ObservableList<Role> role = FXCollections.observableArrayList();
        try {
            try (Connection connection = Koneksi.createConnection()) {
                String query
                        = "SELECT * FROM role";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Role roleObject = new Role();

                    roleObject.setId_Role(rs.getInt("id_Role"));
                    roleObject.setKet_Role(rs.getString("ket_Role"));
                    role.add(roleObject);

                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return role;
    }

    @Override
    public Role getData(Role id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
