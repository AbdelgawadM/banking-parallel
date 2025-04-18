package server;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L; 
    private final int accountNumber;
    private final String userName;
    private final String password;
    private double balance;
    private final List<Transaction> transactions;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.accountNumber = (int)(Math.random() * 1_0000_0000); // random 8‑digit
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }
}
