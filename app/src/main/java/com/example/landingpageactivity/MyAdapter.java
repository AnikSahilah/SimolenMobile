package com.example.landingpageactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    String data1[], data2[], data3[], data4[], data5[];
    int images[];
    Context context;
    public MyAdapter(Context ct, String s1[], String s2[], String s3[], String s4[], String s5[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        images = img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =   inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
        holder.myText4.setText(data4[position]);
        holder.myText5.setText(data5[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView myText1, myText2, myText3, myText4, myText5;
        ImageView myImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.Nama1);
            myText2 = itemView.findViewById(R.id.Lokasi1);
            myText3 = itemView.findViewById(R.id.Kendaraan1);
            myText4 = itemView.findViewById(R.id.Nama);
            myText5 = itemView.findViewById(R.id.Komentar);
            myImage = itemView.findViewById(R.id.foto);;
        }
    }
}

