package services;

import com.mongodb.client.model.Filters;
import dao.GenericDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import objects.LogAbsensi;
import objects.Mahasiswa;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Sorts;
import java.util.List;

public class LogAbsensiService {

    private final GenericDAO<LogAbsensi> dao;

    public LogAbsensiService() {
        dao = new GenericDAO<>("absensi_logs", LogAbsensi.class);
    }

    public boolean sudahAbsenHariIni(String uid) {

        LocalDate today = LocalDate.now();

        LocalDateTime awalHari = today.atStartOfDay();
        LocalDateTime akhirHari = today.atTime(LocalTime.MAX);

        Bson filter = Filters.and(
                Filters.eq("uid", uid),
                Filters.gte("waktuTap", awalHari),
                Filters.lte("waktuTap", akhirHari)
        );

        return dao.findOne(filter) != null;
    }

    public boolean simpanAbsensi(Mahasiswa mahasiswa) {

        if (sudahAbsenHariIni(mahasiswa.getUid())) {
            return false;
        }

        LogAbsensi log = new LogAbsensi();

        log.setUid(mahasiswa.getUid());
        log.setNim(mahasiswa.getNim());
        log.setNamaLengkap(mahasiswa.getNamaLengkap());
        log.setMataKuliah(mahasiswa.getMataKuliah());
        log.setWaktuTap(LocalDateTime.now());
        log.setStatus("HADIR");

        dao.save(log);

        return true;
    }
    
    public long resetSemuaAbsensi() {

        return dao.deleteAll();

    }
    
    public List<LogAbsensi> getRiwayatByNim(String nim){

        return dao.findMany(
                Filters.eq("nim", nim)
        )
        .stream()
        .sorted((a,b)->b.getWaktuTap().compareTo(a.getWaktuTap()))
        .toList();

    }

}