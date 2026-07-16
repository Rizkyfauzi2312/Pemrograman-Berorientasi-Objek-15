package model;

public class VPS extends BaseEntity {

    private String namaServer;
    private String ipAddress;
    private String sistemOperasi;
    private int ram;
    private int storage;
    private String status;
    private int idPelanggan;

    public VPS() {
    }

    public VPS(int id, String namaServer, String ipAddress, String sistemOperasi,
               int ram, int storage, String status, int idPelanggan) {
        setId(id);
        this.namaServer = namaServer;
        this.ipAddress = ipAddress;
        this.sistemOperasi = sistemOperasi;
        this.ram = ram;
        this.storage = storage;
        this.status = status;
        this.idPelanggan = idPelanggan;
    }

    public String getNamaServer() {
        return namaServer;
    }

    public void setNamaServer(String namaServer) {
        this.namaServer = namaServer;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSistemOperasi() {
        return sistemOperasi;
    }

    public void setSistemOperasi(String sistemOperasi) {
        this.sistemOperasi = sistemOperasi;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    @Override
    public String tampilInfo() {
        return "ID: " + getId() + " | Server: " + namaServer + " | IP: " + ipAddress
                + " | OS: " + sistemOperasi + " | RAM: " + ram + " GB"
                + " | Storage: " + storage + " GB | Status: " + status
                + " | ID Pelanggan: " + idPelanggan;
    }
}
