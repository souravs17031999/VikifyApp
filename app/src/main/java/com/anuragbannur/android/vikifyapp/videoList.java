package com.anuragbannur.android.vikifyapp;

import java.util.ArrayList;

public class videoList {

    private String mVideoName;

    public ArrayList<String> getmVideoNames() {
        return mVideoNames;
    }

    public void setmVideoNames(ArrayList<String> mVideoNames) {
        this.mVideoNames = mVideoNames;
    }

    public ArrayList<String> mVideoNames;

    public videoList(){

    }

    public videoList(String mVideoName) {
        this.mVideoName = mVideoName;
    }

    public String getmVideoName() {
        return mVideoName;
    }

    public void setmVideoName(String mVideoName) {
        this.mVideoName = mVideoName;
    }
}
