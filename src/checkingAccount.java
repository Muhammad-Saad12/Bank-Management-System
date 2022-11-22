//package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Scanner;

public class checkingAccount extends bankAccount{




    //**********CONSTRUCTOR*******************
    public checkingAccount(int AccNumber, double Balance, String name,String address,String date,String ph,String id) {
        super(AccNumber, Balance, name,address,ph,id);
        Date=date;
    }
    public checkingAccount()
    {
    }

    //**************************************
    //****METHODS RUN TIME POLYMORPHISM*****
    //**************************************
    static ArrayList<bankAccount> accounts = new ArrayList<>();
    @Override
    protected void makeWithdrawal(double Rs) {
        boolean check= true;
                if((getBalance()+5000)>=Rs) {
                    super.makeWithdrawal(Rs);
                    lastTransaction=time();
                    System.out.println("CASH WITHDRAWAL SUCCESSFULLY FROM CHECKING ACCOUNT ");
                    check=false;

            }
        if(check)
            System.out.println("YOU DON'T HAVE ENOUGH BALANCE");
    }

    @Override
    protected void makeDeposit(double RS ) {
        System.out.println("YOU HAVE SUCCESSFULLY DEPOSIT INTO YOU CHECKING ACCOUNT");
        lastTransaction=time();
        super.makeDeposit(RS);


    }

    //************************************
    //************  METHODS  *************


    Scanner sc=new Scanner(System.in);
    public void openAcc()
    {
        System.out.println("ENTER UNIQUE ID CARD NUMBER WITHOUT SLASH '-' ");
        String ID= sc.next();
        boolean flag=true;
        for (bankAccount s : accounts) {
            if(s instanceof checkingAccount)
            {if(s.obj.getID().equals(ID))
                flag=false;}
        }
        if(flag){
            countAcc++;
            int acc =countAcc;
            System.out.println("****** ACCOUNT NUMBER ASSIGNED ******" +
                    "\nACC NO ASSIGNED IS  "+acc);
            System.out.println("ENTER NAME: ");
            String name = sc.next();
            System.out.println("ENTER ADDRESS :");
            String add = sc.next();
            System.out.println("ENTER PHONE :");
            String ph = sc.next();
            System.out.println("ENTER BALANCE :");
            double Rs = sc.nextDouble();

//        ****************************************
//        *******RUN TIME POLYMORPHISM************
//        ****************************************
        bankAccount checking = new checkingAccount(acc, Rs, name,add,time(),ph,ID);
        accounts.add(checking);
        System.out.println("ACCOUNT CREATED SUCCESSFULLY");
        }
        else
            System.out.println("YOUR CHECKING ACCOUNT ALREADY EXIST");
    }

    public void login()
    {

        System.out.println("ENTER THE ACCOUNT NUMBER: ");
        int acc = sc.nextInt();
        boolean check= true;
        for (bankAccount s : accounts) {

            if (acc==(s.obj.getAccNumber())) {
                check = false;
                int check1 = 0;
                while (check1 != 6) {
                    System.out.println("******* ACCOUNT DETAILS  ******** \nNAME: " + s.obj.getName() + " PHONE NUMBER: " + s.obj.getPhNumber());
                    Display2();
                    check1 = sc.nextInt();
                    switch (check1) {
                        case 1: {
                            System.out.println("ENTER AMOUNT: ");
                            double amount=sc.nextDouble();
                            if(s.lastTransaction==null)
                            {
                                System.out.println("*******FIRST TRANSACTION ********");
                                s.makeDeposit(amount);
                                s.counter++;

                            }
                            else{

                                if(s.monthDifference()==0&&s.yearDifference()==0&&s.counter<2)
                                {
                                    s.makeDeposit(amount);
                                    s.counter++;

                                }
                                else if(s.monthDifference()!=0)
                                {
                                    s.makeDeposit(amount);
                                    s.counter=1;
                                }
                                else if(s.monthDifference()==0&&s.yearDifference()==0&&s.counter>=2)
                                {
                                    System.out.println(" Rs 10 Tax is Deducted ");
                                    s.balance=s.balance-10;
                                    s.makeDeposit(amount);
                                    System.out.println("Tax is Deducted ");
                                    s.counter++;
                                    s.tax=s.tax+10;
                                }
                            }

                            break;
                        }

                        case 2: {
                            System.out.println("ENTER THE AMOUNT");
                            double amount=sc.nextDouble();
                            if(s.lastTransaction==null)
                            {
                                System.out.println("*******FIRST WITHDRAWAL ********");
                                s.makeWithdrawal(amount);
                                s.counter++;

                            }
                            else{

                                if(s.monthDifference()==0&&s.yearDifference()==0&&s.counter<2)
                            {
                                s.makeWithdrawal(amount);
                                s.counter++;

                            }
                            else if(s.monthDifference()!=0)
                            {
                                s.makeWithdrawal(amount);
                                s.counter=1;
                            }
                            else if(s.monthDifference()==0&&s.yearDifference()==0&&s.counter>=2)
                            {
                                System.out.println(" Rs 10 Tax is Deducted ");
                                s.balance=s.balance-10;
                                s.makeWithdrawal(amount);
                                s.counter++;
                                s.tax=s.tax+10;
                            }
                            }
                            break;
                        }
                        case 3: {
                            System.out.println(s.checkBalance());
                            break;
                        }
                        case 4: {
                            s.printStatement();
                            break;
                        }
                        case 5: {
                            boolean flag = true;
                            System.out.println("ENTER THE RECEIVER ACCOUNT: ");
                            int acc2 = sc.nextInt();
                            System.out.println("ENTER THE TRANSFER AMOUNT: ");
                            double RS = sc.nextDouble();
                            if(s.getBalance()+5000>RS){
                            for (bankAccount R : accounts) {
                                if (acc2==(R.obj.getAccNumber())) {
                                    R.TransferReceive(RS, s.obj.getAccNumber());
                                    s.TransferSend(RS, R.obj.getAccNumber());
                                    System.out.println("OPERATION SUCCESSFULL");
                                    flag = false;
                                    break;
                                }
                            }

                            if (flag)
                                System.out.println("RECEIVER ACCOUNT NOT FOUND");}
                            else
                                System.out.println("YOU DON'T HAVE ENOUGH BALANCE");
                            break;
                        }
                        case 6: {
                            check1 = 6;
                            System.out.println("******* PREVIOUS MENU *********");
                            break;
                        }

                        default: {
                            System.out.println("WRONG INPUT ");
                            break;
                        }
                    }
                }
            }
    }if(check)
      System.out.println("ACCOUNT NOT FOUND");
    }


    public void closeAcc() {
        boolean check= true;
        System.out.println("ENTER THE ACC NUMBER : ");
        int acc = sc.nextInt();
        for (bankAccount s : accounts) {
        	if(s instanceof checkingAccount)
            if (acc==(s.obj.getAccNumber())) {
                accounts.remove(s);
                System.out.println("ACCOUNT DELETED SUCCESSFULLY");
                check=false;
                break;
            }
        }
        if(check)
            System.out.println("NO SUCH CHECKING ACCOUNT NOT FOUND");
    }
    public void taxes(){
        for (bankAccount s : accounts) {
            if(s instanceof checkingAccount)
                if (s.tax!=0)
                    TaxRecord("RS "+ String.valueOf(tax)+" TRANSACTION DEDUCTED FROM ACCOUNT"+String.valueOf(obj.getAccNumber()));
        }
    }

    @Override
    protected void taxDetails() {
        for (bankAccount s : accounts) {
            if(s instanceof checkingAccount)
                if (s.tax!=0)
                    System.out.println("Customer name : "+s.obj.getName()+" ID: "+s.obj.getID()+ " Tax Deducted :" +s.tax)  ;
        }

    }

    public void details(){
        for (bankAccount s : accounts) {
            if(s instanceof checkingAccount)
                        System.out.println("From: "+s.getDate()+" Account Number : "+s.obj.getAccNumber()+" Customer name : "+s.obj.getName()+" Address : "+s.obj.getAddress()+" Balance : "+s.getBalance()+
                                " Phone number : " +s.obj.getPhNumber()+ " ID: "+ s.obj.getID());
                   }

    }

}
