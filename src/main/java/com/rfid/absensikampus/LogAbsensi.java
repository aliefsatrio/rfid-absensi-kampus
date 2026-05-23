package com.rfid.absensikampus;

import java.time.LocalDateTime;


public class LogAbsensi {
    
    private String idLog;
    private String uidrfid;
    private LocalDateTime waktuTap;
    private String status;
    
    public LogAbsensi() {
        //
    }

    public LogAbsensi(String idLog, String uidrfid, LocalDateTime waktuTap, String status) {
        this.idLog = idLog;
        this.uidrfid = uidrfid;
        this.waktuTap = waktuTap;
        this.status = status;
    }

    public String getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLog) {
        this.idLog = idLog;
    }

    public String getUidrfid() {
        return uidrfid;
    }

    public void setUidrfid(String uidrfid) {
        this.uidrfid = uidrfid;
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
