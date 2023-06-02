package com.example.landingpageactivity.ViewTab.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.landingpageactivity.ModelResponse.Barang.BarangResponse;
import com.example.landingpageactivity.ModelResponse.Montir.MontirResponse;
import com.example.landingpageactivity.PemesananBarangActivity;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.Utils.Function;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    private List<BarangResponse.BarangData> barangList;
    private List<MontirResponse.MontirData> montirData;

    public BarangAdapter(List<BarangResponse.BarangData> barangList) {
        this.barangList = barangList;
//        this.montirData = montir;
    }

    @NonNull
    @Override
    public BarangAdapter.BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.BarangViewHolder holder, int position) {
        BarangResponse.BarangData data = barangList.get(position);
        String url = ApiClient.LOCAL +data.getPhoto();
        Glide.with(holder.itemView).load(url).into(holder.barangimg);
        holder.title.setText(data.getNamaBarang());
        holder.subTitle.setText(Function.parseRupiah(Double.parseDouble(data.getHarga())));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PemesananBarangActivity.class);
            intent.putExtra("id", data.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder {
        ImageView barangimg;
        TextView title,subTitle;
        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            barangimg = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleTextView);
            subTitle = itemView.findViewById(R.id.subtitleTextView);
        }
    }
}
