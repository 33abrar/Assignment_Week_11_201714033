package com.example.myapplication.ui.retrieve;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Fetch;
import com.example.myapplication.R;
import com.example.myapplication.AData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveFragment extends Fragment{

    private FirebaseDatabase database;
    private static DatabaseReference databaseReference;
    private CustomAdapter customAdapter;
    private List<AData> aDatatList;
    private List<String> strList;
    private  StringBuffer str;
    private  TextView textView5;

    private GridView data_gridView;
    //String[] data = {"A", "B", "C", "D", "E"};

    public static RetrieveFragment getInstance(Bundle bundle ){
        RetrieveFragment instance = new RetrieveFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        databaseReference = database.getInstance().getReference("Gyroscope");

        //customAdapter = new CustomAdapter();
        //Toast.makeText(getContext(),"F",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //str.append("");

        return inflater.inflate(R.layout.fragment_retrieve, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data_gridView = view.findViewById(R.id.data_gridView);
        textView5 = view.findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Fetch.class);
                startActivity(intent);
            }
        });
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                aDatatList = new ArrayList<>();
                //aDatatList.clear();
                for (DataSnapshot  dataSnapshot1 : dataSnapshot.getChildren()){
                    AData aData = dataSnapshot1.getValue(AData.class);
                    aDatatList.add(aData);
                }
                customAdapter = new CustomAdapter();
                data_gridView.setAdapter(customAdapter);
                data_gridView.bringToFront();
                //data_gridView.scrollListBy(5);
                //textView2.setText(Integer.toString(customAdapter.getCount()));
                //textView2.setText(str.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //data_gridView.setAdapter(customAdapter);
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

            String x = Integer.toString(aData.getX());
            String y = Integer.toString(aData.getY());
            String z = Integer.toString(aData.getZ());

            //str.append(x+y+z);

            //x_txt.setText(Integer.toString(i));
            //y_txt.setText(Integer.toString(i));
            //z_txt.setText(Integer.toString(i));

            x_txt.setText(x);
            y_txt.setText(y);
            z_txt.setText(z);


            return view1;
        }
    }
}