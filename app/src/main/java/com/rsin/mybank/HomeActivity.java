package com.rsin.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rsin.mybank.roomdb.BankData;
import com.rsin.mybank.roomdb.MyAccount;
import com.rsin.mybank.roomdb.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    CardView transfer_card,history_card,my_account_card,about_bank;
    String mypref ;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transfer_card = findViewById(R.id.money_transfer_card);
        history_card = findViewById(R.id.transfer_history_card);
        my_account_card = findViewById(R.id.my_account_card);
        about_bank = findViewById(R.id.about_bank_card);

        //save pref
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
      



        // data for insert
        List<BankData> bankDataList = new ArrayList<>();
        bankDataList.add(new BankData("Rahul","dhoppe@hotmail.com","00898343","Kotak Bank","ICIC","7764"));
        bankDataList.add(new BankData("Diya Atwal","thoppe@gmail.com","79123365","Abhyudaya Co-op Bank Ltd","ABHY001","9859"));
        bankDataList.add(new BankData("Mehul Chowdhury","whuels@yahoo.com","87612603","AB Bank Ltd.","ABBL001","9845"));
        bankDataList.add(new BankData("Chandrakant Badami","vernon70@gmail.com","34764080","Allahabad Bank","ALLA001","7487"));
        bankDataList.add(new BankData("Valini Dani","hector99@koch.com","45612217","Bank Of America","BOFA001","5979"));
        bankDataList.add(new BankData("Chandra Das","nikko68@kertzmann.com","41089215","Bank of Baroda","BARB001","25342"));
        bankDataList.add(new BankData("Amitabh Grewal","ryder15@hotmail.com","07287006","Canara Bank","CNRB001","56071"));
        bankDataList.add(new BankData("Alisha Banerjee","ykohler@prosacco.com","65296110","Citibank India","CITI001","27948"));
        bankDataList.add(new BankData("Raghav Amble","gerardo.doyle@hotmail.com","31221230","DBS Bank","DBSS001","46393"));
        bankDataList.add(new BankData("Kushal Behl","gerardo.doyle@hotmail.com","66625424","Development Credit Bank","DCBL001","49461"));
        bankDataList.add(new BankData("Akhil Issac","jeremie.stiedemann@gmail.com","97479877","Federal Bank Ltd","FDRL001","684"));
        bankDataList.add(new BankData("Vivek Thaker","yfeeney@yahoo.com","94999852","Firstrand Bank Ltd","FIRN001","481"));
        bankDataList.add(new BankData("Ranjit Hora","fisher.josianne@rempel.com","33193306","HDFC Bank","HDFC001","851"));
        bankDataList.add(new BankData("Navya Baral","mcglynn@williamson.com","30786133","ICICI Bank","ICIC001","937"));
        bankDataList.add(new BankData("Haimi Chandra","zdicki@hotmail.com","61909427","IndusInd Bank Ltd.","INDB001","574"));

        //save data on condition and get pref
       String keyis=pref.getString("my_key", null);
       
         if (keyis==null)
         {
             editor.putString("my_key", "com.rsin.mybank");
             editor.apply();
             Log.d("data ","inserting.. data ");
             for (BankData bankData :bankDataList)
             {
                 addData(bankData);
             }
             myAccountAdd();
         }
         else {
             Log.d("data ","Data already exist");
         }


        // click
        transfer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountListActivity.class);
                startActivity(intent);


            }
        });
        history_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TransactionHistoryActivity.class));

            }
        });
        my_account_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyAccountActivity.class);
                intent.putExtra("accObject",getmyaccount());
                startActivity(intent);

            }
        });
        about_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutBankActivity.class));

            }
        });



    }
    void addData(BankData bankData)
    {
        BankData dataobject = new BankData(bankData.getName(),bankData.getEmail(),bankData.getAccount_no(),bankData.getBank(),bankData.getIFSC(),bankData.getAmount());
        MyDatabase myDatabase = Room.databaseBuilder(HomeActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        myDatabase.dao().addCostumers(dataobject);
    }

    void myAccountAdd()
    {
        MyAccount myAccount = new MyAccount("Rahul Singh","rahulsingh.83748@gmail.com","836472566589","Kotak Bank","KTB00055","26000");
        MyDatabase myDatabase = Room.databaseBuilder(HomeActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        myDatabase.dao().addMyAccount(myAccount);
    }

    BankData getmyaccount(){
        MyDatabase myDatabase = Room.databaseBuilder(HomeActivity.this,MyDatabase.class,"BankDC")
                .allowMainThreadQueries().build();
        MyAccount myAccounts =  myDatabase.dao().getMyAccount().get(0);
        BankData bankData = new BankData(myAccounts.getName(),myAccounts.getEmail(),myAccounts.getAccount_no(),myAccounts.getBank(),myAccounts.getIFSC(),myAccounts.getAmount());
        return bankData;
    }
}