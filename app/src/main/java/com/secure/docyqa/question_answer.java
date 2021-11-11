package com.secure.docyqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.secure.docyqa.databinding.ActivityQuestionAnswerBinding;

import java.io.IOException;
import java.util.HashMap;
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
    private String URL = "http://1a9c-34-125-105-102.ngrok.io/";
    private String APPROACH = MethodSelection.APPROACH;
    private Boolean CONNECTION_LOST = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question_answer);

        fetch_server_url();

        binding.answer.setMovementMethod(new ScrollingMovementMethod());

        binding.answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CONNECTION_LOST){
                    Toast.makeText(question_answer.this, "No Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(binding.question.getText().toString())){
                    binding.question.setError("Please enter question");
                    return;
                }
                String question = binding.question.getText().toString();
                start_network_call(question);
            }
        });
    }

    private void fetch_server_url() {
        HashMap<String, Object> defaultRate = new HashMap<>();
        defaultRate.put("URL", String.valueOf(getVersionCode()));

        FirebaseRemoteConfig mFirebaseRemoteConfig;
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(defaultRate);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if(task.isSuccessful()){
                            final String NEW_URL = mFirebaseRemoteConfig.getString("URL");
                            URL = NEW_URL;
                            Log.d("TESTING", URL);
                            CONNECTION_LOST = false;
                        }
                        else{
                            Toast.makeText(question_answer.this, "Connection Lost", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private int getVersionCode(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionCode;
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