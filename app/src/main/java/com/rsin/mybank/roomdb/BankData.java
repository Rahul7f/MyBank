package com.rsin.mybank.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BankData")
public class BankData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "account_no")
    String account_no;
    @ColumnInfo(name = "Bank")
    String bank;
    @ColumnInfo(name = "IFSC")
    String IFSC;
    @ColumnInfo(name = "amount")
    String amount;

    public BankData(String name, String email, String account_no, String bank, String IFSC, String amount) {
        this.name = name;
        this.email = email;
        this.account_no = account_no;
        this.bank = bank;
        this.IFSC = IFSC;
        this.amount = amount;
    }

    public BankData(int id, String name, String email, String account_no, String bank, String IFSC, String amount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.account_no = account_no;
        this.bank = bank;
        this.IFSC = IFSC;
        this.amount = amount;
    }

    public BankData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
