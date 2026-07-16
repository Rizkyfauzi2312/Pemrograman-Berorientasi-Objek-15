package dao;

import database.DatabaseConnection;
import model.Pelanggan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {

    public void tambahPelanggan(Pelanggan pelanggan) throws SQLException {
        String sql = "INSERT INTO pelanggan (nama, email, nomor_hp) VALUES (?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pelanggan.getNama());
        ps.setString(2, pelanggan.getEmail());
        ps.setString(3, pelanggan.getNomorHp());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Pelanggan> lihatSemuaPelanggan() throws SQLException {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Pelanggan p = new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("nomor_hp")
            );
            list.add(p);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
}
