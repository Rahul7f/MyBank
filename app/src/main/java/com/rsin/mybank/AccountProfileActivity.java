package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;

public class AccountProfileActivity extends AppCompatActivity {
    MaterialButton  transfer_btn;
    BankData  bankData;
    TextView name,bank,account,money,ifsc;
    MyAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);
        transfer_btn = findViewById(R.id.pay_btn);
        name = findViewById(R.id.account_holder_tv);
        bank = findViewById(R.id.Bank_name_tv);
        account = findViewById(R.id.account_no_tv);
        ifsc = findViewById(R.id.ifsc_tv);
        money = findViewById(R.id.total_ammount_tv);
        bankData  = (BankData) getIntent().getSerializableExtra("accObject");
        name.setText(bankData.getName());
        bank.setText(bankData.getBank());
        account.setText(bankData.getAccount_no());
        money.setText(bankData.getAmount());
        ifsc.setText(bankData.getIFSC());




        transfer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TransferActivity.class);
                intent.putExtra("receiver",bankData);
                startActivity(intent);

            }
        });



    }
}


