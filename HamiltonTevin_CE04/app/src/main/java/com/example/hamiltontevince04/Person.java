package com.example.hamiltontevince04;

import android.widget.ImageView;

public class Person {
    private String  mFirstName = "";
    private String mLastName  = "";
    private String mBirthday = "";
    private Integer mUserImage;

    Person(String firstName,String lastName, String birthday,Integer userimage){
        mFirstName = firstName;
        mLastName = lastName;
        mBirthday = birthday;
        mUserImage = userimage;
    }

    public String getFirstName(){return mFirstName;}
    public String getLastName(){return mLastName;}
    public String getBirthday(){return mBirthday;}
    public Integer getUserImage(){ return mUserImage;}

    public String toSring(){
        return mFirstName +" "+ mLastName;
    }

}
