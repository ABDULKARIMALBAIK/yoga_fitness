package com.example.asus.androidyogafitness;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.androidyogafitness.Database.YogaDB;
import com.example.asus.androidyogafitness.Utils.Common;

public class ViewExerciseActiviy extends AppCompatActivity {

    int image_id;
    String name;

    Button btnStart;
    TextView timer , title;
    ImageView detail_image;

    YogaDB yogaDB;

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_activiy);

        yogaDB = new YogaDB(this);

        if (getIntent().getExtras() != null){

            image_id = getIntent().getIntExtra("image_id" , -1);
            name = getIntent().getStringExtra("name");
        }

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (ImageView)findViewById(R.id.detail_image);
        btnStart = (Button)findViewById(R.id.btnStart);

        timer.setText("");
        title.setText(name);
        detail_image.setImageResource(image_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isRunning){

                    btnStart.setText("DONE");

                    int timeLimit = 1;
                    if (yogaDB.getSettingMode() == 1)
                        timeLimit = Common.TIME_LIMIT_EASY;
                    else if (yogaDB.getSettingMode() == 2)
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    else if (yogaDB.getSettingMode() == 3)
                        timeLimit = Common.TIME_LIMIT_HARD;

                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            timer.setText("" + millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {

                            //you can put Ads here
                            Toast.makeText(ViewExerciseActiviy.this, "Finish !!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
               else {

                    //you can put Ads here
                    Toast.makeText(ViewExerciseActiviy.this, "Timer is running", Toast.LENGTH_SHORT).show();
                }

                isRunning = !isRunning;
            }
        });


    }
}
