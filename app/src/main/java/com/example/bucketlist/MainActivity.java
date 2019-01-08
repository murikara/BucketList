package com.example.bucketlist;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BucketItemAdapter.BucketItemClickListener {

    @BindView(R.id.bucketListRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.titleEditText)
    EditText titleEditText;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    private List<BucketItem> mItems;
    private BucketItemAdapter mAdapter;
    private MainViewModel mMainViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainViewModel = new MainViewModel(getApplicationContext());
        mMainViewModel.getItems().observe(this, new Observer<List<BucketItem>>() {
            @Override
            public void onChanged(@Nullable List<BucketItem> items) {
                mItems = items;
                updateUI();
            }
        });
    }

    /**
     * Update de UI
     */
    private void updateUI() {
        Log.e(TAG, "updateUI: in the method now" );
        if (mAdapter == null) {
            mAdapter = new BucketItemAdapter(mItems, this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            Log.e(TAG, "updateUI: created new Adapter" );
        } else {
            mAdapter.swapList(mItems);
            mAdapter.notifyDataSetChanged();
            Log.e(TAG, "updateUI: swapped the list" );
        }
        Log.e(TAG, "updateUI: leaving the method" );
    }

    private void addBucketItem(){
        if(titleEditText.getText() != null && descriptionEditText.getText() != null) {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            BucketItem item = new BucketItem(title, description, false);
            Log.e(TAG, "addBucketItem: inserting item: " + item.getTitel()
                    + " " + item.getDescription());
            mMainViewModel.insert(item);
            Snackbar.make(findViewById(R.id.ConstraintLayout), "Succesfully added item", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Log.e(TAG, "addBucketItem: NO EDIT TEXT" );
            Snackbar.make(findViewById(R.id.ConstraintLayout), "Fill in the information first!", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.addItemFAB)
    public void addItemFAB(){
        addBucketItem();
    }

    @Override
    public void BucketItemOnClick(int i, boolean checked) {
        if(mItems != null){
            mItems.get(i).setChecked(checked);
        }
    }
}
