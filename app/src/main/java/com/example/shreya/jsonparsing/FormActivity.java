package com.example.shreya.jsonparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class FormActivity extends AppCompatActivity {
        EditText title;
        EditText body;
        PostService taskService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        title = (EditText)findViewById(R.id.title_value);
        body = (EditText)findViewById(R.id.body_value);

        Intent intent = getIntent();
        final String REST_API_BASEURL = intent.getStringExtra("BASE_URL");

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(REST_API_BASEURL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();
                taskService = retrofit.create(PostService.class);
                String tittle = String.valueOf(title.getText());
                String textBody = String.valueOf(body.getText());

                sendPost(tittle,textBody);
            }

        });

    }

    public void sendPost(String title, String body){
        taskService.createPost(title,body,1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                                    }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    public void showResponse(String response){
        TextView tv = (TextView)findViewById(R.id.tv_response);
        if(tv.getVisibility() == View.GONE){
            tv.setVisibility(View.VISIBLE);
        }

        tv.setText(response);
    }
}