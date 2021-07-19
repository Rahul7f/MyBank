package com.rsin.mybank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsin.mybank.roomdb.PaymentRecord;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrasferHistoryAdapter extends RecyclerView.Adapter<TrasferHistoryAdapter.ViewHolder> {
    List<PaymentRecord> paymentRecords;

    public TrasferHistoryAdapter(List<PaymentRecord> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }

    @NonNull
    @NotNull
    @Override
    public TrasferHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrasferHistoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(paymentRecords.get(position).getName());
        holder.bank.setText("Bank: "+paymentRecords.get(position).getBank());
        holder.tid.setText("Transaction ID: "+paymentRecords.get(position).getId());
        holder.date.setText(date(paymentRecords.get(position).getTime()));
        holder.time.setText(time(paymentRecords.get(position).getTime()));
        holder.amount.setText("Rs. "+paymentRecords.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return paymentRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,bank,tid,date,time,amount;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.h_name);
            bank = itemView.findViewById(R.id.h_bank);
            tid = itemView.findViewById(R.id.h_T_id);
            date = itemView.findViewById(R.id.h_date);
            amount = itemView.findViewById(R.id.h_amount);
            time = itemView.findViewById(R.id.h_time);
        }
    }
    String time(long tm)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        String dateString = formatter.format(new Date(tm));
        return dateString;
    }
    String date(long tm)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(tm));
        return dateString;
    }
}
