package com.sketch.smartcan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sketch.smartcan.Activity.ActivityBlog;
import com.sketch.smartcan.Activity.ActivityInfographic;
import com.sketch.smartcan.Activity.EnquiryActivity;
import com.sketch.smartcan.Activity.LoginActivity;
import com.sketch.smartcan.Activity.NewsActivity;
import com.sketch.smartcan.Activity.RegisterActivity;
import com.sketch.smartcan.Util.GlobalClass;

public class MainActivity extends AppCompatActivity {
    ImageView enquiry,img_news,info_graphic;
    GlobalClass globalClass;
    RelativeLayout rl_register,rl_login;
    LinearLayout llblog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enquiry=findViewById(R.id.enquiry);
        img_news=findViewById(R.id.img_news);
        rl_register=findViewById(R.id.rl_register);
        rl_login=findViewById(R.id.rl_login);
        llblog=findViewById(R.id.llblog);
        info_graphic=findViewById(R.id.info_graphic);
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
