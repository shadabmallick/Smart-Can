package com.sketch.smartcan.AdapterClass;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sketch.smartcan.AndroidVersion;
import com.sketch.smartcan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AdapterInfographic extends RecyclerView.Adapter<AdapterInfographic.ViewHolder> {
    private ArrayList<AndroidVersion> android;
    private Context context;

    public AdapterInfographic(Context context,ArrayList<AndroidVersion> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public AdapterInfographic.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterInfographic.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).getAndroid_version_name());
        Picasso.with(context).load(android.get(i).getAndroid_image_url()).fit().centerCrop()
                .placeholder(R.mipmap.infographic_bg)

                .into(viewHolder.img_android);        Log.d(TAG, "onBindViewHolder: "+android.get(i).getAndroid_image_url());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
        }
    }

}