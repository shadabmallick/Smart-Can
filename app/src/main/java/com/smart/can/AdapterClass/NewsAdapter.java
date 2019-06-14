package com.smart.can.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.can.Activity.NewsSingle;
import com.smart.can.DataModel.NewsData;
import com.smart.can.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<NewsData> dataArrayList;
    private Context context;
    Typeface typeface_bold,typeface_medium,typeface_light,typeface_regular;


    public NewsAdapter(Context context, ArrayList<NewsData> dataArrayList) {
        this.dataArrayList = dataArrayList;
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
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_heading.setText(dataArrayList.get(i).getHeading());
        viewHolder.tv_brief.setText(dataArrayList.get(i).getShort_content());


        Picasso.with(context).load(dataArrayList.get(i).getFile_name()).fit().centerCrop()
                .placeholder(R.mipmap.news_bg)
                .into(viewHolder.img_android);


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
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_heading,tv_brief,tv_date;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_heading = (TextView)view.findViewById(R.id.tv_heading);
            tv_brief = (TextView)view.findViewById(R.id.tv_brief);
            tv_date = (TextView)view.findViewById(R.id.tv_date);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            tv_heading.setTypeface(typeface_bold);
            tv_brief.setTypeface(typeface_regular);
            tv_date.setTypeface(typeface_light);
        }
    }

}