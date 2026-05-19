package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.rfid.absensikampus.MongoManager;
import objects.Mahasiswa;

public class MahasiswaServices {

    MongoDatabase db = MongoManager.getDatabase();
    MongoCollection<Document> col = db.getCollection("Mahasiswa");

    // SAVE DATA
    public void tambahMahasiswa(Mahasiswa mhs) {

        Document doc = new Document("uid", mhs.getUid())
                .append("nim", mhs.getNim())
                .append("namaLengkap", mhs.getNamaLengkap())
                .append("mataKuliah", mhs.getMataKuliah());

        col.insertOne(doc);
    }

    // UPDATE DATA
    public void updateMahasiswa(Mahasiswa mhs) {

        Document query = new Document("uid", mhs.getUid());

        Document dataBaru = new Document("$set",
                new Document("nim", mhs.getNim())
                        .append("namaLengkap", mhs.getNamaLengkap())
                        .append("mataKuliah", mhs.getMataKuliah()));

        col.updateOne(query, dataBaru);
    }

    // DELETE DATA
    public void deleteMahasiswa(String uid) {

        Document query = new Document("uid", uid);

        col.deleteOne(query);
    }
}