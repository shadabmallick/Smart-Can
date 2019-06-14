package com.smart.can.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.smart.can.AdapterClass.NewsAdapter;
import com.smart.can.DataModel.NewsData;
import com.smart.can.MainActivity;
import com.smart.can.NetworkCall.AppConfig;
import com.smart.can.R;
import com.smart.can.Util.Constants;
import com.smart.can.Util.GlobalClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {

     String TAG="News";

    RecyclerView recyclerView;
    LinearLayout llenquiry,llinfo,llblog;

    ProgressDialog progressDialog;

    Typeface typeface_bold,typeface_medium,typeface_light,typeface_regular;

    TextView title,tv_new,tv_blog,tv_info,tv_enq;

    ImageView enquiry,img_home;

    ArrayList<NewsData> newsDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.loading));


        InitFont();
        initViews();
        SetFont();



    }
    private void initViews(){
        enquiry=findViewById(R.id.enquiry);
        img_home=findViewById(R.id.img_home);
        llblog=findViewById(R.id.llblog);
        llenquiry=findViewById(R.id.llenquiry);
        llinfo=findViewById(R.id.llinfo);
        title=findViewById(R.id.news_title);
        tv_new=findViewById(R.id.tv_enq_new);
        tv_enq=findViewById(R.id.tv_enq_enquiry);
        tv_info=findViewById(R.id.tv_enq_info);
        tv_blog=findViewById(R.id.tv_enq_blog);

        recyclerView = findViewById(R.id.recyler_view_news);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        tv_new=findViewById(R.id.tv_enq_new);
        tv_enq=findViewById(R.id.tv_enq_enquiry);
        tv_info=findViewById(R.id.tv_enq_info);
        tv_blog=findViewById(R.id.tv_enq_blog);



        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EnquiryActivity.class));
            }
        });
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        llinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityInfographic.class));

            }
        });
        llenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnquiryActivity.class));

            }
        });
        llblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityBlog.class));

            }
        });



        getNews();

    }
    public  void InitFont(){
        AssetManager am = getApplicationContext().getAssets();

        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));

    }
    public void SetFont(){
        title.setTypeface(typeface_bold);
        tv_blog.setTypeface(typeface_regular);
        tv_enq.setTypeface(typeface_regular);
        tv_info.setTypeface(typeface_regular);
        tv_new.setTypeface(typeface_regular);

    }


    private void getNews() {

        newsDataArrayList = new ArrayList<>();

        String tag_string_req = "req_login";

        final String device_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.NEWS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(Constants.TAG, "Login Response: " + response.toString());

                if (response != null){
                    progressDialog.dismiss();
                    try {

                        JSONObject main_object = new JSONObject(response);


                        int status = main_object.optInt("status");

                        if (status == 1){

                            JSONArray message = main_object.getJSONArray("message");

                            for (int i = 0; i < message.length(); i++){

                                JSONObject object = message.getJSONObject(i);

                                NewsData newsData = new NewsData();
                                newsData.setHeading(object.optString(""));
                                newsData.setContent(object.optString(""));
                                newsData.setShort_content(object.optString(""));
                                newsData.setFile_name(object.optString(""));
                                newsData.setDate(object.optString(""));

                                newsDataArrayList.add(newsData);

                            }


                        }


                        NewsAdapter newsAdapter =
                                new NewsAdapter(NewsActivity.this, newsDataArrayList);
                        recyclerView.setAdapter(newsAdapter);


                    } catch (Exception e) {

                        FancyToast.makeText(getApplicationContext(), "Connection error",
                                FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                        e.printStackTrace();

                    }

                }

            }
        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                Log.e(Constants.TAG, "DATA NOT FOUND: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();

                params.put("device_type", "android");
                params.put("device_id", device_id);

                Log.d(Constants.TAG, "login param: "+params);
                return params;
            }

        };

        // Adding request to request queue
        GlobalClass.getInstance().addToRequestQueue(strReq, tag_string_req);
        strReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000,
                10, 1.0f));

    }
}