package com.example.shreya.jsonparsing;

import android.os.Build;

/**
 * Created by shreya on 29/11/17.
 */

public class userPost {

    private String osName = "Android";
    private int osVersion = android.os.Build.VERSION.SDK_INT;
    private String device = Build.MODEL;
    private String Title;
    private boolean userIsATalker;

    public userPost(String t) {
        this.Title = t;
        this.userIsATalker = (t.length() > 200);
    }

}
