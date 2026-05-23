package objects;

import org.bson.types.ObjectId;

public class Mahasiswa {

    private ObjectId id; // Ubah dari String ke ObjectId
    private String uid;
    private String nim;
    private String namaLengkap;
    private String mataKuliah;
    
    public Mahasiswa(String uid, String nim, String namaLengkap, String mataKuliah) {
        this.uid = uid;
        this.nim = nim;
        this.namaLengkap = namaLengkap;
        this.mataKuliah = mataKuliah;
    }

    public Mahasiswa() {
        
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
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