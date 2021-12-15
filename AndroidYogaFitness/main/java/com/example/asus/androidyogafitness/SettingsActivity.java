package com.example.asus.androidyogafitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.asus.androidyogafitness.Database.YogaDB;
import com.example.asus.androidyogafitness.Utils.AlarmNotificationReceiver;

import java.util.Calendar;
import java.util.Date;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class SettingsActivity extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy , rdiMedium , rdiHard;
    RadioGroup radioGroup;
    YogaDB yogaDB;
    StickySwitch switchAlarm;
    TimePicker timePicker;

    int getHour;
    int getMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnSave = (Button)findViewById(R.id.btnSave);
        rdiEasy = (RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton)findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton)findViewById(R.id.rdiHard);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        switchAlarm = (StickySwitch)findViewById(R.id.switchAlarm);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        yogaDB = new YogaDB(this);
        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        //Event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveWorkoutMode();
                saveAlarm(switchAlarm.getDirection() == StickySwitch.Direction.LEFT);
                Toast.makeText(SettingsActivity.this, "SAVED !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked) {

        if (checked){

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingsActivity.this , AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this , 0 , intent , 0);

            //Set time to alarm
            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();  //not used
            Calendar date = Calendar.getInstance();


            if (Build.VERSION.SDK_INT < 23){

                getHour = timePicker.getCurrentHour();
                getMinute = timePicker.getCurrentMinute();
            }
            else {

                getHour = timePicker.getHour();
                getMinute = timePicker.getMinute();
            }

            calendar.set(date.get(Calendar.YEAR) , date.get(Calendar.MONTH) , date.get(Calendar.DAY_OF_MONTH) , getHour , getMinute);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , AlarmManager.INTERVAL_DAY , pendingIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

            Toast.makeText(this, "Alarm will wake at:  " + getHour + ":" + getMinute, Toast.LENGTH_LONG).show();
            Log.d("DEBUG" , "Alarm will wake at:  " + getHour + ":" + getMinute);

        }
        else {

            //Cancel Alarm
            Intent intent = new Intent(SettingsActivity.this , AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this , 0 , intent , 0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);

        }
    }

    private void saveWorkoutMode() {

        int selectedID = radioGroup.getCheckedRadioButtonId();
        if (selectedID == rdiEasy.getId())
            yogaDB.saveSettingsMode(1);
        else if (selectedID == rdiMedium.getId())
            yogaDB.saveSettingsMode(2);
        else if (selectedID == rdiHard.getId())
            yogaDB.saveSettingsMode(3);

    }

    private void setRadioButton(int mode) {

        if (mode == 1){
            radioGroup.check(R.id.rdiEasy);
        }
        else if (mode == 2){
            radioGroup.check(R.id.rdiMedium);
        }
        else if (mode == 3){
            radioGroup.check(R.id.rdiHard);
        }
    }
}
