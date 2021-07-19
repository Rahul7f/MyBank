package com.rsin.mybank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;

import org.w3c.dom.Text;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    Context context;
    List<BankData> bankDataList;


    public AccountAdapter(Context context, List<BankData> bankDataList) {
        this.context = context;
        this.bankDataList = bankDataList;

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AccountAdapter.ViewHolder holder, int position) {

        holder.bank.setText(bankDataList.get(position).getBank());
        holder.name.setText(bankDataList.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AccountProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("accObject",bankDataList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bankDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, bank;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.account_card);
            name = itemView.findViewById(R.id.account_holder_l);
            bank = itemView.findViewById(R.id.bank_l);
        }
    }
}
