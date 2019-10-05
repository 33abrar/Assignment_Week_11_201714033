package com.example.myapplication.ui.map;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DirectionMap;
import com.example.myapplication.MapsActivity;
import com.example.myapplication.R;

public class MapFragment extends Fragment{

    private Button location_btn, direction_btn;

    public static MapFragment getInstance(Bundle bundle) {
        MapFragment instance = new MapFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        location_btn = view.findViewById(R.id.location_btn);
        direction_btn = view.findViewById(R.id.direction_btn);
        final TextView map_txt = (TextView) view.findViewById(R.id.text_map);
        map_txt.setText(new StringBuilder("Map"));
        direction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

            private void openDialog() {
                dir_dialog dirDialog = new dir_dialog();
                dirDialog.show(getActivity().getSupportFragmentManager(), "direction dialog");
            }
        });
        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}