package com.rsin.mybank.roomdb;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface UserDao {
    //add default costumer
    @Insert
    void addCostumers(BankData student);
    @Insert
    void addMyAccount(MyAccount student);
    @Insert
    void addPayment(PaymentRecord paymentRecord);

    //get costumer list
    @Query("Select * from BankData")
    List<BankData> getCostumers();
    //get my account
    @Query("Select * from MyAccount")
    List<MyAccount> getMyAccount();

    //get payment history
    @Query("Select * from PaymentRecord")
    List<PaymentRecord> getPaymentHistory();

    //update costumer account
    @Update
    void updateCostumerBank(BankData student);
    //update own account
    @Update
    void updateMyAccount(MyAccount student);
}
