package com.sketch.smartcan.Activity;


import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sketch.smartcan.R;

import java.util.Locale;

public class OrderTrack extends Activity {

    TextView toolbar_title, tv_order_id, msg1, msg2;
    ImageView iv_back;
    View view10, view11, view20, view21, view30, view31, view40, view41, view50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_track);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        initViews();

    }


    public void initViews(){
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));


        toolbar_title = findViewById(R.id.toolbar_title);
        iv_back = findViewById(R.id.iv_back);
        tv_order_id = findViewById(R.id.tv_order_id);
        view10 = findViewById(R.id.view10);
        view11 = findViewById(R.id.view11);
        view20 = findViewById(R.id.view20);
        view21 = findViewById(R.id.view21);
        view30 = findViewById(R.id.view30);
        view31 = findViewById(R.id.view31);
        view40 = findViewById(R.id.view40);
        view41 = findViewById(R.id.view41);
        view50 = findViewById(R.id.view50);
        msg1 = findViewById(R.id.msg1);
        msg2 = findViewById(R.id.msg2);

        tv_order_id.setTypeface(typeface);

        toolbar_title.setText("Order Track");


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        setOrderStatus(3);

    }


    public void setOrderStatus(int status){

        switch (status){

            case 1:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_blue));
                view11.setBackgroundColor(getResources().getColor(R.color.light_gray));

                view20.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view21.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view30.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view31.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view40.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view41.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view50.setBackground(getResources().getDrawable(R.drawable.circle_orange));


                break;
            case 2:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view11.setBackgroundColor(getResources().getColor(R.color.status_green));

                view20.setBackground(getResources().getDrawable(R.drawable.circle_blue));
                view21.setBackgroundColor(getResources().getColor(R.color.light_gray));

                view30.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view31.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view40.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view41.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view50.setBackground(getResources().getDrawable(R.drawable.circle_orange));


                break;
            case 3:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view11.setBackgroundColor(getResources().getColor(R.color.status_green));
                view20.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view21.setBackgroundColor(getResources().getColor(R.color.status_green));

                view30.setBackground(getResources().getDrawable(R.drawable.circle_blue));
                view31.setBackgroundColor(getResources().getColor(R.color.light_gray));

                view40.setBackground(getResources().getDrawable(R.drawable.circle_orange));
                view41.setBackgroundColor(getResources().getColor(R.color.light_gray));
                view50.setBackground(getResources().getDrawable(R.drawable.circle_orange));

                break;
            case 4:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view11.setBackgroundColor(getResources().getColor(R.color.status_green));
                view20.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view21.setBackgroundColor(getResources().getColor(R.color.status_green));
                view30.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view31.setBackgroundColor(getResources().getColor(R.color.status_green));

                view40.setBackground(getResources().getDrawable(R.drawable.circle_blue));
                view41.setBackgroundColor(getResources().getColor(R.color.light_gray));

                view50.setBackground(getResources().getDrawable(R.drawable.circle_orange));

                break;
            case 5:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view11.setBackgroundColor(getResources().getColor(R.color.status_green));
                view20.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view21.setBackgroundColor(getResources().getColor(R.color.status_green));
                view30.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view31.setBackgroundColor(getResources().getColor(R.color.status_green));
                view40.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view41.setBackgroundColor(getResources().getColor(R.color.status_green));

                view50.setBackground(getResources().getDrawable(R.drawable.circle_blue));


                break;

            case 6:

                view10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view11.setBackgroundColor(getResources().getColor(R.color.status_green));
                view20.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view21.setBackgroundColor(getResources().getColor(R.color.status_green));
                view30.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view31.setBackgroundColor(getResources().getColor(R.color.status_green));
                view40.setBackground(getResources().getDrawable(R.drawable.circle_green));
                view41.setBackgroundColor(getResources().getColor(R.color.status_green));

                view50.setBackground(getResources().getDrawable(R.drawable.circle_green));

                break;

        }

    }

}