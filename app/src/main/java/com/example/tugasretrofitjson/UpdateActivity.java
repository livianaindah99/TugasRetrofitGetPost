package com.example.tugasretrofitjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    //menambahkan beberapa variabel
    private TextView txtnim, txtnama, txtalamat, txtjk, txtnotelp;
    private EditText edtnim, edtnama, edtalamat, edtjk, edtnotelp;
    private Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //menyambungkan variabel dengan id yang pada xml
        txtnim = (TextView)findViewById(R.id.txtnimedit);
        txtnama = (TextView)findViewById(R.id.txtnamaedit);
        txtalamat = (TextView)findViewById(R.id.txtalamatedit);
        txtjk = (TextView)findViewById(R.id.txtjkedit);
        txtnotelp = (TextView)findViewById(R.id.txtnotelpedit);
        edtnim = (EditText)findViewById(R.id.nimedit);
        edtnama = (EditText)findViewById(R.id.namaedit);
        edtalamat = (EditText)findViewById(R.id.alamatedit);
        edtjk = (EditText)findViewById(R.id.jkedit);
        edtnotelp = (EditText)findViewById(R.id.notelpedit);
        btnupdate = (Button)findViewById(R.id.btnupdate);

        //mengambil data dari intent
        Intent mIntent = getIntent();
        edtnim.setText(mIntent.getStringExtra("Nim"));
        edtnama.setText(mIntent.getStringExtra("Nama"));
        edtalamat.setText(mIntent.getStringExtra("Alamat"));
        edtjk.setText(mIntent.getStringExtra("Jenis Kelamin"));
        edtnotelp.setText(mIntent.getStringExtra("No Telp"));

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi retrofit dan api interface untuk mengirimkan variabel2nya
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.5/retrofit/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
                Call<ResponseBody> call = jsonPlaceHolderAPI.updateDataMhs(
                        edtnim.getText().toString(),
                        edtnama.getText().toString(),
                        edtalamat.getText().toString(),
                        edtjk.getText().toString(),
                        edtnotelp.getText().toString());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateActivity.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(UpdateActivity.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
