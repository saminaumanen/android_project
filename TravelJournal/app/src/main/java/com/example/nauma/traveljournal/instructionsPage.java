package com.example.nauma.traveljournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class instructionsPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_page);
        WebView instructionsWebView;
        instructionsWebView = (WebView) findViewById(R.id.WebViewInstructions);
        instructionsWebView.loadUrl("file:///android_asset/instructions.html");

        Button closePage;
        closePage = (Button) findViewById(R.id.closeButton);
        closePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
