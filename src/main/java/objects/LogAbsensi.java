package objects;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

public class LogAbsensi {

    private ObjectId id;

    private String uid;
    private String nim;
    private String namaLengkap;
    private String mataKuliah;

    private LocalDateTime waktuTap;

    private String status;

    public LogAbsensi() {
    }

    public LogAbsensi(String uid,
                      String nim,
                      String namaLengkap,
                      String mataKuliah,
                      LocalDateTime waktuTap,
                      String status) {

        this.uid = uid;
        this.nim = nim;
        this.namaLengkap = namaLengkap;
        this.mataKuliah = mataKuliah;
        this.waktuTap = waktuTap;
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public LocalDateTime getWaktuTap() {
        return waktuTap;
    }

    public void setWaktuTap(LocalDateTime waktuTap) {
        this.waktuTap = waktuTap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}