package com.example.asus.androidyogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnExercise , btnSettings , btnCalendar;
    ImageView btnTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExercise = (Button)findViewById(R.id.btnExercises);
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent exercisesIntent = new Intent(getApplicationContext() , ExercisesActivity.class);
                startActivity(exercisesIntent);
            }
        });

        btnSettings = (Button)findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent settingsIntent = new Intent(getApplicationContext() , SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        btnTraining = (ImageView)findViewById(R.id.btnTraining);
        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent trainingIntent = new Intent(getApplicationContext() , DailyTrainingActivity.class);
                startActivity(trainingIntent);
            }
        });

        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent calendarIntent = new Intent(getApplicationContext() , CalendarActivity.class);
                startActivity(calendarIntent);
            }
        });
    }
}
