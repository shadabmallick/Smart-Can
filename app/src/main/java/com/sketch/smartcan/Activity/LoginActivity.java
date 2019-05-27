package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;

import java.util.EnumMap;
import java.util.Locale;

import static com.android.volley.Request.Method.HEAD;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout rl_forget_pass,rl_register_user,rl_login;
    TextView tv_register,tv_title,tv_login,tv_forget,tv_text_sign;
    ImageView img_back,img_home;
   EditText edt_user_id,edt_pass;
    Typeface typeface,typeface_bold,typeface_medium,typeface_light,typeface_regular;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            InitFont();
            initView();
            SetFont();



        AssetManager am = getApplicationContext().getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        rl_forget_pass=findViewById(R.id.rl_forget_pass);

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


        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
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

        rl_forget_pass=findViewById(R.id.rl_forget_pass);
        tv_register=findViewById(R.id.tv_register);
        img_home=findViewById(R.id.img_home);
        rl_register_user=findViewById(R.id.rl_new_user);
        tv_title=findViewById(R.id.tv_title);
       tv_text_sign =findViewById(R.id.tv_new);
       tv_login =findViewById(R.id.tv_login);
        edt_user_id =findViewById(R.id.edt_user_id);
        edt_pass =findViewById(R.id.edt_pass);
        tv_forget =findViewById(R.id.tv_forget);
        img_back =findViewById(R.id.img_back);
        rl_login =findViewById(R.id.rl_login);

      //  tv_title.setTypeface(typeface);

    }

private static boolean emailValidate(String email){
        return !TextUtils.isEmpty(email)&& android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
}
    public void SetFont(){
        tv_title.setTypeface(typeface);
        tv_login.setTypeface(typeface_bold);
        tv_text_sign.setTypeface(typeface_bold);
        tv_register.setTypeface(typeface_bold);
        edt_pass.setTypeface(typeface_regular);
        edt_user_id.setTypeface(typeface_regular);
        tv_forget.setTypeface(typeface_bold);


        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Container.class);
                startActivity(intent);

            }
        });



    }
}
