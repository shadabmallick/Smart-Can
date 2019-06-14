package com.smart.can.AdapterClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smart.can.R;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<File> list;
    private Context context;


    onItemClickListner mListner;
    public interface onItemClickListner{
        void onItemClick(int position);
    }

    public ImageAdapter(Context context, ArrayList<File> list, onItemClickListner listner) {
        this.list = list;
        this.context = context;
        this.mListner = listner;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.image_item, viewGroup, false);
        return new ViewHolder(view, mListner);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder viewHolder, final int position) {


        String filePath = list.get(position).getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        viewHolder.iv_img.setImageBitmap(bitmap);


        viewHolder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListner.onItemClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_img, iv_remove;
        onItemClickListner listner;
        public ViewHolder(View view, onItemClickListner listner) {
            super(view);
            iv_img = view.findViewById(R.id.iv_img);
            iv_remove = view.findViewById(R.id.iv_remove);
            this.listner = listner;

        }
    }

}