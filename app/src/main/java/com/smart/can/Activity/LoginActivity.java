package com.smart.can.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.smart.can.MainActivity;
import com.smart.can.NetworkCall.AppConfig;
import com.smart.can.R;
import com.smart.can.Util.Constants;
import com.smart.can.Util.GlobalClass;
import com.smart.can.Util.Shared_Preference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout rl_forget_pass,rl_register_user,rl_login;
    TextView tv_register,tv_title,tv_login,tv_forget,tv_text_sign;
    ImageView img_back, img_home, iv_eye;
    EditText edt_user_id,edt_pass;
    Typeface typeface,typeface_bold,typeface_medium,typeface_light,typeface_regular;

    ProgressDialog progressDialog;
    GlobalClass globalClass;
    Shared_Preference preference;

    boolean password_visible = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

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
        iv_eye=findViewById(R.id.iv_eye);
        edt_pass=findViewById(R.id.edt_pass);


        tv_title.setTypeface(typeface);


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.loading));

        globalClass = (GlobalClass) getApplicationContext();
        preference = new Shared_Preference(this);


        buttonOnClick();













        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();


                        Log.d("TAG", "token = "+token);

                    }
                });

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




        edt_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        iv_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!password_visible){

                    edt_pass.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_eye.setImageResource(R.mipmap.eye);

                    password_visible = true;

                }else {

                    edt_pass.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_eye.setImageResource(R.mipmap.invisible);

                    password_visible = false;

                }

                edt_pass.setSelection(edt_pass.length());

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


                if (!emailValidate(edt_user_id.getText().toString())){
                    FancyToast.makeText(getApplicationContext(), "Enter valid email",
                            FancyToast.LENGTH_LONG, FancyToast.WARNING, false)
                            .show();
                    return;
                }

                if (edt_user_id.getText().toString().trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Enter user id",
                            FancyToast.LENGTH_LONG, FancyToast.WARNING, false)
                            .show();

                    return;
                }

                if (edt_pass.getText().toString().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Enter password",
                            FancyToast.LENGTH_LONG, FancyToast.WARNING, false)
                            .show();

                    return;
                }


                login(edt_user_id.getText().toString(), edt_pass.getText().toString());


            }
        });



    }




    private void login(final String user_email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        final String device_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(Constants.TAG, "Login Response: " + response.toString());

                if (response != null){
                    progressDialog.dismiss();
                    try {

                        JSONObject main_object = new JSONObject(response);


                        int status = main_object.optInt("status");
                        String message = main_object.optString("message");

                        if (status == 1){

                             FancyToast.makeText(getApplicationContext(), message,
                                     FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false)
                                     .show();

                            JSONObject data = main_object.getJSONObject("data");


                            String id = data.optString("id");
                            String name = data.optString("name");
                            String email = data.optString("email");
                            String profile_pic = data.optString("profile_pic");
                            String is_login = data.optString("is_login");
                            String device_type = data.optString("device_type");
                            String device_id = data.optString("device_id");


                            globalClass.setId(id);
                            globalClass.setEmail(email);
                            globalClass.setName(name);
                            globalClass.setProfil_pic(profile_pic);

                            globalClass.setLogin_status(true);


                            preference.savePrefrence();

                            Intent intent = new Intent(LoginActivity.this, Container.class);
                            startActivity(intent);


                        }else {

                             FancyToast.makeText(getApplicationContext(), message,
                                     FancyToast.LENGTH_LONG, FancyToast.ERROR, false)
                                     .show();


                        }


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

                params.put("password", password);
                params.put("email", user_email);
                params.put("device_type", "android");
                params.put("device_id", device_id);
                params.put("fcm_token", "");

                Log.d(Constants.TAG, "login param: "+params);
                return params;
            }

        };

        // Adding request to request queue
        GlobalClass.getInstance().addToRequestQueue(strReq, tag_string_req);
        strReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 10, 1.0f));

    }
}
