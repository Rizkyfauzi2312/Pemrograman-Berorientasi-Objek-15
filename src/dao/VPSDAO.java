package dao;

import database.DatabaseConnection;
import model.VPS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VPSDAO {

    public void tambahVPS(VPS vps) throws SQLException {
        String sql = "{CALL tambah_vps(?, ?, ?, ?, ?, ?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, vps.getNamaServer());
        cs.setString(2, vps.getIpAddress());
        cs.setString(3, vps.getSistemOperasi());
        cs.setInt(4, vps.getRam());
        cs.setInt(5, vps.getStorage());
        cs.setInt(6, vps.getIdPelanggan());
        cs.executeUpdate();
        cs.close();
        conn.close();
    }

    public List<VPS> lihatSemuaVPS() throws SQLException {
        List<VPS> list = new ArrayList<>();
        String sql = "SELECT * FROM vps";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            VPS v = new VPS(
                    rs.getInt("id_vps"),
                    rs.getString("nama_server"),
                    rs.getString("ip_address"),
                    rs.getString("sistem_operasi"),
                    rs.getInt("ram"),
                    rs.getInt("storage"),
                    rs.getString("status"),
                    rs.getInt("id_pelanggan")
            );
            list.add(v);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public List<String[]> lihatVPSAktif() throws SQLException {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM v_laporan_vps WHERE status = 'Aktif'";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String[] row = {
                    String.valueOf(rs.getInt("id_vps")),
                    rs.getString("nama_server"),
                    rs.getString("ip_address"),
                    rs.getString("sistem_operasi"),
                    String.valueOf(rs.getInt("ram")),
                    String.valueOf(rs.getInt("storage")),
                    rs.getString("status"),
                    rs.getString("nama"),
                    rs.getString("email")
            };
            list.add(row);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public int getTotalVPS() throws SQLException {
        int total = 0;
        String sql = "SELECT total_vps() AS total";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            total = rs.getInt("total");
        }
        rs.close();
        stmt.close();
        conn.close();
        return total;
    }
}
