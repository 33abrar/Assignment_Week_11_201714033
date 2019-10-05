package com.example.myapplication.ui.accelerometer;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.AData;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccelerometerFragment extends Fragment implements SensorEventListener {

    private SensorManager SM;
    private Sensor mySensor;
    private static final String KEY_SENSOR_TYPE = "Sensor.TYPE_ACCELEROMETER";
    private TextView x_txt, y_txt, z_txt;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static AccelerometerFragment getInstance(Bundle bundle ){
        AccelerometerFragment instance = new AccelerometerFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Bundle arguments = getArguments();
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accelerometer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = database.getInstance().getReference("Accelerometer");
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        x_txt = (TextView)view.findViewById(R.id.x_txt);
        y_txt = (TextView)view.findViewById(R.id.y_txt);
        z_txt = (TextView)view.findViewById(R.id.z_txt);
        final TextView textView = (TextView)view.findViewById(R.id.text_retrieve);
        textView.setText(new StringBuilder("Accelerometer Sensors Output"));
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int) (sensorEvent.values[0]);
        int y = (int) (sensorEvent.values[1]);
        int z = (int) (sensorEvent.values[2]);

        String key = databaseReference.push().getKey();
        AData aData = new AData(x,y,z);

        x_txt.setText("X is: " + x);
        y_txt.setText("Y is: " + y);
        z_txt.setText("Z is: " + z);

        if ((x==0 && y==0 && z==9)||(x==0 && y==0 && z==-9)) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            //databaseReference.child(key).setValue(aData);
        }
        else{
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        SM.unregisterListener(this);
        super.onPause();
    }
}