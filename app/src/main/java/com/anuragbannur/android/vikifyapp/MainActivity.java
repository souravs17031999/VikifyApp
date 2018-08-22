package com.anuragbannur.android.vikifyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class MainActivity extends AppCompatActivity{
    private static  int SPLASH_TIME_OUT=60000;
    public final String mValueForStorage="Storage_values";
    public static final String SHARED_PREFS="sharedPrefs";
    public final String mValueForDb="Db_values";
    private DrawerLayout mDrawerLayout;

    VideoView mVideoView;
    String i="-1";
    String j="0";

     String i1="i1";
     String j1="j1";
    Button mRecordVideo,mPlayVideo,mUploadVideo;
    ImageView mImageView;
    public static final String TAG="MAINACTIVITY";
    public final int VIDEO_REQUEST_CODE=1;
    private StorageReference mStorageRef;
    private StorageReference mVideoRef;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menue, menu);
//        inflater.inflate(R.menu.,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//       Intent mRecordedVideo=new Intent(getApplicationContext(),recordedVideos.class);
//       startActivity(mRecordedVideo);

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

       return true;
    }

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);
        mPlayVideo = findViewById(R.id.buttonView);
        mRecordVideo = findViewById(R.id.buttonRecord);
        mUploadVideo = findViewById(R.id.buttonUpload);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        final Uri[] uri = new Uri[1];
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mVideoRef = mStorageRef.child("videos/video" + getVideoFileCount() + ".mp4");

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);




            mVideoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri downloadUrl) {
                    uri[0] = downloadUrl;
                }
            });


            mRecordVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"You have 60 seconds to finish recording q",Toast.LENGTH_SHORT).show();
                    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takeVideoIntent, VIDEO_REQUEST_CODE);
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homeIntent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    },SPLASH_TIME_OUT);

                }
            });

            mPlayVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVideoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUrl) {
                            mVideoView.setVideoURI(uri[0]);
                            mVideoView.start();
                        }
                    });
                }
            });
            loadData();
            upDateValues();
        }




        private String getVideoFileCount(){
            int fileNumber=Integer.parseInt(i);
            int newFileNumber=fileNumber+1;
            String newScreenValue=Integer.toString(newFileNumber);
            i=newScreenValue;
           // saveData();
            Log.v(TAG,"Screen from method:"+i);
            return newScreenValue;
        }


        private String numberForName(){
            int fileNumber=Integer.parseInt(j);
            int newFileNumber=fileNumber+1;
            String newScreenValue=Integer.toString(newFileNumber);
            j=newScreenValue;
           // saveData();
            Log.v(TAG,"Screenfrommethod:"+j);
            return j;
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == VIDEO_REQUEST_CODE && resultCode == RESULT_OK) {
            final Uri videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
            mVideoView.start();
            Log.v(TAG,"VideoURI"+videoUri);
            mUploadVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Please, wait upload in progress",Toast.LENGTH_SHORT).show();
                    if(videoUri!=null){
                        String number=numberForName();
                        String number1=getVideoFileCount();
                        saveData(number,number1);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("VideoName"+number);
                        myRef.setValue("Video "+number);

                        mStorageRef= FirebaseStorage.getInstance().getReference();
                        mVideoRef=mStorageRef.child("videos/video"+number1+".mp4");

                        UploadTask uploadTask=mVideoRef.putFile(videoUri);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Upload Unsuccessfull",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(),"Upload Successfull",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                updateProgress(taskSnapshot);
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Nothing to upload",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void updateProgress(UploadTask.TaskSnapshot taskSnapshot){
        long fileSize=taskSnapshot.getTotalByteCount();

        long uploadBytes=taskSnapshot.getBytesTransferred();

        long progress=(100*uploadBytes)/fileSize;
        ProgressBar progressBar=findViewById(R.id.pbar);
        progressBar.setProgress((int)progress);
    }


    public  void saveData(String i,String j){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(mValueForStorage,i);
        editor.putString(mValueForDb,j);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        i1=sharedPreferences.getString(mValueForStorage,"0");
        j1=sharedPreferences.getString(mValueForDb,"0");
    }

    public void upDateValues(){
        i=i1;
        j=j1;
    }

}

