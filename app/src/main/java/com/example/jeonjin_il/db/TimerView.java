package com.example.jeonjin_il.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jeonjin-il on 2016. 11. 22..
 */

public class TimerView extends AppCompatActivity implements CircleTimerView.CircleTimerListener {
    private CircleTimerView mTimer;
    private EditText mTimerSet_m;
    private EditText mTimerSet_s;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTimer = (CircleTimerView) findViewById(R.id.ctv);
        mTimer.setCircleTimerListener(this);
        mTimerSet_m = (EditText) findViewById(R.id.time_set_et_m);
        mTimerSet_s = (EditText) findViewById(R.id.time_set_et_s);
    }


    public void setTime(View v) {
        try {
            int time = Integer.parseInt(mTimerSet_m.getText().toString()) * 60 + Integer.parseInt(mTimerSet_s.getText().toString());
            mTimer.setCurrentTime(time);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void start(View v)
    {
        mTimer.startTimer();
    }

    public void pause(View v)
    {
        mTimer.pauseTimer();
    }

    public void stop(View v){
        mTimer.setCurrentTime(0);
        mTimer.pauseTimer();
    }

    @Override
    public void onTimerStop() {}
    @Override
    public void onTimerStart(int currentTime) {}
    @Override
    public void onTimerPause(int currentTime) {}
    @Override
    public void onTimerTimingValueChanged(int time) {}
    @Override
    public void onTimerSetValueChanged(int time){}
    @Override
    public void onTimerSetValueChange(int time) {}

}

