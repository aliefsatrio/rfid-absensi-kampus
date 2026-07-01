package com.rfid.absensikampus;

import com.ituhn.pemkom2.dao.BaseDAO;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi Generic DAO untuk MongoDB yang efisien dan reusable.
 * @param <T>
 */
public class GenericDAO<T> implements BaseDAO<T> {
    private final MongoCollection<T> collection;
    private final Class<T> clazz;

    // Konstruktor menerima nama koleksi dan kelas entitas untuk mapping otomatis
    public GenericDAO(String collectionName, Class<T> clazz) {
        this.clazz = clazz;
        this.collection = MongoManager.getDatabase().getCollection(collectionName, clazz);
    }

    public void save(T entity) {
        collection.insertOne(entity);
    }

    
    public void update(Bson filter, T entity) {
        collection.updateOne(filter, new org.bson.Document("$set", entity));
    }

    public void delete(Bson filter) {
        collection.deleteOne(filter);
    }

    public List<T> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    public T findOne(Bson filter) {
        return collection.find(filter).first();
    }

    public List<T> findMany(Bson filter) {
        return collection.find(filter).into(new ArrayList<>());
    }
}