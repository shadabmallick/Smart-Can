package com.sketch.smartcan.Activity;

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

import com.sketch.smartcan.R;

import java.util.Locale;

public class ComplainDetails extends AppCompatActivity {
    TextView toolbar_title,tv_order_id,tv_order_value,
            tv_date,tv_date_value,tv_picture,tv_complain,tv_complain_value;
    EditText edt_complain,edt_reply;
    ImageView img_back;
    Typeface typeface,typeface_bold,typeface_medium,typeface_light,typeface_regular;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_details);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        toolbar_title=findViewById(R.id.toolbar_title);
        img_back=findViewById(R.id.iv_back);
        tv_order_id=findViewById(R.id.tv1);
        tv_order_value=findViewById(R.id.edt_order_id);
        tv_date=findViewById(R.id.tv2);
        tv_date_value=findViewById(R.id.edt_date);
        tv_complain=findViewById(R.id.complain_id);
        tv_complain_value=findViewById(R.id.complain_text);
        edt_complain=findViewById(R.id.edt_complain);
        edt_reply=findViewById(R.id.edt_reply);
        tv_picture=findViewById(R.id.tv_picture);
        AssetManager am = getApplicationContext().getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        toolbar_title.setText("Complain Details" );
        toolbar_title.setTypeface(typeface_bold);
        tv_picture.setTypeface(typeface_medium);
        tv_date_value.setTypeface(typeface_medium);
        tv_order_id.setTypeface(typeface_medium);
        tv_date.setTypeface(typeface_medium);
        tv_complain.setTypeface(typeface_medium);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
