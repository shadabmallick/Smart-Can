package com.sketch.smartcan.AdapterClass;

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

import com.sketch.smartcan.Activity.ComplainDetails;
import com.sketch.smartcan.Activity.OrderTrack;
import com.sketch.smartcan.R;

import java.util.ArrayList;
import java.util.Locale;

public class TrackComplainListAdapter extends
        RecyclerView.Adapter<TrackComplainListAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;
    private Typeface typeface;


    TrackComplainListAdapter.onItemClickListner mListner;
    public interface onItemClickListner{
        void onItemClick(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv1, tv_date, tv2, tv_order_id, tv3, tv_do_id;
      //  ImageView iv_track, iv_complain;
        TrackComplainListAdapter.onItemClickListner listner;

        public ItemViewHolder(View itemView, TrackComplainListAdapter.onItemClickListner listner) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv2 = itemView.findViewById(R.id.tv2);
            tv_order_id = itemView.findViewById(R.id.tv_order_id);
            tv3 = itemView.findViewById(R.id.tv3);
            tv_do_id = itemView.findViewById(R.id.tv_do_id);

            this.listner = listner;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sinlgeComplain=new Intent(context, ComplainDetails.class);
                    context.startActivity(sinlgeComplain);

                }
            });
        }
    }


    public TrackComplainListAdapter(Context context, ArrayList<String> itemList){

        this.context = context;
        this.arrayList=itemList;

        AssetManager am = context.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));

    }

    @Override
    public TrackComplainListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_compain_list, parent,false);
        TrackComplainListAdapter.ItemViewHolder dvh = new TrackComplainListAdapter.ItemViewHolder(v, mListner);
        return dvh;
    }


    @Override
    public void onBindViewHolder(TrackComplainListAdapter.ItemViewHolder holder, final int position) {

        holder.tv_date.setTypeface(typeface);
        holder.tv_do_id.setTypeface(typeface);
        holder.tv_order_id.setTypeface(typeface);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
