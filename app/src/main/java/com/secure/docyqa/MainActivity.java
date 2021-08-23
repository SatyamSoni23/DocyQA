package com.secure.docyqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    Button upload;
    ImageView choose_image;
    MaterialTextView pdf_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, question_answer.class);
                startActivity(intent);
            }
        });
    }

    void init(){
        upload = findViewById(R.id.upload);
        choose_image = findViewById(R.id.choose_image);
        pdf_name = findViewById(R.id.pdf_name);
    }
}