package com.sketch.smartcan.AdapterClass;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sketch.smartcan.R;

import java.util.ArrayList;
import java.util.Locale;

public class OrderDetailsAdapter extends
        RecyclerView.Adapter<OrderDetailsAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;
    private Typeface typeface;
    Typeface typeface_bold,typeface_light,typeface_regular,typeface_medium;



    OrderDetailsAdapter.onItemClickListner mListner;
    public interface onItemClickListner{
        void onItemClick(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView action, desc,dismiss;
        //  ImageView iv_track, iv_complain;
        OrderDetailsAdapter.onItemClickListner listner;

        public ItemViewHolder(View itemView, OrderDetailsAdapter.onItemClickListner listner) {
            super(itemView);
            action = itemView.findViewById(R.id.action);
            desc = itemView.findViewById(R.id.desc);
            dismiss = itemView.findViewById(R.id.dismiss);


            this.listner = listner;
        }
    }


    public OrderDetailsAdapter(Context context, ArrayList<String> itemList){

        this.context = context;
        this.arrayList=itemList;

        AssetManager am = context.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Medium.ttf"));
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
    public OrderDetailsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_order_details, parent,false);
        OrderDetailsAdapter.ItemViewHolder dvh = new OrderDetailsAdapter.ItemViewHolder(v, mListner);
        return dvh;
    }


    @Override
    public void onBindViewHolder(OrderDetailsAdapter.ItemViewHolder holder, final int position) {

        holder.action.setTypeface(typeface_regular);
        holder.desc.setTypeface(typeface_regular);
        holder.dismiss.setTypeface(typeface_regular);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
