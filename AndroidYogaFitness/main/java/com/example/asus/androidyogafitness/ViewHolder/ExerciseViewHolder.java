package com.example.asus.androidyogafitness.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.androidyogafitness.Interface.IItemClickListener;
import com.example.asus.androidyogafitness.R;
import com.github.abdularis.civ.CircleImageView;


public class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView text;

    private IItemClickListener iItemClickListener;

    public ExerciseViewHolder(View itemView) {
        super(itemView);

        image = (ImageView)itemView.findViewById(R.id.ex_image);
        text = (TextView)itemView.findViewById(R.id.ex_text);

        itemView.setOnClickListener(this);
    }

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    @Override
    public void onClick(View v) {

        iItemClickListener.onClick(v , getAdapterPosition());
    }
}
