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
import com.example.landingpageactivity.ModelResponse.Bengkel.BengkelResponse;
import com.example.landingpageactivity.ModelResponse.Montir.MontirResponse;
import com.example.landingpageactivity.MontirList;
import com.example.landingpageactivity.PemesananBarangActivity;
import com.example.landingpageactivity.PemesananMontirActivity;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.Utils.Function;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BengkelAdapter extends RecyclerView.Adapter<BengkelAdapter.BarangViewHolder> {
//    private List<BarangResponse.BarangData> barangList;
    private List<BengkelResponse.PemesananBengkelData> barangList;

    public BengkelAdapter(List<BengkelResponse.PemesananBengkelData> barangList) {
//        this.barangList = barangList;
        this.barangList = barangList;
    }

    @NonNull
    @Override
    public BengkelAdapter.BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BengkelAdapter.BarangViewHolder holder, int position) {
        BengkelResponse.PemesananBengkelData data = barangList.get(position);
        List<BengkelResponse.MontirData> bengkelss = new ArrayList<>();
        for (BengkelResponse.MontirData item : data.getMontir()){
            bengkelss.add(item);
        }
        //        BengkelResponse.Bengkel bengkels = new BengkelResponse.Bengkel();
//        for (BengkelResponse.BengkelData item : barangList){
//            bengkels = item.getBengkel();
//        }
        String url = ApiClient.LOCAL +data.getPhoto();
        Glide.with(holder.itemView).load(url).into(holder.barangimg);
        holder.title.setText(data.getNama_bengkel());
        holder.subTitle.setText(data.getAlamat());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), MontirList.class);
//            intent.putExtra("id", data.getId());
            intent.putExtra("bengkelss", (Serializable) bengkelss);
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
