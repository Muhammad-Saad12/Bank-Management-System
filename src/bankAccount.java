

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public abstract class bankAccount  {

    //**********  DATA MEMBERS*****************

    protected double balance;
    protected String Date;
    private String[] record=new String[1000];
    protected String[] TaxRecord= new String[1000];
    protected double tax;
    static protected int countAcc;
    protected String lastTransaction;
    protected int counter;


    //**********GETTERS & SETTERS***************

    public double getBalance() {
        return balance;
    }
    public String getDate() {
        return Date;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    // *************CONSTRUCTOR********************

    public bankAccount(int AccNumber,double Balance,String name,String address,String ph,String ID )
    {
        obj.setAccNumber(AccNumber);
        this.balance=Balance;
        obj.setName(name);
        obj.setAddress(address);
        obj.setPhNumber(ph);
        obj.setID(ID);


    }
    public bankAccount() {
    }



    // *************METHODS************************

    customer obj=new customer();
    protected double checkBalance()
    {
       return getBalance();

    }
    protected void makeWithdrawal(double Rs) {
        setBalance(getBalance()-Rs);
        record("Withdrawal money "+String.valueOf(Rs) + " on "+time());
    }
    protected void makeDeposit(double RS)
    {
        this.balance=this.balance+RS;
        record("Deposit money "+String.valueOf(RS) + " on "+time());

    }

    protected void printStatement(){

                int i;
                for(i=0;i<1000;i++)
                {
                    if(record[i]!=null)
                    {
                        System.out.println(record[i]);

                    }
                }
                System.out.println("Balance : "+getBalance());
                

    }

    protected void record(String Record)
    {
                boolean check=false;
                while(!check)
                {
                    int i;
                    for(i=0;i<1000;i++)
                    {
                        if(record[i]==null)
                        {
                            record[i]=Record;
                            check=true;
                            break;
                        }
                    }
                }
    }
    //over ride in saving account
    protected void InterestRate(){};
    protected void TaxRecord(String Record)
    {
        boolean check=false;
        while(!check)
        {
            int i;
            for(i=0;i<1000;i++)
            {
                if(record[i]==null)
                {
                    record[i]=Record;
                    check=true;
                    break;
                }
            }
        }
    }

    protected void TransferReceive(double RS,int Sender){
        setBalance(getBalance()+RS);
        record("TRANSFER AMOUNT RECEIVE FROM ACCOUNT"+Sender+ " TOTAL OF RS " +String.valueOf(RS) + " on "+time());
    }
    protected void TransferSend(double RS,int Receiver){
        setBalance(getBalance()-RS);
        record("TRANSFER AMOUNT SEND TO ACCOUNT  "+Receiver+ " TOTAL OF RS " +String.valueOf(RS) + " on "+time());

    }
    //*************************************
    //******** ABSTRACT METHODS ***********
    //*************************************

    protected abstract void details();
    protected abstract void taxes();
    protected abstract void taxDetails();

    //*************************************
    //****METHODS THAT USE FREQUENTLY *****
    //*************************************

    protected String time(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }
    protected void Display2(){
        System.out.println("PRESS 1: FOR DEPOSIT\nPRESS 2: FOR WITHDRAWAL");
        System.out.println("PRESS 3: CHECK BALANCE\nPRESS 4: PRINT STATEMENT");
        System.out.println("PRESS 5: FOR TRANSFER AMOUNT\nPRESS 6: EXIT MENU");
    }
    protected int monthDifference()
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
        LocalDateTime Past=LocalDateTime.parse(lastTransaction,myFormatObj);
        LocalDateTime x=LocalDateTime.now();
        LocalDateTime now=LocalDateTime.parse(x.format(myFormatObj),myFormatObj);
        return (int) ChronoUnit.MONTHS.between(Past, now);
    }
    protected int yearDifference()
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
        LocalDateTime Past=LocalDateTime.parse(lastTransaction,myFormatObj);
        LocalDateTime x=LocalDateTime.now();
        LocalDateTime now=LocalDateTime.parse(x.format(myFormatObj),myFormatObj);
        return (int) ChronoUnit.YEARS.between(Past,now);

    }
}

