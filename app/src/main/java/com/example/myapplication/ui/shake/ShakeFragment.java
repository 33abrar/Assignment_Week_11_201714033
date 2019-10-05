package com.example.myapplication.ui.shake;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.squareup.seismic.ShakeDetector;

public class ShakeFragment extends Fragment implements ShakeDetector.Listener{

    private SensorManager SM;
    private Sensor mySensor;
    private static final int Req=1;

    public static ShakeFragment getInstance(Bundle bundle ){
        ShakeFragment instance = new ShakeFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Bundle arguments = getArguments();
        //mySensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        ShakeDetector SD=new ShakeDetector(this);
        SD.start(SM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shake, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        final TextView textView = (TextView)view.findViewById(R.id.text_shake);
        textView.setText(new StringBuilder("Shake to Call 999"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) makePhoneCall();
        }
    }

    @Override
    public void hearShake() {
        makePhoneCall();
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= (PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else{
            String dial ="tel:999";//+number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

}