package com.example.tugasretrofitjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    private SiswaAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_title);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5/retrofit/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Post>> call = jsonPlaceHolderAPI.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void handleAddButton(View view) {
        Intent handleadd = new Intent(MainActivity.this, KirimActivity.class);
        startActivity(handleadd);
    }

    private void generateDataList(List<Post> dataList) {
        recyclerView = findViewById(R.id.id_rv);
        //membuat adapter dan menampilkan list datanya
        adapter = new SiswaAdapter(dataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        //membuat recyclerview dengan layout default
        recyclerView.setLayoutManager(layoutManager);
        //menghubungkan adapter dengan recyclerview
        recyclerView.setAdapter(adapter);
    }
}
