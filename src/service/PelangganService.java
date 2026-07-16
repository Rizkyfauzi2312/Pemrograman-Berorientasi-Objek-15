package service;

import dao.PelangganDAO;
import model.Pelanggan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PelangganService {

    private PelangganDAO pelangganDAO;

    public PelangganService() {
        this.pelangganDAO = new PelangganDAO();
    }

    public boolean tambahPelanggan(Pelanggan pelanggan) {
        try {
            pelangganDAO.tambahPelanggan(pelanggan);
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menambah pelanggan: " + e.getMessage());
            return false;
        }
    }

    public List<Pelanggan> lihatSemuaPelanggan() {
        try {
            return pelangganDAO.lihatSemuaPelanggan();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pelanggan: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
