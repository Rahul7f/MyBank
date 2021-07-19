package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.rsin.mybank.roomdb.MyDatabase;
import com.rsin.mybank.roomdb.PaymentRecord;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {
    TrasferHistoryAdapter trasferHistoryAdapter;
    RecyclerView recyclerView;
    List<PaymentRecord> paymentRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        paymentRecords = new ArrayList<>();
        recyclerView = findViewById(R.id.history_recycler_view);

        MyDatabase myDatabase = Room.databaseBuilder(TransactionHistoryActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        paymentRecords = myDatabase.dao().getPaymentHistory();
        trasferHistoryAdapter = new TrasferHistoryAdapter(paymentRecords);
        recyclerView.setAdapter(trasferHistoryAdapter);
    }
}