package com.anuragbannur.android.vikifyapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class playVideo extends AppCompatActivity {
TextView mTextView;
VideoView mVideoView;
    private StorageReference mStorageRef;
    private StorageReference mVideoRef;

private static final String TAG="PLAYVIDEO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        mTextView=findViewById(R.id.videoNumber);
        mVideoView=findViewById(R.id.videoView2);

        Intent mIntent=getIntent();
        int Number=mIntent.getIntExtra("VideoNumber",0);
        String mNumber=Integer.toString(Number);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mVideoRef = mStorageRef.child("videos/video"+mNumber+".mp4");
        final Uri[] uri = new Uri[1];

        mVideoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri downloadUrl) {
                uri[0] = downloadUrl;
                Toast.makeText(getApplicationContext(),"Please, wait",Toast.LENGTH_SHORT).show();
                mVideoView.setVideoURI(uri[0]);

                mVideoView.start();
            }
        });
        mTextView.setText("Video "+mNumber+" is playing");
        Log.v(TAG,"Number:"+Number);




    }
}
