package com.example.loginactivity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.loginactivity.model.MutasiModel;

import java.util.ArrayList;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.MutasiViewHolder> {
    Context context;
    ArrayList<MutasiModel> mutasiList;

    public MutasiAdapter(Context context, ArrayList<MutasiModel> mutasiList) {
        this.context = context;
        this.mutasiList = mutasiList;
    }

    @NonNull
    @Override
    public MutasiAdapter.MutasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mutasi, parent, false);
        return new  MutasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MutasiAdapter.MutasiViewHolder holder, int position) {
        holder.datetime.setText(mutasiList.get(position).getDatetime().toString());
        holder.details.setText(mutasiList.get(position).getDetails());
        holder.type.setText(mutasiList.get(position).getType());
        holder.nominal.setText(mutasiList.get(position).getNominal().toString());
        holder.balance.setText(mutasiList.get(position).getBalance().toString());


    }

    @Override
    public int getItemCount() {
        return mutasiList.size();
    }

    public class MutasiViewHolder extends RecyclerView.ViewHolder{
        TextView datetime;
        TextView details;
        TextView type;
        TextView nominal;
        TextView balance;

        public MutasiViewHolder(@NonNull View itemView) {
            super(itemView);

            datetime = itemView.findViewById(R.id.datetime);
            details = itemView.findViewById(R.id.details);
            type = itemView.findViewById(R.id.type);
            nominal = itemView.findViewById(R.id.nominal);
            balance = itemView.findViewById(R.id.balance);

        }
    }
}
