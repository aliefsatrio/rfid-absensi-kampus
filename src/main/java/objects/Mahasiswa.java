package objects;

public class Mahasiswa {

    private String uid;
    private String nim;
    private String namaLengkap;
    private String mataKuliah;

    // Setter
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    // Getter
    public String getUid() {
        return uid;
    }

    public String getNim() {
        return nim;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }
}