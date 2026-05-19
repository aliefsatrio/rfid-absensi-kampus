package objects;

import java.time.LocalDateTime;

public class LogAbsensi {
    private String idLog;
    private String uidRfid;
    private String idKaryawan;
    private String namaLengkap;
    private String divisi;
    private String jabatan;
    private LocalDateTime waktuTap;
    private String status;

    public LogAbsensi() {
        //
    }

    public LogAbsensi(String idLog, String uidRfid, String idKaryawana, String namaLengkap, String divisi, String jabatan, LocalDateTime waktuTap, String status) {
        this.idLog = idLog;
        this.uidRfid = uidRfid;
        this.idKaryawan = idKaryawana;
        this.namaLengkap = namaLengkap;
        this.divisi = divisi;
        this.jabatan = jabatan;
        this.waktuTap = waktuTap;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLog) {
        this.idLog = idLog;
    }

    public String getUidRfid() {
        return uidRfid;
    }

    public void setUidRfid(String uidRfid) {
        this.uidRfid = uidRfid;
    }
    
    public String getidKaryawan() {
        return idKaryawan;
    }

    public void setidKaryawan(String uidRfid) {
        this.idKaryawan = uidRfid;
    }
    
    public String getnamaLengkap() {
        return namaLengkap;
    }
    
    public void setnamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
    
    public String getDivisi() {
        return divisi;
    }

    public void setdivisi(String divisi) {
        this.divisi = divisi;
    }
    
    public String getjabatan() {
        return jabatan;
    }

    public void setjabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public LocalDateTime getWaktuTap() {
        return waktuTap;
    }

    public void setWaktuTap(LocalDateTime waktuTap) {
        this.waktuTap = waktuTap;
    }
    
    
    
    
}