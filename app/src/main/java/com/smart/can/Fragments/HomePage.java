package com.smart.can.Fragments;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smart.can.Activity.ActivityBlog;
import com.smart.can.Activity.ActivityInfographic;
import com.smart.can.Activity.EnquiryActivity;
import com.smart.can.Activity.NewsActivity;
import com.smart.can.Activity.OrderComplain;
import com.smart.can.AdapterClass.TrackComplainListAdapter;
import com.smart.can.AdapterClass.TrackOrderListAdapter;
import com.smart.can.R;

import java.util.ArrayList;
import java.util.Locale;


public class HomePage extends Fragment {

    RecyclerView recylerview_track_order;
    RecyclerView recylerview_complain;
    ImageView iv_add_complain;

    Button btn_track_order, btn_complain;
    RelativeLayout rel_track_order, rel_complain;

    Typeface typeface_bold, typeface_regular;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.frag_home, container, false);;

        initViews(view);

        footerItemClick(view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }


    private void initViews(View view){

        recylerview_track_order = view.findViewById(R.id.recylerview_track_order);
        recylerview_complain = view.findViewById(R.id.recylerview_complain);

        recylerview_track_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerview_complain.setLayoutManager(new LinearLayoutManager(getActivity()));

        iv_add_complain = getActivity().findViewById(R.id.iv_add_complain);

        btn_track_order = view.findViewById(R.id.btn_track_order);
        btn_complain = view.findViewById(R.id.btn_complain);
        rel_track_order = view.findViewById(R.id.rel_track_order);
        rel_complain = view.findViewById(R.id.rel_complain);


        AssetManager am = getActivity().getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));

        btn_track_order.setTypeface(typeface_bold);
        btn_complain.setTypeface(typeface_bold);





        buttonClick();

        setRecylerview_track_order();
    }



    public void buttonClick(){

        btn_track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_track_order.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                btn_track_order.setTextColor(getResources().getColor(R.color.white));

                btn_complain.setBackgroundColor(getResources().getColor(R.color.white));
                btn_complain.setTextColor(getResources().getColor(R.color.black));

                rel_track_order.setVisibility(View.VISIBLE);
                rel_complain.setVisibility(View.GONE);

                iv_add_complain.setVisibility(View.GONE);

            }
        });

        btn_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_track_order.setBackgroundColor(getResources().getColor(R.color.white));
                btn_track_order.setTextColor(getResources().getColor(R.color.black));

                btn_complain.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                btn_complain.setTextColor(getResources().getColor(R.color.white));

                rel_track_order.setVisibility(View.GONE);
                rel_complain.setVisibility(View.VISIBLE);

                setRecylerview_Complain();

                iv_add_complain.setVisibility(View.VISIBLE);

            }
        });


        iv_add_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), OrderComplain.class);
                startActivity(intent);

            }
        });

    }


    public void footerItemClick(View view){

        LinearLayout llnews = view.findViewById(R.id.llnews);
        LinearLayout llinfo = view.findViewById(R.id.llinfo);
        LinearLayout llblog = view.findViewById(R.id.llblog);
        LinearLayout llenquiry = view.findViewById(R.id.llenquiry);

        llnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewsActivity.class));


            }
        });

        llinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityInfographic.class));

            }
        });

        llblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityBlog.class));


            }
        });

        llenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EnquiryActivity.class));

            }
        });

    }



    public void setRecylerview_track_order(){

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");

        TrackOrderListAdapter listAdapter = new TrackOrderListAdapter(getActivity(), arrayList);
        recylerview_track_order.setAdapter(listAdapter);


    }

    public void setRecylerview_Complain(){

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");

        TrackComplainListAdapter listAdapter = new TrackComplainListAdapter(getActivity(), arrayList);
        recylerview_complain.setAdapter(listAdapter);


    }


















}
