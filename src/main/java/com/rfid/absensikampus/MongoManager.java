package com.rfid.absensikampus;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoManager {
    private static MongoClient mongoClient;
    private static final String DATABASE_NAME = "absensi_mahasiswa";

    public static MongoDatabase getDatabase() {
        // 1. Inisialisasi mongoClient JIKA belum dibuat
        if (mongoClient == null) {
            // Inisiasi koneksi ke MongoDB Localhost
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            System.out.println("Koneksi MongoDB berhasil dibuat!");
        }
        
        // 2. Siapkan CodecRegistry untuk mapping class Java (POJO) ke BSON MongoDB
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // 3. Selalu kembalikan database BESERTA codec registry-nya
        return mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
    }
}