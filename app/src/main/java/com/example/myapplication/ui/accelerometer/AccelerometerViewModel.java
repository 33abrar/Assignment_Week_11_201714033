package com.example.myapplication.ui.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccelerometerViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Sensor mySensor;
    private SensorManager SM;

    public AccelerometerViewModel() {
        mText = new MutableLiveData<>();
        //SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mText.setValue("This is Accelerometer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}