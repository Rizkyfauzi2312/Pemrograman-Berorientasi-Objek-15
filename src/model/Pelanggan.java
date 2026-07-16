package model;

public class Pelanggan extends BaseEntity {

    private String nama;
    private String email;
    private String nomorHp;

    public Pelanggan() {
    }

    public Pelanggan(int id, String nama, String email, String nomorHp) {
        setId(id);
        this.nama = nama;
        this.email = email;
        this.nomorHp = nomorHp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    @Override
    public String tampilInfo() {
        return "ID: " + getId() + " | Nama: " + nama + " | Email: " + email + " | No HP: " + nomorHp;
    }
}
