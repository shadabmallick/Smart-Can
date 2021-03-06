package com.smart.can.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.can.Activity.NewsSingle;
import com.smart.can.AndroidVersion;
import com.smart.can.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;
    private Context context;
    Typeface typeface_bold,typeface_medium,typeface_light,typeface_regular;


    public DataAdapter(Context context,ArrayList<AndroidVersion> android) {
        this.android = android;
        this.context = context;
        AssetManager am =context.getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));
        typeface_light = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Light.ttf"));
        typeface_medium = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).getAndroid_version_name());
        Picasso.with(context).load(android.get(i).getAndroid_image_url()).fit().centerCrop()
                .placeholder(R.mipmap.news_bg)

                .into(viewHolder.img_android);        Log.d(TAG, "onBindViewHolder: "+android.get(i).getAndroid_image_url());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blogSingle=new Intent(context, NewsSingle.class);
                blogSingle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(blogSingle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android,id_brief,id_date;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            id_brief = (TextView)view.findViewById(R.id.tv_brief);
           // id_date = (TextView)view.findViewById(R.id.date);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            tv_android.setTypeface(typeface_bold);
            id_brief.setTypeface(typeface_regular);
            id_date.setTypeface(typeface_light);
        }
    }

}