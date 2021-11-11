package com.secure.docyqa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.secure.docyqa.databinding.ActivityMethodSelectionBinding;

public class MethodSelection extends AppCompatActivity {

    ActivityMethodSelectionBinding binding;
    public static String APPROACH = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_method_selection);

        binding.NaiveApproach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APPROACH = "1";
                start_next_activity();
            }
        });

        binding.Word2vecApproach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APPROACH = "2";
                start_next_activity();
            }
        });

        binding.gloveApproach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APPROACH = "3";
                start_next_activity();
            }
        });
    }

    private void start_next_activity(){
        Intent intent = new Intent(this, question_answer.class);
        startActivity(intent);
    }
}