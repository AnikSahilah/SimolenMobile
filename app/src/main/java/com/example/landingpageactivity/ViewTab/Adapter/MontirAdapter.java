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
import com.example.landingpageactivity.PemesananBarangActivity;
import com.example.landingpageactivity.PemesananMontirActivity;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.Utils.Function;

import java.io.Serializable;
import java.util.List;

public class MontirAdapter extends RecyclerView.Adapter<MontirAdapter.BarangViewHolder> {
    private List<BengkelResponse.MontirData> barangList;
    private List<MontirResponse.MontirData> montirData;
    public MontirAdapter(List<BengkelResponse.MontirData> barangList) {
        this.barangList = barangList;
//        this.montirData = montir;
    }

    @NonNull
    @Override
    public MontirAdapter.BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MontirAdapter.BarangViewHolder holder, int position) {
        BengkelResponse.MontirData data = barangList.get(position);
        String url = ApiClient.LOCAL +data.getPhoto();
        String url_user = url.replace("foto_bengkel", "foto_user");
        Glide.with(holder.itemView).load(url_user).into(holder.barangimg);
        holder.title.setText(data.getName());
        holder.subTitle.setText(data.getStatus());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PemesananMontirActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("montir", (Serializable) data);
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
