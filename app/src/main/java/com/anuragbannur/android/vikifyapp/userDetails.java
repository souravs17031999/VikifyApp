package com.anuragbannur.android.vikifyapp;

import android.net.Uri;

public class userDetails {
    String mpersonName;
    String mpersonGivenName;
    String mpersonFamilyName;
    String mpersonEmail;
    String mpersonId;
    Uri mpersonPhoto;

    public userDetails() {
    }

    public userDetails(String personName, String personGivenName, String personFamilyName, String personEmail, String personId, Uri personPhoto) {
        mpersonName = personName;
       mpersonGivenName = personGivenName;
        mpersonFamilyName = personFamilyName;
        mpersonEmail = personEmail;
        mpersonId = personId;
        mpersonPhoto = personPhoto;

    }

    public String getPersonName() {
        return mpersonName;
    }

    public void setPersonName(String personName) {
        mpersonName = personName;
    }

    public String getPersonGivenName() {
        return mpersonGivenName;
    }

    public void setPersonGivenName(String personGivenName) {
        mpersonGivenName = personGivenName;
    }

    public String getPersonFamilyName() {
        return mpersonFamilyName;
    }

    public void setPersonFamilyName(String personFamilyName) {
        mpersonFamilyName = personFamilyName;
    }

    public String getPersonEmail() {
        return mpersonEmail;
    }

//    public void setPersonEmail(String personEmail) {
//        this.personEmail = personEmail;
//    }

    public String getPersonId() {
        return mpersonId;
    }

//    public void setPersonId(String personId) {
//        this.personId = personId;
//    }

    public Uri getPersonPhoto() {
        return mpersonPhoto;
    }

//    public void setPersonPhoto(Uri personPhoto) {
//        this.personPhoto = personPhoto;
//    }
}
