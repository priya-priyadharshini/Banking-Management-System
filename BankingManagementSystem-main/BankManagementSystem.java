import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    int accountNumber;
    String name;
    int pin;
    double balance;

    BankAccount(int accountNumber, String name, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    void display() {
        System.out.println("---------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Name           : " + name);
        System.out.println("Balance        : " + balance);
    }
}

public class BankManagementSystem {

    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in)) {

            ArrayList<BankAccount> accounts = new ArrayList<>();

            int choice;

            do {

                System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Balance");
                System.out.println("5. View All Accounts");
                System.out.println("6. Transfer Money");
                System.out.println("7. Exit");

                System.out.print("Enter Choice: ");
                choice = sc.nextInt();

                switch(choice) {
                    case 1 -> {
                        System.out.print("Enter Account Number: ");
                        int accNo = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Set 4-Digit PIN: ");
                        int pin = sc.nextInt();

                        System.out.print("Enter Initial Balance: ");
                        double balance = sc.nextDouble();

                        BankAccount acc = new BankAccount(accNo, name, pin, balance);

                        accounts.add(acc);

                        System.out.println("Account Created Successfully");
                    }
                    case 2 -> {
                        System.out.print("Enter Account Number: ");
                        int depAcc = sc.nextInt();

                        boolean depositFound = false;

                        for(BankAccount a : accounts) {

                            if(a.accountNumber == depAcc) {

                                System.out.print("Enter Amount to Deposit: ");
                                double amount = sc.nextDouble();

                                a.balance += amount;

                                System.out.println("Amount Deposited Successfully");

                                depositFound = true;
                            }
                        }

                        if(!depositFound) {
                            System.out.println("Account Not Found");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Account Number: ");
                        int withAcc = sc.nextInt();

                        System.out.print("Enter PIN: ");
                        int withPin = sc.nextInt();

                        boolean withdrawFound = false;

                        for(BankAccount a : accounts) {

                            if(a.accountNumber == withAcc && a.pin == withPin) {

                                System.out.print("Enter Withdraw Amount: ");
                                double amount = sc.nextDouble();

                                if(amount <= a.balance) {

                                    a.balance -= amount;

                                    System.out.println("Please Collect Your Money");
                                }
                                else {
                                    System.out.println("Insufficient Balance");
                                }

                                withdrawFound = true;
                            }
                        }

                        if(!withdrawFound) {
                            System.out.println("Invalid Account Number or PIN");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Account Number: ");
                        int balAcc = sc.nextInt();

                        System.out.print("Enter PIN: ");
                        int balPin = sc.nextInt();

                        boolean balanceFound = false;

                        for(BankAccount a : accounts) {

                            if(a.accountNumber == balAcc && a.pin == balPin) {

                                System.out.println("Current Balance: " + a.balance);

                                balanceFound = true;
                            }
                        }

                        if(!balanceFound) {
                            System.out.println("Invalid Account Number or PIN");
                        }
                    }
                    case 5 -> {
                        if(accounts.isEmpty()) {
                            System.out.println("No Accounts Available");
                        }
                        else {

                            for(BankAccount a : accounts) {
                                a.display();
                            }
                        }
                    }
                    case 6 -> {
                        System.out.print("Enter Sender Account Number: ");
                        int sender = sc.nextInt();

                        System.out.print("Enter Receiver Account Number: ");
                        int receiver = sc.nextInt();

                        System.out.print("Enter Transfer Amount: ");
                        double transferAmount = sc.nextDouble();

                        BankAccount senderAcc = null;
                        BankAccount receiverAcc = null;

                        for(BankAccount a : accounts) {

                            if(a.accountNumber == sender) {
                                senderAcc = a;
                            }

                            if(a.accountNumber == receiver) {
                                receiverAcc = a;
                            }
                        }

                        if(senderAcc != null && receiverAcc != null) {

                            if(transferAmount <= senderAcc.balance) {

                                senderAcc.balance -= transferAmount;
                                receiverAcc.balance += transferAmount;

                                System.out.println("Money Transferred Successfully");
                            }
                            else {
                                System.out.println("Insufficient Balance");
                            }
                        }
                        else {
                            System.out.println("Invalid Account Number");
                        }
                    }
                    case 7 -> System.out.println("Thank You");
                    default -> System.out.println("Invalid Choice");
                }

            } while(choice != 7);
        }
    }
}