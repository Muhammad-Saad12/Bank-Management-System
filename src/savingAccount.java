

import java.util.Scanner;

public class savingAccount extends bankAccount{

    private static float it=2;
    //************ CONSTRUCTOR *******************
    public savingAccount(int AccNumber, double Balance, String name,String address,String date,String ph, String id) {
        super(AccNumber, Balance, name,address,ph,id);
        Date=date;

    }
    public savingAccount()
    {
    }

    @Override
    protected void makeWithdrawal(double Rs) {
        boolean check= true;
        if(getBalance()>=Rs) {
            System.out.println(getBalance());
            super.makeWithdrawal(Rs);
            System.out.println("CASH WITHDRAWAL SUCCESSFULLY FROM SAVING ACCOUNT ");
            check=false;
        }
        if(check)
            System.out.println("YOU DON'T HAVE ENOUGH BALANCE");
    }


    @Override
    protected void makeDeposit(double RS ) {
        System.out.println("YOU HAVE SUCCESSFULLY DEPOSIT INTO YOU SAVING ACCOUNT");
        super.makeDeposit(RS);
    }


    //************************************
    //************  METHODS  *************
    //************************************


    Scanner sc=new Scanner(System.in);
    public void openAcc()
    {
        System.out.println("ENTER UNIQUE ID CARD NUMBER WITHOUT SLASH '-' ");
        String ID= sc.next();
        boolean flag=true;
        for (bankAccount s : checkingAccount.accounts) {
            if(s instanceof savingAccount)
            {if(s.obj.getID().equals(ID))
                flag=false;}
        }
        if(flag) {
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
            bankAccount checking = new savingAccount(acc, Rs, name, add, time(), ph, ID);
            checkingAccount.accounts.add(checking);
            System.out.println("ACCOUNT CREATED SUCCESSFULLY");
        }
        else
            System.out.println("YOUR SAVING ACCOUNT ALREADY EXIST");

    }




    public void login()
    {

        System.out.println("ENTER THE ACCOUNT NUMBER: ");
        int acc = sc.nextInt();
        boolean check= true;
        for (bankAccount s : checkingAccount.accounts) {

            if (acc==(s.obj.getAccNumber())) {
                check = false;
                int check1 = 0;
                while (check1 != 6) {
                    System.out.println("***** ACCOUNT DETAILS **** \nNAME: " + s.obj.getName() + " PHONE NUMBER: " + s.obj.getPhNumber());
                    System.out.println("PRESS 0: FOR ZAKAT CALCULATION");
                    Display2();
                    System.out.println("PRESS 7: FOR CALCULATE INTEREST");
                    check1 = sc.nextInt();
                    switch (check1) {
                        case 0:{
                            s.taxes();
                            break;
                        }
                        case 1: {
                            System.out.println("ENTER AMOUNT: ");
                            s.makeDeposit(sc.nextDouble());
                            break;
                        }

                        case 2: {
                            System.out.println("ENTER THE AMOUNT");
                            s.makeWithdrawal(sc.nextDouble());
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
                            if(s.getBalance()>RS) {
                                for (bankAccount R : checkingAccount.accounts) {
                                    if (acc2 == (R.obj.getAccNumber())) {
                                        R.TransferReceive(RS, s.obj.getAccNumber());
                                        s.TransferSend(RS, R.obj.getAccNumber());
                                        System.out.println("OPERATION SUCCESSFULL");
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag)
                                    System.out.println("RECEIVER ACCOUNT NOT FOUND");
                            }
                            else
                                System.out.println("YOU DON'T HAVE ENOUGH BALANCE");
                            break;
                        }
                        case 6: {
                            check1 = 6;
                            System.out.println("******* PREVIOUS MENU *********");
                            break;
                        }
                        case 7:{
                            s.InterestRate();
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
        for (bankAccount s : checkingAccount.accounts) {
        	if(s instanceof savingAccount)
            if (acc==(s.obj.getAccNumber())) {
                System.out.println("ACCOUNT DELETED SUCCESSFULLY");
                checkingAccount.accounts.remove(s);
                check=false;
                break;
            }
        }
        if(check)
            System.out.println("NO SUCH SAVING ACCOUNT ACCOUNT NOT FOUND");
    }



    public void details(){
        for (bankAccount s : checkingAccount.accounts) {
            if(s instanceof savingAccount)
                System.out.println("From: "+s.getDate()+" Account Number: "+s.obj.getAccNumber()+" Customer name : "+s.obj.getName()+" Address : "+s.obj.getAddress()+" Balance : "+s.getBalance()+
                        " Phone number : " +s.obj.getPhNumber()+" ID: "+s.obj.getID())  ;
        }

    }


    public void taxDetails(){
        for (bankAccount s : checkingAccount.accounts) {
            if(s instanceof savingAccount)
                if (s.tax!=0)
                System.out.println("Customer name : "+s.obj.getName()+" ID: "+s.obj.getID()+ " Tax Deducted  :" +s.tax)  ;
        }
    }



    public void taxes()
    {

                if(getBalance()>=20000) {
                    tax = ((getBalance() * 2.5) / 100);
                    setBalance(getBalance()-tax);
                    System.out.println("ZAKAT TAX DEDUCTED TOTAL OF "+ tax);
                    TaxRecord("RS "+ String.valueOf(tax)+" ZAKAT DEDUCTED FROM ACCOUNT"+String.valueOf(obj.getAccNumber()));

                }
                else
                    System.out.println("NOT APPLICABLE FOR ZAKAT");


    }
    
   


    public void SpecifyInterest()
    {
        boolean flag=true;
        while (flag) {
            System.out.println("DEFAULT INTEREST RATE IS " + it+"\nDO YOU WANT TO CHANGE IT?\nPRESS 1: TO CONTINUE" +
                    "\nPRESS 2 TO CHANGE:                *NOTE:CHANGE WILL REFLECT FOR ALL ACCOUNTS ");
            int check = sc.nextInt();
            switch (check){
                case 1:{
                    System.out.println("INTEREST RATE IS "+it );
                    flag=false;
                    break;
                }
                case 2:{
                    System.out.println("SPECIFY NEW INTEREST RATE: ");
                        it=sc.nextInt();
                        System.out.println("INTEREST RETE: "+it);
                        flag=false;
                        break;
                }
                default:{
                    System.out.println("WRONG INPUT ");break;
                }
    }
        }
    }



    @Override
    public void InterestRate() {
        double interest=(getBalance()*it)/100;
        boolean flag1=true;
        while(flag1){
        System.out.println("TOTAL INTEREST IS : "+interest+"" +
                "\nPRESS 1: TO DEPOSIT\nPRESS 2: TO CONTINUE : ");
        int check=sc.nextInt();
        switch (check){
            case 1:{
                setBalance(getBalance()+interest);
                System.out.println("OPERATION SUCCESSFULL");flag1=false;
                break;
            }
            case 2:
                {
                    System.out.println("============================");flag1=false;
                    break;
                }
            default:{
                System.out.println("WRONG INPUT");
                break;
            }
        }
        }

    }


}
