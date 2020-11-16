package com.jaehyun.jhpaint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ColorRecyclerViewAdapter extends RecyclerView.Adapter<ColorRecyclerViewAdapter.ColorViewHolder> {
    private List<Integer> list = null;
    private Context mContext;
    private OnPickColorListener pickListener;

    interface OnPickColorListener{
        void onPick (View view);
    }
    ColorRecyclerViewAdapter(Context context){
        super();
        this.mContext = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color_ball,parent,false);

        return new ColorViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {

        final GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(mContext, R.drawable.color_circle);
        if (drawable != null) {
            drawable.setColor(list.get(position));
        }
        holder.imageView.setImageDrawable(drawable);
        holder.color = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }


    public void setOnPickListener(ColorRecyclerViewAdapter.OnPickColorListener listener){
        this.pickListener = listener;
    }

    class ColorViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        int color;

        ColorViewHolder(@NonNull final View itemView) {
            super(itemView);
            color = -1;
            imageView = itemView.findViewById(R.id.imageViewCircle);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pickListener != null)
                        pickListener.onPick(v);
                    Log.d("test","선택 : "+ color);
                }
            });
        }
    }
}


