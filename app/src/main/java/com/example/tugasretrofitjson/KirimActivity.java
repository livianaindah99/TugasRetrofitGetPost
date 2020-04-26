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

public class KirimActivity extends AppCompatActivity {

    //menambahkan beberapa variabel
    private TextView txtnim, txtnama, txtalamat, txtjk, txtnotelp;
    private EditText edtnim, edtnama, edtalamat, edtjk, edtnotelp;
    private Button proses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim);

        //menyambungkan variabel dengan id yang pada xml
        txtnim = (TextView)findViewById(R.id.txtnim);
        txtnama = (TextView)findViewById(R.id.txtnama);
        txtalamat = (TextView)findViewById(R.id.txtalamat);
        txtjk = (TextView)findViewById(R.id.txtjk);
        txtnotelp = (TextView)findViewById(R.id.txtnotelp);
        edtnim = (EditText)findViewById(R.id.nim);
        edtnama = (EditText)findViewById(R.id.nama);
        edtalamat = (EditText)findViewById(R.id.alamat);
        edtjk = (EditText)findViewById(R.id.jk);
        edtnotelp = (EditText)findViewById(R.id.notelp);
        proses = (Button)findViewById(R.id.btnproses);

        //handler ketika button proses diklik dan memanggil method kirimData
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimData();
                Intent intent = new Intent(KirimActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //buat method kirimData untuk mengambil semua inputan user kemudian dimasukkan ke dalam variabelnya
    private void kirimData(){
        String id_siswa = edtnim.getText().toString().trim();
        String nama = edtnama.getText().toString().trim();
        String alamat = edtalamat.getText().toString().trim();
        String jenis_kelamin = edtjk.getText().toString().trim();
        String no_telp = edtnotelp.getText().toString().trim();

        //inisialisasi retrofit dan api interface untuk mengirimkan variabel2nya
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5/retrofit/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<ResponseBody> call = jsonPlaceHolderAPI.addDataMhs(
                id_siswa,
                nama,
                alamat,
                jenis_kelamin,
                no_telp
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(KirimActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(KirimActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
