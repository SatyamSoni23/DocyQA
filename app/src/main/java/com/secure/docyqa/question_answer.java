package com.secure.docyqa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class question_answer extends AppCompatActivity {

    ConstraintLayout buffer;
    TextInputEditText question;
    MaterialTextView answer;
    MaterialButton answer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        init();
    }

    void init(){
        buffer = findViewById(R.id.buffer);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        answer_btn = findViewById(R.id.answer_btn);
    }
}