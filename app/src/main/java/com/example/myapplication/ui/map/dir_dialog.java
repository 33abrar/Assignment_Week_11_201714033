package com.example.myapplication.ui.map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.DirectionMap;
import com.example.myapplication.R;

public class dir_dialog extends AppCompatDialogFragment {

    private EditText source_txt, destination_txt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.direction_dialog,null);
        source_txt = view.findViewById(R.id.source_txt);
        destination_txt = view.findViewById(R.id.destination_txt);

        builder.setView(view).setTitle("Direction Dialog").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if ((!source_txt.getText().toString().isEmpty()) && (!destination_txt.getText().toString().isEmpty())) {
                    Intent intent = new Intent(getContext(), DirectionMap.class);
                    intent.putExtra("source", source_txt.getText().toString());
                    intent.putExtra("destination", destination_txt.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(),"Please give locations",Toast.LENGTH_LONG).show();
                }
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
