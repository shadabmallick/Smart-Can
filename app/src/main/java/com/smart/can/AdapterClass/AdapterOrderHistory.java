package com.smart.can.AdapterClass;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.can.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterOrderHistory  extends
        RecyclerView.Adapter<AdapterOrderHistory.ItemViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;
    private Typeface typeface;


    AdapterOrderHistory.onItemClickListner mListner;
    public interface onItemClickListner{
        void onItemClick(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv1, tv_date, tv2, tv_order_id, tv3, tv_do_id;
        ImageView iv_track, iv_complain;
        AdapterOrderHistory.onItemClickListner listner;

        public ItemViewHolder(View itemView, AdapterOrderHistory.onItemClickListner listner) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv2 = itemView.findViewById(R.id.tv2);
            tv_order_id = itemView.findViewById(R.id.tv_order_id);
            tv3 = itemView.findViewById(R.id.tv3);
            tv_do_id = itemView.findViewById(R.id.tv_do_id);
            iv_track = itemView.findViewById(R.id.iv_track);
            iv_complain = itemView.findViewById(R.id.iv_complain);
            this.listner = listner;
        }
    }


    public AdapterOrderHistory(Context context, ArrayList<String> itemList){

        this.context = context;
        this.arrayList=itemList;

        AssetManager am = context.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

    }

    @Override
    public AdapterOrderHistory.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_history_order, parent,false);
        AdapterOrderHistory.ItemViewHolder dvh = new AdapterOrderHistory.ItemViewHolder(v, mListner);
        return dvh;
    }


    @Override
    public void onBindViewHolder(AdapterOrderHistory.ItemViewHolder holder, final int position) {

        holder.tv_date.setTypeface(typeface);
        holder.tv_do_id.setTypeface(typeface);
        holder.tv_order_id.setTypeface(typeface);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
