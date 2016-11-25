package com.example.jeonjin_il.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jeonjin-il on 2016. 11. 22..
 */

public class TimerView extends AppCompatActivity implements CircleTimerView.CircleTimerListener {
    private CircleTimerView mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTimer = (CircleTimerView) findViewById(R.id.ctv);
        mTimer.setCircleTimerListener(this);
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
