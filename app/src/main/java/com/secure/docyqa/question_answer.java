package com.secure.docyqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.secure.docyqa.databinding.ActivityQuestionAnswerBinding;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class question_answer extends AppCompatActivity {

    ActivityQuestionAnswerBinding binding;
    String FILENAME = MainActivity.file_name + ".pdf";
    ProgressDialog dialog;
    private String URL = "http://ea2c-104-196-129-101.ngrok.io/";
    private String APPROACH = MethodSelection.APPROACH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question_answer);

        binding.answer.setMovementMethod(new ScrollingMovementMethod());

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

        dialog = new ProgressDialog(this);
        dialog.setMessage("Processing...");
        dialog.show();

        //OkHttpClient okHttpClient = new OkHttpClient();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .callTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("approach", APPROACH)
                .add("file_name", FILENAME)
                .add("question", question)
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("TESTING", e.getMessage().toString());
                dialog.dismiss();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            binding.answer.setText(response.body().string());
                            dialog.dismiss();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
}