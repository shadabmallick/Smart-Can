package com.smart.can.Activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smart.can.AdapterClass.ImageAdapter;
import com.smart.can.R;
import com.smart.can.Util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class OrderComplain extends Activity implements ImageAdapter.onItemClickListner {

    TextView toolbar_title, tv1, tv2, tv_btn1, tv_title1;
    EditText edt_order_id, edt_date, edt_complain;
    ImageView iv_back, iv_scan,img_date;
    Button btn_submit;
    RelativeLayout rel_add_image,rl_qr_code;
    RecyclerView recyler_images;
    private int mYear, mMonth, mDay, mHour, mMinute,mSecond;
    Calendar myCalendar = Calendar.getInstance();

    ArrayList<File> listSelectedImages;
    ImageAdapter imageAdapter;
    String exam_date;
    File p_image1;

    private int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 1888;
    public static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;


    Typeface typeface_bold, typeface_regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_complain);
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

        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));


        toolbar_title = findViewById(R.id.toolbar_title);
        img_date = findViewById(R.id.img_date);
        iv_back = findViewById(R.id.iv_back);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv_btn1 = findViewById(R.id.tv_btn1);
        tv_title1 = findViewById(R.id.tv_title1);

        edt_order_id = findViewById(R.id.edt_order_id);
        edt_date = findViewById(R.id.edt_date);
        edt_complain = findViewById(R.id.edt_complain);



        iv_scan = findViewById(R.id.iv_scan);

        btn_submit = findViewById(R.id.btn_submit);
        rel_add_image = findViewById(R.id.rel_add_image);
        rl_qr_code = findViewById(R.id.rl_qr_code);

        recyler_images = findViewById(R.id.recyler_images);
        recyler_images.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));



        toolbar_title.setText("Complain");

        listSelectedImages = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, listSelectedImages, this);
        recyler_images.setAdapter(imageAdapter);


        edt_complain.setTypeface(typeface_regular);
        btn_submit.setTypeface(typeface_bold);
        tv_btn1.setTypeface(typeface_bold);
        tv_title1.setTypeface(typeface_bold);
        tv1.setTypeface(typeface_regular);
        tv2.setTypeface(typeface_regular);
        edt_date.setTypeface(typeface_regular);
        edt_complain.setTypeface(typeface_regular);
        edt_order_id.setTypeface(typeface_regular);




        buttonOnclick();

    }
    public DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int selectedYear,
                                      int selectedMonth, int selectedDay) {
                    myCalendar.set(Calendar.YEAR, selectedYear);
                    myCalendar.set(Calendar.MONTH, selectedMonth);
                    myCalendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                    String myFormat = "MMM dd, yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    exam_date = sdf1.format(myCalendar.getTime());
                   // String date_to_show = sdf.format(myCalendar.getTime());
                    edt_date.setText(exam_date);

                }
            };

    public void buttonOnclick(){


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(OrderComplain.this, datePickerListener, mYear, mMonth, mDay).show();


            }


        });


        rel_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listSelectedImages.size() >= 2){

                    Toasty.info(OrderComplain.this,
                            "You have already selected 2 images.",
                            Toast.LENGTH_SHORT, true).show();

                }else {

                    if (checkPermission());

                }

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        rl_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(OrderComplain.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                integrator.setOrientationLocked(false);



            }
        });


    }



    private boolean checkPermission() {

        List<String> permissionsList = new ArrayList<String>();

        if (ContextCompat.checkSelfPermission(OrderComplain.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(OrderComplain.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(OrderComplain.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.CAMERA);
        }

        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions((Activity) OrderComplain.this, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);

            return false;
        } else {

            selectImage();

        }


        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                if (permissions.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                        (permissions.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                                grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

                    //list is still empty
                    selectImage();
                } else {
                    checkPermission();
                    // Permission Denied

                }
                break;
        }
    }


    public void selectImage() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(OrderComplain.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_picture_select, null);
        dialogBuilder.setView(dialogView);

        ImageView iv_gallery = dialogView.findViewById(R.id.iv_gallery);
        ImageView iv_camera = dialogView.findViewById(R.id.iv_camera);

        final AlertDialog alertDialog = dialogBuilder.create();

        iv_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                        PICK_IMAGE_REQUEST);

                alertDialog.dismiss();

            }
        });


        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        p_image1 = null;

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri uri = data.getData();
            p_image1 = new File(getRealPathFromURI(uri));

            Log.d(Constants.TAG , "PICK_IMAGE_REQUEST - "+uri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


            } catch (IOException e) {
                e.printStackTrace();
            }

            if (p_image1 != null){
                listSelectedImages.add(p_image1);
            }


        }else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Log.e("Scan", "Scanned");

          //  edt_order_id.setText(result.getContents());
           // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            Log.d(Constants.TAG , "CAMERA_REQUEST - "+data.getExtras().get("data"));

            File f = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }

            try {


                String path = Environment.getExternalStorageDirectory()+File.separator;

                f.delete();
                OutputStream outFile = null;
                File file = new File(path, System.currentTimeMillis() + ".jpg");
                try {

                    p_image1 = file;

                    outFile = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 80, outFile);
                    outFile.flush();
                    outFile.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (p_image1 != null){
                listSelectedImages.add(p_image1);
            }


        }
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

                edt_order_id.setText(result.getContents());
                Log.d(Constants.TAG, "onActivityResult: " +result);
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }

        imageAdapter.notifyDataSetChanged();
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result = "";
        try {
            Cursor cursor = getApplicationContext().getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) { // Source is Dropbox or other similar local file path
                result = contentURI.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx); // Exception raised HERE
                cursor.close(); }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void onItemClick(int position) {

        listSelectedImages.remove(position);
        imageAdapter.notifyDataSetChanged();
    }





}