package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;
import com.rsin.mybank.roomdb.MyDatabase;
import com.rsin.mybank.roomdb.PaymentRecord;

import java.util.logging.Handler;

public class TransferActivity extends AppCompatActivity {
    TextInputLayout ammount_et;
    MaterialButton pay_btn;
    BankData rereiverData;
    MyAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ammount_et = findViewById(R.id.enter_ammount_et);
        pay_btn = findViewById(R.id.pay_btn);
        rereiverData = (BankData) getIntent().getSerializableExtra("receiver");
        myAccount = getmyaccount();


        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ammount_et.getEditText().getText().toString().equals(null)||ammount_et.getEditText().getText().toString().equals(""))
                {
                    Toast.makeText(TransferActivity.this, "enter amount to pay", Toast.LENGTH_SHORT).show();
                }
                else {
                    String topay = ammount_et.getEditText().getText().toString();
                    int TOPAY = Integer.valueOf(topay);
                    if (TOPAY>Integer.valueOf(myAccount.getAmount()))
                    {
                        showAlert();
                    }
                    else {
                        //update my account
                        int left_amount = Integer.valueOf(myAccount.getAmount())-TOPAY;
                        int addAmount = Integer.valueOf(rereiverData.getAmount())+TOPAY;
                        Long time = System.currentTimeMillis();
                        MyAccount updateMyAccount = new MyAccount(myAccount.getId(),myAccount.getName(),myAccount.getEmail(),myAccount.getAccount_no(),myAccount.getBank(),myAccount.getIFSC(),String.valueOf(left_amount));
                        //update receiver ammount
                        BankData updateReceiverAccount = new BankData(rereiverData.getId(),rereiverData.getName(),rereiverData.getEmail(),rereiverData.getAccount_no(),rereiverData.getBank(),rereiverData.getIFSC(),String.valueOf(addAmount));
                        //update history
                        PaymentRecord paymentRecord = new PaymentRecord(rereiverData.getName(),rereiverData.getBank(),String.valueOf(TOPAY),time);
                        ProgressDialog progressDialog = new ProgressDialog(TransferActivity.this);
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
                                payMoney(updateReceiverAccount,paymentRecord,updateMyAccount);
                                Toast.makeText(TransferActivity.this, "done", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        };
                        timer.start();
                    }
                }
            }
        });
    }

    void showAlert()
    {
        new MaterialAlertDialogBuilder(TransferActivity.this)
                .setTitle("Insufficient balance")
                .setMessage("Please add money to your bank account")
                .setPositiveButton("Add Money", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    void payMoney(BankData bankData,PaymentRecord paymentRecord,MyAccount myAccount) {
        MyDatabase myDatabase = Room.databaseBuilder(TransferActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        // transfer history
        myDatabase.dao().addPayment(paymentRecord);
        //update my account
        myDatabase.dao().updateMyAccount(myAccount);
        //update receiver account
        myDatabase.dao().updateCostumerBank(bankData);
        startActivity(new Intent(getApplicationContext(),ConformationActivity.class));

    }

    MyAccount getmyaccount(){
        MyDatabase myDatabase = Room.databaseBuilder(TransferActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        MyAccount myAccounts =  myDatabase.dao().getMyAccount().get(0);
       return myAccounts;
    }
}