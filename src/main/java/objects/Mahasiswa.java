package objects;

import org.bson.types.ObjectId;

public class Mahasiswa {

    private ObjectId id;

    private String uid;
    private String nim;
    private String namaLengkap;
    private String mataKuliah;

    // Login Mahasiswa
    private String username;
    private String password;

    public Mahasiswa() {
    }

    public Mahasiswa(String uid,
                     String nim,
                     String namaLengkap,
                     String mataKuliah,
                     String username,
                     String password) {

        this.uid = uid;
        this.nim = nim;
        this.namaLengkap = namaLengkap;
        this.mataKuliah = mataKuliah;
        this.username = username;
        this.password = password;
    }

    // ===========================
    // Getter & Setter ObjectId
    // ===========================

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    // ===========================
    // Getter & Setter UID
    // ===========================

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    // ===========================
    // Getter & Setter NIM
    // ===========================

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    // ===========================
    // Getter & Setter Nama
    // ===========================

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    // ===========================
    // Getter & Setter Mata Kuliah
    // ===========================

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    // ===========================
    // Getter & Setter Username
    // ===========================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ===========================
    // Getter & Setter Password
    // ===========================

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "uid='" + uid + '\'' +
                ", nim='" + nim + '\'' +
                ", namaLengkap='" + namaLengkap + '\'' +
                ", mataKuliah='" + mataKuliah + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}