package com.smart.can.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Developer on 3/ques/18.
 */

public class GlobalClass extends MultiDexApplication {

    String TAG = "app";

    public Boolean login_status = false;

    private static GlobalClass mInstance;

    String id;
    String name;
    String email;
    String phone_number;
    String fcm_reg_token;
    String deviceid;
    String profil_pic;
    String fname;
    String lname;

    public String device_type = "Android";
    public String login_from= "";
    public RequestQueue mRequestQueue;

    public static synchronized GlobalClass getInstance() {
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        TypeFacesUtils.overrideFont(getApplicationContext(), "Serif",
                "fonts/Raleway-Light.ttf");
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Boolean getLogin_status() {
        return login_status;
    }

    public void setLogin_status(Boolean login_status) {
        this.login_status = login_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getProfil_pic() {
        return profil_pic;
    }

    public void setProfil_pic(String profil_pic) {
        this.profil_pic = profil_pic;
    }

    public String getLogin_from() {
        return login_from;
    }

    public void setLogin_from(String login_from) {
        this.login_from = login_from;
    }


    /////////////////////

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

}
