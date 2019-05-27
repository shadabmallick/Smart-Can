package com.sketch.smartcan.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sketch.smartcan.AdapterClass.DrawerListAdapter;
import com.sketch.smartcan.DataModel.DrawerItem;
import com.sketch.smartcan.Fragments.FragOrderHistory;
import com.sketch.smartcan.Fragments.HomePage;
import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;
import com.sketch.smartcan.Util.GlobalClass;
import com.sketch.smartcan.Util.PrefManager;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class Container extends AppCompatActivity implements DrawerListAdapter.onItemClickListner {

    DrawerLayout drawer;
    RecyclerView nav_drawer_recycler_view;
    TextView toolbar_title;

    PrefManager prefManager;
    GlobalClass globalClass;


    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;
    private FragmentManager mFragmentManager;
    ArrayList<DrawerItem> drawerItemArrayList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        initViews();



    }


    private void initViews(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Home");
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Home");



        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        nav_drawer_recycler_view = findViewById(R.id.nav_drawer_recycler_view);
        nav_drawer_recycler_view.setLayoutManager(new LinearLayoutManager(this));


        prefManager = new PrefManager(this);
        globalClass = (GlobalClass) getApplicationContext();

        mFragmentManager = getSupportFragmentManager();

        transactFragment(new HomePage());

        initNavigationItems();
        footerItemClick();


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
        drawerItem.setTitle("Profile");
        drawerItemArrayList.add(drawerItem);


        drawerItem = new DrawerItem();

        drawerItem.setImgResID(R.mipmap.notification);
        drawerItem.setTitle("Notification");
        drawerItemArrayList.add(drawerItem);

        drawerItem = new DrawerItem();

        /*drawerItem.setImgResID(R.mipmap.cart);
        drawerItem.setTitle("Cart");
        drawerItemArrayList.add(drawerItem);


        drawerItem = new DrawerItem();*/

        drawerItem.setImgResID(R.mipmap.about);
        drawerItem.setTitle("About");
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



                break;

            case 3:



                break;

            case 4:



                break;

            case 5:

                dialogLogout();

                break;

            case 6:



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
    }


    public void footerItemClick(){

        LinearLayout llnews = findViewById(R.id.llnews);
        LinearLayout llinfo = findViewById(R.id.llinfo);
        LinearLayout llblog = findViewById(R.id.llblog);
        LinearLayout llenquiry = findViewById(R.id.llenquiry);

        llnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewsActivity.class));


            }
        });

        llinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityInfographic.class));

            }
        });

        llblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityBlog.class));


            }
        });

        llenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EnquiryActivity.class));

            }
        });

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

