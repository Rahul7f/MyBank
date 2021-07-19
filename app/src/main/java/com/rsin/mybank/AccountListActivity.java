package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.accounts.Account;
import android.os.Bundle;

import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;
import com.rsin.mybank.roomdb.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class AccountListActivity extends AppCompatActivity {
    List<BankData> bankDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        bankDataList= new ArrayList<>();


        MyDatabase myDatabase = Room.databaseBuilder(AccountListActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        bankDataList =  myDatabase.dao().getCostumers();
        AccountAdapter accountAdapter = new AccountAdapter(getApplicationContext(),bankDataList);
        RecyclerView recyclerView = findViewById(R.id.account_recycler_view);
        recyclerView.setAdapter(accountAdapter);
    }
}