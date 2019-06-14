package com.smart.can.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.smart.can.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends Fragment {

    CardView card_view;
    ImageView iv_edit_mode;
    EditText edt_phone, edt_email, edt_location, edt_company, edt_name;
    CircleImageView profile_image;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.frag_profile, container, false);;

        initViews(view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }


    private void initViews(View view){

        card_view = view.findViewById(R.id.card_view);
        card_view.getBackground().setAlpha(150);

        iv_edit_mode = view.findViewById(R.id.iv_edit_mode);
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_email = view.findViewById(R.id.edt_email);
        edt_location = view.findViewById(R.id.edt_location);
        edt_company = view.findViewById(R.id.edt_company);
        edt_name = view.findViewById(R.id.edt_name);
        profile_image = view.findViewById(R.id.profile_image);



    }





















}
