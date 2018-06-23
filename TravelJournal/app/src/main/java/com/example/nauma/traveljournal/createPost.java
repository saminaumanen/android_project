package com.example.nauma.traveljournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        final Button createButton;
        createButton = (Button) findViewById(R.id.createButton);
        final EditText idTime = (EditText) findViewById(R.id.idTime);
        final EditText idEntry = (EditText) findViewById(R.id.idEntry);

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String time = idTime.getText().toString();
                String post = idEntry.getText().toString();

                frontPage.posts.add(time);
                frontPage.posts.add(post);

                Intent homePage = new Intent(createPost.this, frontPage.class);
                startActivity(homePage);
                finish();
            }
        });
    }

}
