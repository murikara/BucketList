package com.example.bucketlist;

import android.app.usage.NetworkStats;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketItemRepository {

    private AppDatabase mAppDatabase;
    private BucketListDao mBucketListDao;
    private LiveData<List<BucketItem>> mItems;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public BucketItemRepository(Context context){
        mAppDatabase = AppDatabase.getInstance(context);
        mBucketListDao = mAppDatabase.bucketListDao();
        mItems = mBucketListDao.getAllBucketItems();
    }

    public LiveData<List<BucketItem>> getAllBucketItems() {
        return mItems;
    }

    public void insert(final BucketItem item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.insertItems(item);
            }
        });
    }

    public void update(final BucketItem item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.updateItems(item);
            }
        });
    }


    public void delete(final BucketItem item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.deleteItems(item);
            }
        });
    }
}
