package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sketch.smartcan.R;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout rl_forget_pass, rl_login;
    TextView tv_register,tv_title;
    ImageView img_back;
    Typeface typeface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AssetManager am = getApplicationContext().getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        rl_forget_pass=findViewById(R.id.rl_forget_pass);
        rl_login=findViewById(R.id.rl_login);
        tv_register=findViewById(R.id.tv_register);
        img_back=findViewById(R.id.img_back);
        tv_title=findViewById(R.id.tv_title);
        tv_title.setTypeface(typeface);




        buttonOnClick();
    }


    private void buttonOnClick(){

        rl_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Container.class);
                startActivity(intent);

            }
        });


    }
}
