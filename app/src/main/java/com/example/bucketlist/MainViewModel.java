package com.example.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {

    private BucketItemRepository mRepository;
    private LiveData<List<BucketItem>> mItems;

    public MainViewModel(Context context){
        mRepository = new BucketItemRepository(context);
        mItems = mRepository.getAllBucketItems();
    }

    public LiveData<List<BucketItem>> getItems() {
        return mItems;
    }

    public void insert(BucketItem item){
        mRepository.insert(item);
    }

    public void update(BucketItem item){
        mRepository.update(item);
    }

    public void delete(BucketItem item){
        mRepository.delete(item);
    }
}
