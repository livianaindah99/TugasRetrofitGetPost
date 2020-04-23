package com.example.tugasretrofitjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private List<Post> dataSiswa;
    private Context context;

    public SiswaAdapter(List<Post> dataSiswa, Context context) {
        this.dataSiswa = dataSiswa;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View Siswaview = layoutInflater.inflate(R.layout.item_ssw, parent, false);

        ViewHolder viewHolder = new ViewHolder(Siswaview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaAdapter.ViewHolder holder, int position) {
        final Post ssw = dataSiswa.get(position);

        TextView textView = holder.IdsswTextView;
        TextView textView1 = holder.nameTextView;
        TextView textView2 = holder.alamatTextView;
        TextView textView3 = holder.jkTextView;
        TextView textView4 = holder.notelpTextView;

        textView.setText(ssw.getId_siswa());
        textView1.setText(ssw.getNama());
        textView2.setText(ssw.getAlamat());
        textView3.setText(ssw.getJenis_kelamin());
        textView4.setText(ssw.getNo_telp());
    }

    @Override
    public int getItemCount() {

        return dataSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView IdsswTextView;
        public TextView nameTextView;
        public TextView alamatTextView;
        public TextView jkTextView;
        public TextView notelpTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IdsswTextView = (TextView)itemView.findViewById(R.id.ssw_idsiswa);
            nameTextView = (TextView)itemView.findViewById(R.id.ssw_name);
            alamatTextView = (TextView)itemView.findViewById(R.id.ssw_alamat);
            jkTextView = (TextView)itemView.findViewById(R.id.ssw_jk);
            notelpTextView = (TextView)itemView.findViewById(R.id.ssw_telp);
        }
    }
}
