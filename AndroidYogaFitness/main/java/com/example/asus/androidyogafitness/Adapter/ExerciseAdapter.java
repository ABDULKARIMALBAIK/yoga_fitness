package com.example.asus.androidyogafitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.androidyogafitness.Interface.IItemClickListener;
import com.example.asus.androidyogafitness.Model.Exercise;
import com.example.asus.androidyogafitness.R;
import com.example.asus.androidyogafitness.ViewExerciseActiviy;
import com.example.asus.androidyogafitness.ViewHolder.ExerciseViewHolder;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {

    Context context;
    List<Exercise> exerciseList;

    public ExerciseAdapter(Context context, List<Exercise> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_exercise , parent , false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

        holder.text.setText(exerciseList.get(position).getName());
        holder.image.setImageResource(exerciseList.get(position).getImage_id());

        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Start Activiy
                Intent viewExerciseIntent = new Intent(context , ViewExerciseActiviy.class);
                viewExerciseIntent.putExtra("image_id" , exerciseList.get(position).getImage_id());
                viewExerciseIntent.putExtra("name" , exerciseList.get(position).getName());
                context.startActivity(viewExerciseIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
