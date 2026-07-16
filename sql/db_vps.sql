CREATE DATABASE IF NOT EXISTS db_vps;
USE db_vps;

CREATE TABLE pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nomor_hp VARCHAR(20) NOT NULL
);

CREATE TABLE vps (
    id_vps INT AUTO_INCREMENT PRIMARY KEY,
    nama_server VARCHAR(100) NOT NULL,
    ip_address VARCHAR(45) NOT NULL,
    sistem_operasi VARCHAR(50) NOT NULL,
    ram INT NOT NULL,
    storage INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Nonaktif',
    id_pelanggan INT NOT NULL,
    FOREIGN KEY (id_pelanggan) REFERENCES pelanggan(id_pelanggan)
);

DELIMITER //

CREATE PROCEDURE tambah_vps(
    IN p_nama_server VARCHAR(100),
    IN p_ip_address VARCHAR(45),
    IN p_sistem_operasi VARCHAR(50),
    IN p_ram INT,
    IN p_storage INT,
    IN p_id_pelanggan INT
)
BEGIN
    INSERT INTO vps (nama_server, ip_address, sistem_operasi, ram, storage, id_pelanggan)
    VALUES (p_nama_server, p_ip_address, p_sistem_operasi, p_ram, p_storage, p_id_pelanggan);
END //

CREATE FUNCTION total_vps()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE jumlah INT;
    SELECT COUNT(*) INTO jumlah FROM vps;
    RETURN jumlah;
END //

CREATE TRIGGER trg_set_status_aktif
BEFORE INSERT ON vps
FOR EACH ROW
BEGIN
    SET NEW.status = 'Aktif';
END //

DELIMITER ;

CREATE VIEW v_laporan_vps AS
SELECT v.id_vps, v.nama_server, v.ip_address, v.sistem_operasi,
       v.ram, v.storage, v.status,
       p.id_pelanggan, p.nama, p.email, p.nomor_hp
FROM vps v
JOIN pelanggan p ON v.id_pelanggan = p.id_pelanggan;
