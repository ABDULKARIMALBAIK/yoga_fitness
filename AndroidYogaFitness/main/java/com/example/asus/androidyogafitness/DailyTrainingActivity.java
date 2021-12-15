package com.example.asus.androidyogafitness;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.androidyogafitness.Database.YogaDB;
import com.example.asus.androidyogafitness.Model.Exercise;
import com.example.asus.androidyogafitness.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class DailyTrainingActivity extends AppCompatActivity {

    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady , txtCountdown , txtTimer , ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    YogaDB yogaDB;

    int ex_id = 0 , limit_time = 1;
    List<Exercise> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();

        yogaDB = new YogaDB(this);

        btnStart = (Button)findViewById(R.id.btnStart);
        ex_image = (ImageView)findViewById(R.id.detail_image);
        ex_name = (TextView)findViewById(R.id.title);

        txtCountdown = (TextView)findViewById(R.id.txtCountdown);
        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtTimer = (TextView)findViewById(R.id.timer);

        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);
        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        //Set data
        progressBar.setMax(list.size());

        setExercisesInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnStart.getText().toString().toLowerCase().equals("start")){

                    showGetReady();
                    btnStart.setText("Done");
                }
                else if (btnStart.getText().toString().toLowerCase().equals("done")){

                    if (yogaDB.getSettingMode() == 1)
                        exerciseEasyModeCountDown.cancel();
                    else if (yogaDB.getSettingMode() == 2)
                        exerciseMediumModeCountDown.cancel();
                    else if (yogaDB.getSettingMode() == 3)
                        exerciseHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if (ex_id < list.size() -1){

                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else
                        showFinished();

                }
                else {

                    if (yogaDB.getSettingMode() == 1)
                        exerciseEasyModeCountDown.cancel();
                    else if (yogaDB.getSettingMode() == 2)
                        exerciseMediumModeCountDown.cancel();
                    else if (yogaDB.getSettingMode() == 3)
                        exerciseHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if (ex_id < list.size() -1)
                        setExercisesInformation(ex_id);
                    else
                        showFinished();

                }

            }
        });
    }

    CountDownTimer exerciseEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtTimer.setText("" + (millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {

            if (ex_id <list.size() -1){

                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExercisesInformation(ex_id);
                btnStart.setText("Start");
            }
            else {

                showFinished();
            }
        }
    };
    CountDownTimer exerciseMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtTimer.setText("" + (millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {

            if (ex_id <list.size() -1){

                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExercisesInformation(ex_id);
                btnStart.setText("Start");
            }
            else {

                showFinished();
            }
        }
    };
    CountDownTimer exerciseHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtTimer.setText("" + (millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {

            if (ex_id <list.size() -1){

                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExercisesInformation(ex_id);
                btnStart.setText("Start");
            }
            else {

                showFinished();
            }
        }
    };


    CountDownTimer restTimeCountDown = new CountDownTimer(10000 , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtCountdown.setText("" + (millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {

           setExercisesInformation(ex_id);
           showExercises();
        }
    };

    private void showGetReady() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);
        txtGetReady.setText("GET READY");

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                txtCountdown.setText("" + (millisUntilFinished - 1)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {

        if (ex_id < list.size()){

            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if (yogaDB.getSettingMode() == 1)
                exerciseEasyModeCountDown.start();
            else if (yogaDB.getSettingMode() == 2)
                exerciseMediumModeCountDown.start();
            else if (yogaDB.getSettingMode() == 3)
                exerciseHardModeCountDown.start();

            //Set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else
            showFinished();
    }

    private void setExercisesInformation(int id) {

        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void showRestTime() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setText("Skip");
        txtTimer.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        txtGetReady.setText("REST TIME");

        restTimeCountDown.start();
    }

    private void showFinished() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        txtGetReady.setText("FINISHED !!!");
        txtCountdown.setText("Congratulation ! \nYou're done with today exercises");
        txtCountdown.setTextSize(20);

        //Save Workout don in DB
        yogaDB.saveDay("" + Calendar.getInstance().getTimeInMillis());
    }

    private void initData() {

        list.add(new Exercise(R.drawable.easy_pose , "Easy Pose"));
        list.add(new Exercise(R.drawable.cobra_pose , "Cobra Pose"));
        list.add(new Exercise(R.drawable.downward_facing_dog , "Downward Facing Dog"));
        list.add(new Exercise(R.drawable.boat_pose , "Boat Pose"));
        list.add(new Exercise(R.drawable.half_pigeon , "Half Pigeon"));
        list.add(new Exercise(R.drawable.low_lunge , "Low Lunge"));
        list.add(new Exercise(R.drawable.upward_bow , "Upward Bow"));
        list.add(new Exercise(R.drawable.crescent_lunge , "Crescent Lunge"));
        list.add(new Exercise(R.drawable.warrior_pose , "Warrior Pose"));
        list.add(new Exercise(R.drawable.bow_pose , "Bow Pose"));
        list.add(new Exercise(R.drawable.warrior_pose_2 , "Warrior Pose 2"));
    }
}
