package com.rsin.mybank.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "PaymentRecord")
public class PaymentRecord {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "Bank")
    String bank;
    @ColumnInfo(name = "amount")
    String amount;
    @ColumnInfo(name = "date_time")
    Long time;

    public PaymentRecord(String name, String bank, String amount, Long time) {
        this.name = name;
        this.bank = bank;
        this.amount = amount;
        this.time = time;
    }

    public PaymentRecord(int id, String name, String bank, String amount, Long time) {
        this.id = id;
        this.name = name;
        this.bank = bank;
        this.amount = amount;
        this.time = time;
    }

    public PaymentRecord() {
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
