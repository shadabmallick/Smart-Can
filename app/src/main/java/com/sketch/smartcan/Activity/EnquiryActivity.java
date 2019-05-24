package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;

import java.util.Locale;

public class EnquiryActivity extends AppCompatActivity {
    boolean isPlay = false;
    TextView tv_submit,title,tv_new,tv_blog,tv_info,tv_enq;
    ImageView img_home,img_news,info_graphic;
    LinearLayout llblog;
    RelativeLayout rl_login;
    EditText edt_name,edt_company,edt_location,edt_mail,edt_phone,edt_message;
    Typeface typeface_bold,typeface_light,typeface_regular,typeface_medium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        AssetManager am = getApplicationContext().getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        img_news=findViewById(R.id.img_news);
        title=findViewById(R.id.title);
        rl_login=findViewById(R.id.rl_login);
        img_home=findViewById(R.id.img_home);
        tv_new=findViewById(R.id.tv_enq_new);
        tv_enq=findViewById(R.id.tv_enq_enquiry);
        tv_info=findViewById(R.id.tv_enq_info);
        tv_blog=findViewById(R.id.tv_enq_blog);
        info_graphic=findViewById(R.id.info_graphic);
        tv_submit=findViewById(R.id.tv_submit);
        llblog=findViewById(R.id.llblog);
        edt_name=findViewById(R.id.edt_name);
        edt_mail=findViewById(R.id.edt_mail);
        edt_company=findViewById(R.id.edt_company);
        edt_location=findViewById(R.id.edt_location);
        edt_phone=findViewById(R.id.edt_phone);
        edt_message=findViewById(R.id.edt_message);

        title.setTypeface(typeface_bold);
        tv_blog.setTypeface(typeface_regular);
        tv_enq.setTypeface(typeface_regular);
        tv_info.setTypeface(typeface_regular);
        tv_new.setTypeface(typeface_regular);
        edt_name.setTypeface(typeface_regular);
        edt_mail.setTypeface(typeface_regular);
        edt_company.setTypeface(typeface_regular);
                edt_location.setTypeface(typeface_regular);
        edt_phone.setTypeface(typeface_regular);
        edt_message.setTypeface(typeface_regular);
        tv_submit.setTypeface(typeface_bold);
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        info_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityInfographic.class));
            }
        });
        img_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
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
