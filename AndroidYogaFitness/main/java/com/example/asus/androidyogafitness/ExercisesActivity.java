package com.example.asus.androidyogafitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.androidyogafitness.Adapter.ExerciseAdapter;
import com.example.asus.androidyogafitness.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {

    RecyclerView recycler_exercises;
    ExerciseAdapter adapter;

    List<Exercise> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        initData();

        recycler_exercises = (RecyclerView)findViewById(R.id.list_exercises);
        recycler_exercises.setHasFixedSize(true);
        recycler_exercises.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ExerciseAdapter(this , exerciseList);
        recycler_exercises.setAdapter(adapter);
    }

    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose , "Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose , "Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog , "Downward Facing Dog"));
        exerciseList.add(new Exercise(R.drawable.boat_pose , "Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon , "Half Pigeon"));
        exerciseList.add(new Exercise(R.drawable.low_lunge , "Low Lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow , "Upward Bow"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge , "Crescent Lunge"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose , "Warrior Pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose , "Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2 , "Warrior Pose 2"));
    }
}
