package com.sketch.smartcan;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sketch.smartcan.Activity.ActivityBlog;
import com.sketch.smartcan.Activity.ActivityInfographic;
import com.sketch.smartcan.Activity.EnquiryActivity;
import com.sketch.smartcan.Activity.LoginActivity;
import com.sketch.smartcan.Activity.NewsActivity;
import com.sketch.smartcan.Activity.RegisterActivity;
import com.sketch.smartcan.Util.GlobalClass;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView enquiry,img_news,info_graphic;
    GlobalClass globalClass;
    RelativeLayout rl_register,rl_login;
    LinearLayout llblog;
    TextView tv_login,tv_registration,tv_new,tv_blog,tv_info,tv_enq;

    Typeface typeface_bold,typeface_medium,typeface_light,typeface_regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager am = getApplicationContext().getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        enquiry=findViewById(R.id.enquiry);

        img_news=findViewById(R.id.img_news);
        rl_register=findViewById(R.id.rl_register);
        rl_login=findViewById(R.id.rl_login);
        llblog=findViewById(R.id.llblog);
        info_graphic=findViewById(R.id.info_graphic);
        tv_login=findViewById(R.id.tv_login);
        tv_registration=findViewById(R.id.tv_registration);
      //  title=findViewById(R.id.title);
        tv_new=findViewById(R.id.tv_enq_new);
        tv_enq=findViewById(R.id.tv_enq_enquiry);
        tv_info=findViewById(R.id.tv_enq_info);
        tv_blog=findViewById(R.id.tv_enq_blog);
      //  title.setTypeface(typeface_bold);
        tv_blog.setTypeface(typeface_regular);
        tv_enq.setTypeface(typeface_regular);
        tv_info.setTypeface(typeface_regular);
        tv_new.setTypeface(typeface_regular);
        tv_login.setTypeface(typeface_bold);
        tv_registration.setTypeface(typeface_bold);

        globalClass = (GlobalClass)getApplicationContext();
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), EnquiryActivity.class));
            }

        });
        rl_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        img_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });

        info_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityInfographic.class));
            }
        });
        llblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityBlog.class));
            }
        });
    }
}
