package client ;
import interfaces.BankInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyClientThread extends Thread {
    private int accountNum;
    private double amount;
    private long session;

    public MyClientThread(int accountNum, double amount, long session) {
        this.accountNum = accountNum;
        this.amount = amount;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankInterface bank = (BankInterface) registry.lookup("Bank");

            double balance = bank.withdraw(accountNum, amount, session);
            System.out.println(Thread.currentThread().getName() + " withdrew Rs. " + amount + ", new balance: " + balance);

        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName() + " encountered error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java client.MyClientThread <accountNum> <amount> <sessionID>");
            return;
        }

        int accountNum = Integer.parseInt(args[0]);
        double amount = Double.parseDouble(args[1]);
        long session = Long.parseLong(args[2]);

        MyClientThread t1 = new MyClientThread(accountNum, amount, session);
        MyClientThread t2 = new MyClientThread(accountNum, amount, session);
        MyClientThread t3 = new MyClientThread(accountNum, amount, session);

        t1.start();
        t2.start();
        t3.start();
    }
}
