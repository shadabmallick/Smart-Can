package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sketch.smartcan.AdapterClass.DataAdapter;
import com.sketch.smartcan.AndroidVersion;
import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;

import java.util.ArrayList;
import java.util.Locale;

public class NewsActivity extends AppCompatActivity {
     String TAG="News";
    LinearLayout llenquiry,llinfo,llblog;

    Typeface typeface_bold,typeface_medium,typeface_light,typeface_regular;

    TextView title,tv_new,tv_blog,tv_info,tv_enq;
    private final String android_version_names[] = {
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum",
            "What is Lorem Ipsum"
    };
    ImageView enquiry,img_home;

    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        AssetManager am = getApplicationContext().getAssets();

        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        initViews();
    }
    private void initViews(){
        enquiry=findViewById(R.id.enquiry);
        img_home=findViewById(R.id.img_home);
        llblog=findViewById(R.id.llblog);
        llenquiry=findViewById(R.id.llenquiry);
        llinfo=findViewById(R.id.llinfo);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyler_view_news);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        tv_new=findViewById(R.id.tv_enq_new);
        tv_enq=findViewById(R.id.tv_enq_enquiry);
        tv_info=findViewById(R.id.tv_enq_info);
        tv_blog=findViewById(R.id.tv_enq_blog);
        ArrayList<AndroidVersion> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EnquiryActivity.class));
            }
        });
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        llinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityInfographic.class));

            }
        });
        llenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnquiryActivity.class));

            }
        });
        llblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityBlog.class));

            }
        });

    }
    private ArrayList<AndroidVersion> prepareData(){

        ArrayList<AndroidVersion> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
            Log.d(TAG, "prepareData: "+androidVersion.getAndroid_image_url());
        }
        return android_version;
    }
}