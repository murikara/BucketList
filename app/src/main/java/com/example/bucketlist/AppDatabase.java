package com.example.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Deze klasse maakt de database voor een bucket item object
 */
@Database(entities = {BucketItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BucketListDao bucketListDao();

    private final static String NAME_DATABASE = "bucket_item_db";

    //Static instance
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {

        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class,   NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }
}
