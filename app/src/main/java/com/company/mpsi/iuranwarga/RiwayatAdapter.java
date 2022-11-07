package com.company.mpsi.iuranwarga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import individual.Iuran;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private List<Iuran> listIuran;

    public RiwayatAdapter(List<Iuran> listIuran) {
        this.listIuran = listIuran;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayat_pembayaran_frags, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int tanggal = listIuran.get(position).getTanggal();
        int bulan = listIuran.get(position).getBulan();
        int tahun = listIuran.get(position).getTahun();
        double total = listIuran.get(position).getTotalIuran();
        holder.setData(tanggal,bulan,tahun,total);
    }

    @Override
    public int getItemCount() {
        return listIuran.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal_riwayat;
        TextView total_riwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggal_riwayat = itemView.findViewById(R.id.tanggal_riwayat_pembayaran);
            total_riwayat = itemView.findViewById(R.id.total_riwayat_pembayaran);
        }

        private void setData(int tanggal, int bulan, int tahun, double total){
            String tanggal_jadi = tanggal + "/" + bulan + "/" + tahun;
            tanggal_riwayat.setText(tanggal_jadi);
            total_riwayat.setText("Total Rp. "+total);
        }
    }
}
