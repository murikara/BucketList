package com.example.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Deze interface handelt de queries af voor een bucket item
 */
@Dao
public interface BucketListDao {

    @Query("SELECT * FROM bucket_item")
    public LiveData<List<BucketItem>> getAllBucketItems();

    @Insert
    void insertItems(BucketItem item);

    @Delete
    void deleteItems(BucketItem item);

    @Update
    void updateItems(BucketItem item);
}
