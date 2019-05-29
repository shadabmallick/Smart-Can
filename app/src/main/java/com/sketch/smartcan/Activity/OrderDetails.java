package com.sketch.smartcan.Activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sketch.smartcan.AdapterClass.NotificationListAdapter;
import com.sketch.smartcan.AdapterClass.OrderDetailsAdapter;
import com.sketch.smartcan.R;

import java.util.ArrayList;
import java.util.Locale;

public class OrderDetails extends AppCompatActivity {
    RecyclerView recycle_order_details;
    TextView toolbar_title,sr_no,desc,dismiss;
    ImageView iv_back;
    Typeface typeface_bold,typeface_light,typeface_regular,typeface_medium;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        recycle_order_details = findViewById(R.id.recycle_order_details);
        toolbar_title = findViewById(R.id.toolbar_title);
        iv_back = findViewById(R.id.iv_back);
        sr_no = findViewById(R.id.sr_no);
        desc = findViewById(R.id.desc);
        dismiss = findViewById(R.id.dismiss);
        AssetManager am = getApplicationContext().getAssets();


        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
        recycle_order_details.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        toolbar_title.setText("Order Details");
        toolbar_title.setTypeface(typeface_bold);
        sr_no.setTypeface(typeface_bold);
        desc.setTypeface(typeface_bold);
        dismiss.setTypeface(typeface_bold);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");

        OrderDetailsAdapter notificationListAdapter
                = new OrderDetailsAdapter(getApplicationContext(), list);
        recycle_order_details.setAdapter(notificationListAdapter);
    }
}
