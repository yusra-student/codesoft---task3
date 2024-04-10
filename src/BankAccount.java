import java.util.Scanner;

class ATM {

    private final BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("Welcome to ATM!");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                withdrawCash();
                break;
            case 2:
                depositCash();
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Thank you for using ATM. Goodbye!");
                System.exit(0); // Terminate program on exit
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getValidAmountInput();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Please collect your cash.");
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    private void depositCash() {
        System.out.print("Enter amount to deposit: ");
        double amount = getValidAmountInput();
        account.deposit(amount);
        System.out.println("Deposit successful. Your new balance is: " + account.getBalance());
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    private double getValidAmountInput() {
        Scanner scanner = new Scanner(System.in);
        double amount;
        do {
            amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.print("Invalid amount. Please enter a positive value: ");
            }
        } while (amount <= 0);
        return amount;
    }
}

public class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
class ATMMain {

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Set initial balance
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            int choice = new Scanner(System.in).nextInt();
            atm.handleUserChoice(choice);
        }
    };
}

