

import java.util.ArrayList;

import java.util.Scanner;



public class Main {
	

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int check2;
        while (true){
        System.out.println("PRESS 1: CHECKING ACCOUNT MENU \nPRESS 2: SAVING ACCOUNT MENU \n"+
                "PRESS 3: ALL ACCOUNT DETAILS\nPRESS 4: DISPLAY AMOUNT DEDUCTION\nPRESS 5: EXIT:  ");
        check2= sc.nextInt();

            switch (check2) {
                case 1: {
                    checkingAccount obj=new checkingAccount() ;
                    int check1 =0;
                    while (check1!=4) {
                    display();
                    check1 = sc.nextInt();
                    switch (check1) {
                        case 1: {
                            obj.openAcc();
                            break;
                        }
                        case 2:{
                            obj.closeAcc();
                            break;
                        }
                        case 3: {
                            obj.login();
                            break;
                        }
                        case 4:{
                            System.out.println("**** MAIN MENU  ****");
                            check1=4;
                            break;
                        }
                        default:{
                            System.out.println("WRONG INPUT");
                            break;
                        }
                     }
                  }
                    break;   }
                case 2: {
                    int check1 =0;
                    while (check1!=4) {
                    display();
                        System.out.println("PRESS 5: SPECIFY INTEREST RATE FOR ALL SAVING ACCOUNTS ");
                        check1 = sc.nextInt();
                        savingAccount obj2=new savingAccount();
                        switch (check1) {
                            case 1: {
                                obj2.openAcc();
                                break;
                            }
                            case 2:{
                                obj2.closeAcc();
                                break;
                            }
                            case 3: {
                                obj2.login();
                                break;
                            }
                            case 4:{
                                System.out.println("**** MAIN MENU  ****");
                                check1=4;
                                break;
                            }
                            case 5:{
                                obj2.SpecifyInterest();
                                break;
                            }
                            default:{
                                System.out.println("WRONG INPUT");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3:{
                    System.out.println("***********************" +
                                     "\n***CHECKING ACCOUNTS***\n");
                    bankAccount obj1=new checkingAccount();
                    obj1.details();
                    System.out.println("***********************" +
                                     "\n***SAVING  ACCOUNTS***\n");
                    bankAccount obj2=new savingAccount();
                    obj2.details();

                    break;

                }
                case 4:{
                    int check=0;
                    while(check!=3) {
                        System.out.println("PRESS 1: DISPLAY ZAKAT DEDUCTION" +
                                           "\nPRESS 2: DISPLAY TRANSACTION TAX " +
                                           "\nPRESS 3: DISPLAY BACK MENU : ");
                        check = sc.nextInt();
                        switch (check){
                            case 1: {
                                System.out.println("***********************" +
                                        "\n****  ZAKAT TAX  ******  ");
                                bankAccount obj = new savingAccount();
                                obj.taxDetails();
                                break;
                            }
                            case 2: {
                                System.out.println("***********************" +
                                        "\n****  TRANSACTION TAX  ******  ");
                                bankAccount obj1 = new checkingAccount();
                                obj1.taxDetails();
                                break;
                            }
                            case 3:{
                                System.out.println("****** MENU ********");
                                break;
                            }
                            default:{
                                System.out.println("WRONG INPUT");break;
                            }
                    }

                    }
                    break;
                }
                case 5:{
                    System.out.println("****** THANK YOU ***********");
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("WRONG INPUT");
                    break;
                }
            }
        }

    }
    private static void display(){
        System.out.println("PRESS 1: OPEN A NEW ACCOUNT");
        System.out.println("PRESS 2: CLOSE A ACCOUNT");
        System.out.println("PRESS 3: LOGIN BY ACCOUNT NUMBER FOR TRANSACTION AND FOR OTHER OPERATIONS");
        System.out.println("Press 4: MAIN MENU");
    }
}



