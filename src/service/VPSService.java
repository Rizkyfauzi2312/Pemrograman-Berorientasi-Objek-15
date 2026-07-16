package service;

import dao.VPSDAO;
import model.VPS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VPSService {

    private VPSDAO vpsDAO;

    public VPSService() {
        this.vpsDAO = new VPSDAO();
    }

    public boolean tambahVPS(VPS vps) {
        try {
            vpsDAO.tambahVPS(vps);
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menambah VPS: " + e.getMessage());
            return false;
        }
    }

    public List<VPS> lihatSemuaVPS() {
        try {
            return vpsDAO.lihatSemuaVPS();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data VPS: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String[]> lihatVPSAktif() {
        try {
            return vpsDAO.lihatVPSAktif();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil laporan VPS aktif: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public int getTotalVPS() {
        try {
            return vpsDAO.getTotalVPS();
        } catch (SQLException e) {
            System.out.println("Gagal menghitung total VPS: " + e.getMessage());
            return 0;
        }
    }
}
