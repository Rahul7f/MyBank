package com.rsin.mybank.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BankData.class, MyAccount.class,PaymentRecord.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao dao();
}
