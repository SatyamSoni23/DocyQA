package com.secure.docyqa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.secure.docyqa.databinding.ActivityQuestionAnswerBinding;

public class question_answer extends AppCompatActivity {

    ActivityQuestionAnswerBinding binding;
    String file_name = MainActivity.file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question_answer);

        binding.answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(binding.question.getText().toString())){
                    binding.question.setError("Please enter question");
                    return;
                }
                String question = binding.question.getText().toString();
                start_network_call(question);
            }
        });
    }

    private void start_network_call(String question){
        //file_name, question;
    }
}