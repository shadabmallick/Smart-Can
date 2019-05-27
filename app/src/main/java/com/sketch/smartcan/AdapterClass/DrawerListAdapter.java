package com.sketch.smartcan.AdapterClass;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sketch.smartcan.DataModel.DrawerItem;
import com.sketch.smartcan.R;

import java.util.ArrayList;
import java.util.Locale;

public class DrawerListAdapter extends
        RecyclerView.Adapter<DrawerListAdapter.DrawerItemViewHolder> {

    private Context context;
    private ArrayList<DrawerItem> menuList;
    private Typeface typeface;


    onItemClickListner mListner;
    public interface onItemClickListner{
        void onItemClick(int position);
    }

    public class DrawerItemViewHolder extends RecyclerView.ViewHolder{
        TextView menu_item_title;
        ImageView menu_item_icon;
        RelativeLayout rel_main;
        onItemClickListner listner;

        public DrawerItemViewHolder(View itemView, onItemClickListner listner) {
            super(itemView);
            menu_item_title = itemView.findViewById(R.id.menu_item_title);
            menu_item_icon = itemView.findViewById(R.id.menu_item_icon);
            rel_main = itemView.findViewById(R.id.rel_main);
            this.listner = listner;
        }
    }


    public DrawerListAdapter(Context context, ArrayList<DrawerItem> itemList,
                             onItemClickListner listner){

        this.context = context;
        this.menuList=itemList;
        this.mListner=listner;

        AssetManager am = context.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Raleway-Bold.ttf"));

    }

    @Override
    public DrawerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_item, parent,false);
        DrawerItemViewHolder dvh = new DrawerItemViewHolder(v, mListner);
        return dvh;
    }


    @Override
    public void onBindViewHolder(DrawerItemViewHolder holder, final int position) {

        DrawerItem currentItem = menuList.get(position);

        holder.menu_item_title.setText(currentItem.getTitle());
        holder.menu_item_title.setTextColor(Color.WHITE);
        holder.menu_item_title.setTypeface(typeface);


        holder.menu_item_icon.setImageResource(currentItem.getImgResID());

        holder.rel_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListner.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
