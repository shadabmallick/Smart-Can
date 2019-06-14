package com.smart.can.AdapterClass;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.can.R;

import java.util.ArrayList;
import java.util.Locale;

public class NotificationListAdapter extends
        RecyclerView.Adapter<NotificationListAdapter.DrawerItemViewHolder> {

    private Context context;
    private ArrayList<String> stringArrayList;
    private Typeface typeface_bold, typeface_regular;



    public class DrawerItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_order_id, tv_msg;


        public DrawerItemViewHolder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_order_id = itemView.findViewById(R.id.tv_order_id);
            tv_msg = itemView.findViewById(R.id.tv_msg);

        }
    }


    public NotificationListAdapter(Context context, ArrayList<String> itemList){

        this.context = context;
        this.stringArrayList=itemList;

        AssetManager am = context.getAssets();
        typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));

        typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Regular.ttf"));

    }

    @Override
    public DrawerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list_item, parent,false);
        DrawerItemViewHolder dvh = new DrawerItemViewHolder(v);
        return dvh;
    }


    @Override
    public void onBindViewHolder(DrawerItemViewHolder holder, final int position) {


        holder.tv_date.setTypeface(typeface_bold);
        holder.tv_order_id.setTypeface(typeface_bold);
        holder.tv_msg.setTypeface(typeface_regular);



    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

}
