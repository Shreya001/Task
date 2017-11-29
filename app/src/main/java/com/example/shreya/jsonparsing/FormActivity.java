package com.example.shreya.jsonparsing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class FormActivity extends AppCompatActivity {
        EditText title;
        EditText body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent intent = getIntent();
        final String REST_API_BASEURL = intent.getStringExtra("BASE_URL");
        final String tittle = String.valueOf(title.getText());
        String textBody = String.valueOf(body.getText());


        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(REST_API_BASEURL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();
                PostService taskService = retrofit.create(PostService.class);

                Call<ResponseBody> call = taskService.createPost(new userPost(tittle));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                            Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

}
