package com.example.myapplication.ui.proximity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.gyroscope.GyroscopeFragment;

public class ProximityFragment extends Fragment implements SensorEventListener {

    private SensorManager SM;
    private Sensor mySensor;
    private static final String KEY_SENSOR_TYPE = "Sensor.TYPE_ACCELEROMETER";
    private Vibrator vibrator;
    private TextView x_txt, y_txt, z_txt;

    public static GyroscopeFragment getInstance(Bundle bundle ){
        GyroscopeFragment instance = new GyroscopeFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        Bundle arguments = getArguments();
        mySensor = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gyroscope, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        x_txt = (TextView)view.findViewById(R.id.x_txt);
        y_txt = (TextView)view.findViewById(R.id.y_txt);
        z_txt = (TextView)view.findViewById(R.id.z_txt);
        final TextView textView = (TextView)view.findViewById(R.id.text_gyroscope);
        textView.setText(new StringBuilder("Proximity Sensor Output"));
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int) (sensorEvent.values[0]);

        x_txt.setText("X is: " + x);

        if (x==8){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
        else{
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
}