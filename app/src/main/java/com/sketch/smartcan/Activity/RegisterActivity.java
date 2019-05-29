package com.sketch.smartcan.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    ImageView img_back,img_home;
    RelativeLayout rl_login;
    TextView tv_header,tv_register,tv_login;
    EditText edt_name,edt_email,edt_phone,edt_agent_id;
    Typeface typeface,typeface_bold,typeface_medium,typeface_light,typeface_regular;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        InitFont();
        InitView();
        SetFont();


        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
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
    public void InitView(){
        img_back=findViewById(R.id.img_back);
        rl_login=findViewById(R.id.rl_login);
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_mail);
        edt_phone=findViewById(R.id.edt_phone);
        edt_agent_id=findViewById(R.id.edt_agent_id);
        tv_header=findViewById(R.id.tv_header);
        tv_register=findViewById(R.id.tv_register);
        tv_login=findViewById(R.id.tv_login);
        img_home=findViewById(R.id.img_home);
    }


    public void SetFont(){
        tv_header.setTypeface(typeface_bold);
        edt_agent_id.setTypeface(typeface_regular);
        edt_name.setTypeface(typeface_regular);
        edt_email.setTypeface(typeface_regular);
        edt_phone.setTypeface(typeface_regular);
        tv_register.setTypeface(typeface_bold);
        tv_login.setTypeface(typeface_bold);
    }
    private static boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
