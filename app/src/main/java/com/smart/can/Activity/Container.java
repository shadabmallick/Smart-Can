package com.smart.can.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smart.can.AdapterClass.DrawerListAdapter;
import com.smart.can.DataModel.DrawerItem;
import com.smart.can.Fragments.AboutUs;
import com.smart.can.Fragments.FragOrderHistory;
import com.smart.can.Fragments.HomePage;

import com.smart.can.Fragments.Notification;
import com.smart.can.MainActivity;

import com.smart.can.Fragments.Profile;

import com.smart.can.R;
import com.smart.can.Util.GlobalClass;
import com.smart.can.Util.Shared_Preference;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class Container extends AppCompatActivity
        implements DrawerListAdapter.onItemClickListner {

    DrawerLayout drawer;
    RecyclerView nav_drawer_recycler_view;
    TextView toolbar_title, tv_name;
    ImageView iv_add_complain;
    CircleImageView iv_user;
    RelativeLayout rel_profile;


    Shared_Preference prefManager;
    GlobalClass globalClass;

    Typeface typeface_bold, typeface_regular;


    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;
    private FragmentManager mFragmentManager;
    ArrayList<DrawerItem> drawerItemArrayList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorBlue));
        }

        initViews();



    }


    private void initViews(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
        }

        AssetManager am = getApplicationContext().getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));


        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Home");
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface_bold);
        toolbar_title.setText("Home");


        iv_add_complain = findViewById(R.id.iv_add_complain);
        tv_name = findViewById(R.id.tv_name);
        iv_user = findViewById(R.id.iv_user);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        nav_drawer_recycler_view = findViewById(R.id.nav_drawer_recycler_view);
        nav_drawer_recycler_view.setLayoutManager(new LinearLayoutManager(this));


        prefManager = new Shared_Preference(this);
        globalClass = (GlobalClass) getApplicationContext();
        prefManager.loadPrefrence();

        tv_name.setText(globalClass.getName());
        Glide.with(this)
                .load(globalClass.getProfil_pic())
                .centerCrop()
                .placeholder(R.mipmap.avatar)
                .into(iv_user);



        mFragmentManager = getSupportFragmentManager();

        transactFragment(new HomePage());

        initNavigationItems();


        rel_profile = findViewById(R.id.rel_profile);
        rel_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transactFragment(new Profile());

                toolbar_title.setText("My Profile");
            }
        });


    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;

            Toasty.info(Container.this, "Please click BACK again to exit",
                    Toast.LENGTH_SHORT, true).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }


    @Override
    protected void onResume() {


        super.onResume();
    }


    public void initNavigationItems(){

        drawerItemArrayList = new ArrayList<>();

        DrawerItem drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.home_orange);
        drawerItem.setTitle("Home");
        drawerItemArrayList.add(drawerItem);

        drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.order_history);
        drawerItem.setTitle("Order History");
        drawerItemArrayList.add(drawerItem);


        drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.user);
        drawerItem.setTitle("My Profile");
        drawerItemArrayList.add(drawerItem);


        drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.notification);
        drawerItem.setTitle("Notification");
        drawerItemArrayList.add(drawerItem);




        drawerItem = new DrawerItem();


        drawerItem.setImgResID(R.mipmap.about);
        drawerItem.setTitle("About Us");
        drawerItemArrayList.add(drawerItem);


        drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.logout);
        drawerItem.setTitle("Logout");
        drawerItemArrayList.add(drawerItem);


        DrawerListAdapter drawerListAdapter =
                new DrawerListAdapter(Container.this, drawerItemArrayList, this);
        nav_drawer_recycler_view.setAdapter(drawerListAdapter);

    }


    @Override
    public void onItemClick(int position) {

        Fragment fragment = null;

        switch (position){

            case 0:

                fragment = new HomePage();

                transactFragment(fragment);

                break;


            case 1:

                  fragment =new FragOrderHistory();
                  transactFragment(fragment);

                break;

            case 2:

                fragment = new Profile();

                transactFragment(fragment);

                break;

            case 3:

                fragment = new Notification();

                transactFragment(fragment);


                break;

            case 4:


                fragment =new AboutUs();
                transactFragment(fragment);

                break;

            case 5:

                dialogLogout();

                break;



        }


        toolbar_title.setText(drawerItemArrayList.get(position).getTitle());

    }


    private void transactFragment(final Fragment fragment){

        if (fragment != null) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.container, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();

                }
            }, 300);

        }

        drawer.closeDrawer(GravityCompat.START);

        iv_add_complain.setVisibility(View.GONE);
    }





    public void dialogLogout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Container.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("LOGOUT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                  startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                });

        builder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }






}

