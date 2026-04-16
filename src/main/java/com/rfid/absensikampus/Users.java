package com.rfid.absensikampus;

/**
 *
 * @author bolem
 */
public class Users {

    private String uidrfid;
    private String idUser;
    private String namaLengkap;
    private String jabatan;
    
    public Users () {
        
    }

    public Users(String uidrfid, String idUser, String namaLengkap, String jabatan) {
        this.uidrfid = uidrfid;
        this.idUser = idUser;
        this.namaLengkap = namaLengkap;
        this.jabatan = jabatan;
    }

    public String getUidrfid() {
        return uidrfid;
    }

    public void setUidrfid(String uidrfid) {
        this.uidrfid = uidrfid;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public String toString() {
        return "Karyawan{" + 
                "uidRfid=" + uidrfid + 
                ", idKaryawan=" + idUser + 
                ", namaLengkap=" + namaLengkap + 
                ", departemen=" + jabatan + '}';
    }
}
