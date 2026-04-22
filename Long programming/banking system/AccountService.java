package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class AccountService {
   ArrayList<Account> accountArray=new ArrayList<>();

    int acc=0;
    int password;
    public void createAccount(String name,BigDecimal balance) {
        Random rd=new Random();
        acc=accountArray.size()+1;
        password=rd.nextInt(9999);
//        System.out.println(accountArray.size())
        String accountId=String.valueOf(acc);
        boolean login=false;
        Account account=new Account(name,accountId,balance,password,login);
        accountArray.add((account));
        System.out.println("Your account number is:"+account.getAccontId());
        System.out.println("Your password is:"+account.getPassword());
    }

    public boolean loginIn(String acc, int password) {
        boolean flag=false;
        for(Account account:accountArray){
            if(acc.equals(account.getAccontId())){
                if(password==account.getPassword()){
                    account.setLogin(true);
                    if(account.isLogin()){
                        System.out.println("Loginned!");
                        flag=true;
                        break;
                    }
                }else{
                    System.out.println("Wrong password");
                }
            }
        }
        if(!flag){
            System.out.println("Invalid accountId :(");
        }
        return flag;

    }
    public void deposit(String acc,int amount,TransactionService transactionService){
        for(Account account:accountArray){
            if(account.getAccontId().equals(acc)){
                BigDecimal bal=account.getBalance().add(BigDecimal.valueOf(amount));
                account.setBalance(bal);
                if(!account.isLogin()){
                    break;
                }
                System.out.println("New balance:"+account.getBalance());
                LocalDateTime date=LocalDateTime.now();
                String transType="deposit";
                transaction tra=transactionService.transactionUpdate(acc,amount,date,account.getBalance(),transType);
                account.addToTransactionHistory(tra);
                break;
            }
        }
    }
    public void withdraw(String acc, int cost,TransactionService transactionService){
        for(Account account:accountArray){
            if(account.getAccontId().equals(acc)){
                if(!account.isLogin()){
                    System.out.println("Not logged :)");
                    break;
                }
                BigDecimal bal=account.getBalance().subtract(BigDecimal.valueOf(cost));
                if(bal.intValue()<0){
                    System.out.println("You don't have enough amount");
                    break;
                }
                account.setBalance(bal);
                System.out.println("New balance:"+account.getBalance());
                LocalDateTime date=LocalDateTime.now();
                String transType="withdraw";

                transaction tra=transactionService.transactionUpdate(acc,cost,date,account.getBalance(),transType);
                account.addToTransactionHistory(tra);
                break;
            }
        }
    }

    public void banktransfer(String acc,int m, String toacc, TransactionService transactionService) {
        boolean flag1=false;
        boolean flag2=false;
        for(Account account:accountArray){
            if(account.getAccontId().equals(acc)){
                flag1=true;
                break;
            }
        }
        for(Account account:accountArray){
            if(account.getAccontId().equals(toacc)){
                flag2=true;
                break;
            }
        }
        for(Account account:accountArray){
            if((!flag1)||(!flag2)){
                break;
            }else if(!flag2){
                System.out.println("Sender doesn't exits");
            }
            if(account.getAccontId().equals(acc)){
                if(!account.isLogin()){
                    System.out.println("Not logged :)");
                    break;
                }
                BigDecimal newbal=account.getBalance().subtract(BigDecimal.valueOf(m));
                if(newbal.intValue()<0){
                    System.out.println("You don't have enough amount");
                    break;
                }
                account.setBalance(newbal);
                LocalDateTime date=LocalDateTime.now();
                String transtype="Banktransfer:sending";
                int newbalInt=newbal.intValue();
                transaction tra=transactionService.transactionUpdate(acc,m,date,account.getBalance(),transtype);
                account.addToTransactionHistory(tra);
            } else if (account.getAccontId().equals(toacc)) {
                BigDecimal newbal=account.getBalance().add(BigDecimal.valueOf(m));
                account.setBalance(newbal);
                int newbalInt=newbal.intValue();
                LocalDateTime date=LocalDateTime.now();
                String transtype="Banktransfer:Receiving";
                transaction tra=transactionService.transactionUpdate(acc,m,date,account.getBalance(),transtype);
                System.out.println("Amout transfered");
            }
        }
    }

    public ArrayList<transaction> minstatement(String acc, TransactionService transactionService) {
        for(Account account:accountArray){
            if(account.getAccontId().equals(acc)){
                return account.getTransactionHistory();
            }
        }return null;
    }
}
class Account{

    private String name;
    private String accountId;
    private BigDecimal balance;
    private int password;
    private boolean login;
    private ArrayList<transaction> transactionHistory;


    public Account(String name, String accountId, BigDecimal balance, int password,boolean login) {
        this.name = name;
        this.accountId = accountId;
        this.balance = balance;
        this.password = password;
        this.login=login;
        this.transactionHistory=new ArrayList<>();

    }
    public boolean isLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", password=" + password +
                ", login=" + login +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

    public ArrayList<transaction> getTransactionHistory() {
        return transactionHistory;
    }
    public void addToTransactionHistory(transaction transactionobj){
        this.transactionHistory.add(transactionobj);

    }

    public void setTransactionHistory(ArrayList<transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccontId() {
        return accountId;
    }

    public void setAccontId(String accontId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
