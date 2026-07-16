package main;

import model.Pelanggan;
import model.VPS;
import service.PelangganService;
import service.VPSService;
import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static PelangganService pelangganService = new PelangganService();
    private static VPSService vpsService = new VPSService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            tampilMenuUtama();
            String pilihan = scanner.nextLine().trim();
            try {
                int menu = InputValidator.parseInteger(pilihan);
                switch (menu) {
                    case 1:
                        menuKelolaPelanggan();
                        break;
                    case 2:
                        menuKelolaVPS();
                        break;
                    case 3:
                        menuLaporan();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Terima kasih. Program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void tampilMenuUtama() {
        System.out.println("\n========================================");
        System.out.println("   SISTEM MANAJEMEN VPS BERBASIS CLI   ");
        System.out.println("========================================");
        System.out.println("  1. Kelola Pelanggan");
        System.out.println("  2. Kelola VPS");
        System.out.println("  3. Laporan");
        System.out.println("  4. Keluar");
        System.out.println("========================================");
        System.out.print("Pilih menu: ");
    }

    private static void menuKelolaPelanggan() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Menu Kelola Pelanggan ---");
            System.out.println("  1. Tambah Pelanggan");
            System.out.println("  2. Lihat Data Pelanggan");
            System.out.println("  3. Kembali");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine().trim();
            try {
                int menu = InputValidator.parseInteger(pilihan);
                switch (menu) {
                    case 1:
                        tambahPelanggan();
                        break;
                    case 2:
                        lihatPelanggan();
                        break;
                    case 3:
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private static void tambahPelanggan() {
        System.out.println("\n--- Tambah Pelanggan Baru ---");

        System.out.print("Nama       : ");
        String nama = scanner.nextLine().trim();
        if (!InputValidator.isNotEmpty(nama)) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }

        System.out.print("Email      : ");
        String email = scanner.nextLine().trim();
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Format email tidak valid.");
            return;
        }

        System.out.print("Nomor HP   : ");
        String nomorHp = scanner.nextLine().trim();
        if (!InputValidator.isValidPhone(nomorHp)) {
            System.out.println("Nomor HP harus terdiri dari 10-15 digit angka.");
            return;
        }

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setEmail(email);
        pelanggan.setNomorHp(nomorHp);

        if (pelangganService.tambahPelanggan(pelanggan)) {
            System.out.println("Pelanggan berhasil ditambahkan.");
        }
    }

    private static void lihatPelanggan() {
        System.out.println("\n--- Data Pelanggan ---");
        List<Pelanggan> list = pelangganService.lihatSemuaPelanggan();
        if (list.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-25s | %-15s |\n", "ID", "Nama", "Email", "No HP");
            System.out.println("----------------------------------------------------------------------");
            for (Pelanggan p : list) {
                System.out.printf("| %-5d | %-20s | %-25s | %-15s |\n",
                        p.getId(), p.getNama(), p.getEmail(), p.getNomorHp());
            }
            System.out.println("----------------------------------------------------------------------");
        }
    }

    private static void menuKelolaVPS() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Menu Kelola VPS ---");
            System.out.println("  1. Tambah VPS");
            System.out.println("  2. Lihat Data VPS");
            System.out.println("  3. Kembali");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine().trim();
            try {
                int menu = InputValidator.parseInteger(pilihan);
                switch (menu) {
                    case 1:
                        tambahVPS();
                        break;
                    case 2:
                        lihatVPS();
                        break;
                    case 3:
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private static void tambahVPS() {
        System.out.println("\n--- Tambah VPS Baru ---");

        System.out.print("Nama Server     : ");
        String namaServer = scanner.nextLine().trim();
        if (!InputValidator.isNotEmpty(namaServer)) {
            System.out.println("Nama server tidak boleh kosong.");
            return;
        }

        System.out.print("IP Address      : ");
        String ipAddress = scanner.nextLine().trim();
        if (!InputValidator.isNotEmpty(ipAddress)) {
            System.out.println("IP Address tidak boleh kosong.");
            return;
        }

        System.out.print("Sistem Operasi  : ");
        String sistemOperasi = scanner.nextLine().trim();
        if (!InputValidator.isNotEmpty(sistemOperasi)) {
            System.out.println("Sistem operasi tidak boleh kosong.");
            return;
        }

        int ram;
        try {
            System.out.print("RAM (GB)        : ");
            ram = InputValidator.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("RAM harus berupa angka.");
            return;
        }

        int storage;
        try {
            System.out.print("Storage (GB)    : ");
            storage = InputValidator.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Storage harus berupa angka.");
            return;
        }

        int idPelanggan;
        try {
            System.out.print("ID Pelanggan    : ");
            idPelanggan = InputValidator.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID Pelanggan harus berupa angka.");
            return;
        }

        VPS vps = new VPS();
        vps.setNamaServer(namaServer);
        vps.setIpAddress(ipAddress);
        vps.setSistemOperasi(sistemOperasi);
        vps.setRam(ram);
        vps.setStorage(storage);
        vps.setIdPelanggan(idPelanggan);

        if (vpsService.tambahVPS(vps)) {
            System.out.println("VPS berhasil ditambahkan.");
        }
    }

    private static void lihatVPS() {
        System.out.println("\n--- Data VPS ---");
        List<VPS> list = vpsService.lihatSemuaVPS();
        if (list.isEmpty()) {
            System.out.println("Belum ada data VPS.");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-15s | %-15s | %-12s | %-6s | %-9s | %-8s | %-12s |\n",
                    "ID", "Server", "IP Address", "OS", "RAM", "Storage", "Status", "ID Pelanggan");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            for (VPS v : list) {
                System.out.printf("| %-5d | %-15s | %-15s | %-12s | %-4d GB | %-7d GB | %-8s | %-12d |\n",
                        v.getId(), v.getNamaServer(), v.getIpAddress(), v.getSistemOperasi(),
                        v.getRam(), v.getStorage(), v.getStatus(), v.getIdPelanggan());
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }

    private static void menuLaporan() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Menu Laporan ---");
            System.out.println("  1. Lihat VPS Aktif");
            System.out.println("  2. Total VPS");
            System.out.println("  3. Kembali");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine().trim();
            try {
                int menu = InputValidator.parseInteger(pilihan);
                switch (menu) {
                    case 1:
                        lihatVPSAktif();
                        break;
                    case 2:
                        tampilTotalVPS();
                        break;
                    case 3:
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private static void lihatVPSAktif() {
        System.out.println("\n--- Laporan VPS Aktif (View: v_laporan_vps) ---");
        List<String[]> list = vpsService.lihatVPSAktif();
        if (list.isEmpty()) {
            System.out.println("Tidak ada VPS aktif.");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-15s | %-15s | %-12s | %-6s | %-9s | %-8s | %-15s | %-20s |\n",
                    "ID", "Server", "IP Address", "OS", "RAM", "Storage", "Status", "Pelanggan", "Email");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for (String[] row : list) {
                System.out.printf("| %-5s | %-15s | %-15s | %-12s | %-4s GB | %-7s GB | %-8s | %-15s | %-20s |\n",
                        row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }

    private static void tampilTotalVPS() {
        System.out.println("\n--- Total VPS (Function: total_vps()) ---");
        int total = vpsService.getTotalVPS();
        System.out.println("Total VPS yang tersimpan di database: " + total);
    }
}
