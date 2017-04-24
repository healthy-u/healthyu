package com.example.dustin.cs495helloworld;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class PedometerActivity extends Activity implements SensorEventListener{
    private int stepCount;
    private int runStart;

    private TextView textView;
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    protected void onResume() {

        super.onResume();
        mSensorManager.registerListener(this, mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    public void onSensorChanged (SensorEvent e)
    {
        if (e.sensor.getType()==18){
            stepCount++;
            textView.setText("Current Number of Steps: " + stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    //These two functions are to help log the step counts of runs

    public void markRunStart(){
        runStart = stepCount;
    }

    public int endRunReport(){
        return stepCount-runStart;
    }

    public int getSteps(){
        return stepCount;
    }

    public void reset(){
        stepCount = 0;
    }
}
