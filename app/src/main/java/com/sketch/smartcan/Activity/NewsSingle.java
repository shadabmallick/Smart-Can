package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;

import java.util.Locale;

public class NewsSingle extends AppCompatActivity {
    TextView tv_header,tv_title,tv_date,tv_title_single,tv_title_brief;
    ImageView img_back,img_home;
    Typeface typeface,typeface_bold,typeface_medium,typeface_light,typeface_regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nes_single);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        InitFont();
        initView();
        SetFont();

    }
    public void InitFont(){
        AssetManager am = getApplicationContext().getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));

    }
    public void initView(){


        img_home=findViewById(R.id.img_home);
        tv_date=findViewById(R.id.tv_date);
        tv_header=findViewById(R.id.title);
        tv_title_single=findViewById(R.id.tv_title_single);
        tv_title_brief=findViewById(R.id.tv_title_brief);

        tv_header=findViewById(R.id.title);

        img_back =findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });


    }
    public void SetFont(){
        tv_header.setTypeface(typeface_bold);
        tv_date.setTypeface(typeface_regular);
        tv_title_single.setTypeface(typeface_regular);
        tv_title_brief.setTypeface(typeface_regular);


    }



}
