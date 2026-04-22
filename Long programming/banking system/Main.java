package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        AccountService accountService=new AccountService();
        TransactionService transactionService=new TransactionService();
        while(true){
        System.out.println("1.Create Account");
        System.out.println("2.Login to Account");
        int option=sc.nextInt();


        BigDecimal balance= BigDecimal.valueOf(2000.0);
        switch (option){
            case 1:
                System.out.println("Enter the name:");
                sc.nextLine();
                String name=sc.nextLine();
                accountService.createAccount(name,balance);
                break;
            case 2:
                System.out.println("Enter AccountId:");
                sc.nextLine();
                String acc=sc.nextLine();
                System.out.println("Enter password:");
                int password=sc.nextInt();
                boolean flag=accountService.loginIn(acc,password);
                if (!flag) {
                    break;
                }while(true){
                System.out.println("Enter any opration from below");
                System.out.println("1.Check balance");
                System.out.println("2.To deposit");
                System.out.println("3.To withdraw");
                System.out.println("4.Bank transfer");
                System.out.println("5.View mini statement");
                System.out.println("6.Exit");
                int choice=sc.nextInt();
                if(choice==6){
                    break;
                }
                switch (choice){
                    case 1:
                        for(Account account: accountService.accountArray){

                            if(account.getAccontId().equals(acc)){
                                if(!account.isLogin()){
                                    break;
                                }
                                System.out.println(account.getBalance());

                            }
                        }break;

                    case 2:
                        System.out.println("Enter the amount to deposit:");
                        int amount=sc.nextInt();
                        accountService.deposit(acc,amount,transactionService);
                        break;
                    case 3:
                        System.out.println("Enter the amount to withdraw:");
                        int cost=sc.nextInt();
                        accountService.withdraw(acc,cost,transactionService);
                        break;
                    case 4:
                        System.out.println("Enter the accountId to send money");
                        sc.nextLine();
                        String toacc=sc.nextLine();
                        System.out.println("Enter the amount to transfer");
                        int m=sc.nextInt();
                        accountService.banktransfer(acc,m,toacc,transactionService);
                        break;
                    case 5:
                        ArrayList statement= accountService.minstatement(acc,transactionService);
                        String string = statement.toString();
                        System.out.println(string);
                        break;
                    default:

                        System.out.println("Invalid Option ");


                }
                }
        }
        }
        }
    }
