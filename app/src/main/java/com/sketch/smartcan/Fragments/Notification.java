package com.sketch.smartcan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sketch.smartcan.AdapterClass.NotificationListAdapter;
import com.sketch.smartcan.R;

import java.util.ArrayList;

public class Notification extends Fragment {

    RecyclerView recylerview_noti;


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_notification, container, false);;

        initViews(view);


        return view;
    }
    private void initViews(View view){

        recylerview_noti = view.findViewById(R.id.recylerview_noti);
        recylerview_noti.setLayoutManager(new LinearLayoutManager(getActivity()));


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

        NotificationListAdapter notificationListAdapter
                = new NotificationListAdapter(getActivity(), list);
        recylerview_noti.setAdapter(notificationListAdapter);

    }

}
