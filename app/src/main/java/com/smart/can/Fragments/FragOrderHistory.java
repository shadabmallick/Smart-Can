package com.smart.can.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.smart.can.AdapterClass.AdapterOrderHistory;
import com.smart.can.R;

import java.util.ArrayList;

public class FragOrderHistory extends Fragment {

    RecyclerView recylerview_history;


    Button btn_track_order, btn_complain;
    RelativeLayout rel_track_order, rel_complain;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_history, container, false);;

        initViews(view);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }


    private void initViews(View view){

        recylerview_history = view.findViewById(R.id.recylerview_history);
     //   recylerview_complain = view.findViewById(R.id.recylerview_complain);

        recylerview_history.setLayoutManager(new LinearLayoutManager(getActivity()));
      //  recylerview_complain.setLayoutManager(new LinearLayoutManager(getActivity()));





      //  buttonClick();

        setRecylerview_track_order();
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

        AdapterOrderHistory listAdapter = new AdapterOrderHistory(getActivity(), arrayList);
        recylerview_history.setAdapter(listAdapter);


    }



















}