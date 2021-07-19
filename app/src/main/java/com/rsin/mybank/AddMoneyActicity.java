package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;
import com.rsin.mybank.roomdb.MyDatabase;
import com.rsin.mybank.roomdb.PaymentRecord;

public class AddMoneyActicity extends AppCompatActivity {
    TextInputLayout ammount_et;
    MaterialButton pay_btn;
    MyAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_acticity);
        ammount_et = findViewById(R.id.payme_ammount_et);
        pay_btn = findViewById(R.id.payme_bnt);
        myAccount = getmyaccount();
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ammount_et.getEditText().getText().toString().equals(null)||ammount_et.getEditText().getText().toString().equals(""))
                {
                    Toast.makeText(AddMoneyActicity.this, "enter amount to Add", Toast.LENGTH_SHORT).show();
                }
                else {
                    int money = Integer.valueOf(ammount_et.getEditText().getText().toString())+Integer.valueOf(myAccount.getAmount());
                    MyAccount updateMyAccount = new MyAccount(myAccount.getId(),myAccount.getName(),myAccount.getEmail(),myAccount.getAccount_no(),myAccount.getBank(),myAccount.getIFSC(),String.valueOf(money));
                    ProgressDialog progressDialog = new ProgressDialog(AddMoneyActicity.this);
                    progressDialog.setTitle("Transaction in process..");
                    progressDialog.setMessage("please do not close or refresh the pages");

                    CountDownTimer timer = new CountDownTimer(3000, 3000)
                    {
                        public void onTick(long millisUntilFinished)
                        {
                            progressDialog.show();
                        }

                        public void onFinish()
                        {
                            payMoney(updateMyAccount);
                            Toast.makeText(AddMoneyActicity.this, "done", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    };
                    timer.start();
                }
            }
        });

    }

    void payMoney(MyAccount myAccount) {
        MyDatabase myDatabase = Room.databaseBuilder(AddMoneyActicity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        myDatabase.dao().updateMyAccount(myAccount);
        startActivity(new Intent(getApplicationContext(),ConformationActivity.class));

    }
    MyAccount getmyaccount(){
        MyDatabase myDatabase = Room.databaseBuilder(AddMoneyActicity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        MyAccount myAccounts =  myDatabase.dao().getMyAccount().get(0);
        return myAccounts;
    }
}