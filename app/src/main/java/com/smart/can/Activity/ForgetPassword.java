package com.smart.can.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.can.R;

import java.util.Locale;

public class ForgetPassword  extends AppCompatActivity {
    ImageView img_back;
    TextView tv_title,tv_submit,tv_forget,tv_new,tv_register;
    EditText user_id;
    Typeface typeface_bold,typeface_light,typeface_regular,typeface_medium;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));

        }

        AssetManager am = getApplicationContext().getAssets();
        img_back=findViewById(R.id.img_back);
        tv_submit=findViewById(R.id.tv_forget);
        tv_title=findViewById(R.id.tv_title);
        user_id=findViewById(R.id.edt_user_id);
       // tv_forget=findViewById(R.id.tv_login);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
       // tv_new=findViewById(R.id.tv_new);
        tv_register=findViewById(R.id.tv_register);
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        tv_title.setTypeface(typeface_bold);
        tv_submit.setTypeface(typeface_bold);
       // tv_forget.setTypeface(typeface_medium);
       // tv_new.setTypeface(typeface_medium);
        tv_register.setTypeface(typeface_medium);
        user_id.setTypeface(typeface_regular);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
