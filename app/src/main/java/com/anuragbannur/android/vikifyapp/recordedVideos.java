package com.anuragbannur.android.vikifyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class recordedVideos extends AppCompatActivity {
    private final static String TAG="RecordVideos";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private StorageReference mStorageRef;
    private DatabaseReference mFirebaseDatabase;
    private StorageReference mVideoRef;
    videoList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_videos);




        // Write a message to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<HashMap<String, Object>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Object>>() {};
                Map<String, Object> objectHashMap = snapshot.getValue(objectsGTypeInd);
                ArrayList<Object> objectArrayList = new ArrayList<Object>(objectHashMap.values());
                ArrayList<Object> mActualList =reverse(objectArrayList);


                Log.v(TAG,"ArrayList"+objectArrayList);
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                mAdapter=new MyAdapter(mActualList);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        }


    public ArrayList<Object> reverse(ArrayList<Object> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }
}
