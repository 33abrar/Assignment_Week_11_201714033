package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DirectionMap extends AppCompatActivity {

    private WebView directionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_map);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");

        directionView = (WebView) findViewById(R.id.directionView);
        directionView.setWebViewClient(new WebViewClient());
        directionView.getSettings().setJavaScriptEnabled(true);
        directionView.loadUrl("http://maps.google.ca/?q=From "+source+" to "+destination);
    }
}
