package com.sketch.smartcan.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.NetworkCall.AppConfig;
import com.sketch.smartcan.R;
import com.sketch.smartcan.Util.GlobalClass;
import com.sketch.smartcan.Util.Shared_Preference;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    String TAG="Register";
    ImageView img_back,img_home;
    GlobalClass globalClass;
    RelativeLayout rl_login;
    Shared_Preference prefrence;
    ProgressDialog pd;
    TextView tv_header,tv_register,tv_login;
    EditText edt_name,edt_email,edt_phone,edt_agent_id;
    RelativeLayout rl_register;
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
        globalClass = (GlobalClass) getApplicationContext();
        prefrence = new Shared_Preference(RegisterActivity.this);
        prefrence.loadPrefrence();
        pd = new ProgressDialog(RegisterActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(getResources().getString(R.string.loading));
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
        rl_register=findViewById(R.id.rl_register);
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


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_name.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                String phone = edt_phone.getText().toString().trim();
                String agent_id = edt_agent_id.getText().toString().trim();
                if (globalClass.isNetworkAvailable()) {
                    if (!edt_name.getText().toString().isEmpty()) {
                        if (!edt_email.getText().toString().isEmpty()) {
                            if (isValidEmail(edt_email.getText().toString())) {
                                if (!edt_phone.getText().toString().isEmpty()) {
                                    if (!edt_agent_id.getText().toString().isEmpty()) {

                                                CheckRegister(username, email, phone, agent_id);

                                    } else {
                                        FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.agent_empty), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                                    }
                                } else {
                                    FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.phone_empty), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                                }
                            } else {
                                FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_email), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                            }
                        } else {
                            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.mail_empty), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                        }
                    } else {
                        FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.name_empty), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                    }
                }
            }

        });

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
    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void CheckRegister(final String username, final String user_email, final String phone_number, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pd.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                pd.dismiss();

                Gson gson = new Gson();

                try {


                    JsonObject jobj = gson.fromJson(response, JsonObject.class);
                    String success = jobj.get("status").toString().replaceAll("\"", "");
                    String message = jobj.get("message").toString().replaceAll("\"", "");
                    Log.d(TAG, "message: "+message);

                    if (success.equals("1")) {
                        JsonObject data = jobj.getAsJsonObject("data");
                        String user_id =data.get("id").toString().replaceAll("\"", "");
                        String email=data.get("email").toString().replaceAll("\"", "");
                        String username=data.get("username").toString().replaceAll("\"", "");
                        String phone=data.get("phone").toString().replaceAll("\"", "");
                        String device_type=data.get("device_type").toString().replaceAll("\"", "");
                        String device_id=data.get("device_id").toString().replaceAll("\"", "");
                        String fcm_token=data.get("fcm_token").toString().replaceAll("\"", "");

                        globalClass.setId(user_id);
                        globalClass.setEmail(email);
                        globalClass.setFname(username);
                        globalClass.setPhone_number(phone);
                        globalClass.setLogin_status(true);


                        FancyToast.makeText(getApplicationContext(), message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        pd.dismiss();

                    } else
                    {
                        FancyToast.makeText(getApplicationContext(), message, FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                    }

                    //  JsonObject obj3 = jobj1.get("profileDetails").getAsJsonObject();

                    Log.d(TAG, "Token \n" + message);


                } catch (Exception e) {

                    FancyToast.makeText(getApplicationContext(), "Data Connection", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "DATA NOT FOUND: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Registration Error", Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);
                params.put("email_id", user_email);
                params.put("device_type", "android");
             //   params.put("device_id", device_id);
                params.put("fcm_token", "1234456");

                //  params.put("user_zip", user_pin);
                params.put("phone", phone_number);


                Log.d(TAG, "Register: "+params);
                return params;
            }

        };

        // Adding request to request queue
        GlobalClass.getInstance().addToRequestQueue(strReq, tag_string_req);
        strReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 10, 1.0f));

    }

}
