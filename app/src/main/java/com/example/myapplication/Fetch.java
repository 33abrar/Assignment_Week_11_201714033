package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fetch extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private List<AData> aDatatList;
    private CustomAdapter customAdapter;
    private GridView gridView;

    String[] data = {"A", "B", "C", "D", "E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        databaseReference = database.getInstance().getReference("Gyroscope");
        aDatatList = new ArrayList<>();
        gridView = findViewById(R.id.data_gridView);
        gridView.bringToFront();
        Toast.makeText(this,"F",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                aDatatList.clear();
                for (DataSnapshot  dataSnapshot1 : dataSnapshot.getChildren()){
                    AData aData = dataSnapshot1.getValue(AData.class);
                    aDatatList.add(aData);
                }
                customAdapter = new CustomAdapter();
                gridView.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
        }
        });
        super.onStart();
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return aDatatList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.sample_retrieve,null);

            TextView x_txt = view1.findViewById(R.id.x_txt);
            TextView y_txt = view1.findViewById(R.id.y_txt);
            TextView z_txt = view1.findViewById(R.id.z_txt);
            AData aData = aDatatList.get(i);

            String x = Integer.toString(aData.getX()).trim();
            String y = Integer.toString(aData.getY()).trim();
            String z = Integer.toString(aData.getZ()).trim();

            x_txt.setText(x);
            y_txt.setText(y);
            z_txt.setText(z);

            return view1;
        }
    }
}
