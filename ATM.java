import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private User user;
    private List<Transaction> transactionHistory;

    public ATM(User user) {
        this.user = user;
        this.transactionHistory = new ArrayList<>();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    performWithdraw(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting ATM. Have a nice day!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": $" + transaction.getAmount());
        }
    }

    private void performWithdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (amount <= user.getAccount().getBalance()) {
                user.getAccount().withdraw(amount);
                Transaction transaction = new Transaction("Withdraw", amount);
                transactionHistory.add(transaction);
                System.out.println("Withdrawal successful. Remaining balance: $" + user.getAccount().getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            user.getAccount().deposit(amount);
            Transaction transaction = new Transaction("Deposit", amount);
            transactionHistory.add(transaction);
            System.out.println("Deposit successful. New balance: $" + user.getAccount().getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }
    private Account findRecipientAccount(String recipientAccountNumber) {
        // Simulate account lookup based on the account number
        // In a real system, you would retrieve this information from a database or another data source
        // For simplicity, we are using a hard-coded account here
        if (recipientAccountNumber.equals("1234567890")) {
            return new Account("1234567890", 0.0); // Replace with actual recipient account details
        }
        
        return null; // Return null if the recipient account is not found
    }


    private void performTransfer(Scanner scanner) {
        System.out.print("Enter the recipient's account number: ");
        String recipientAccountNumber = scanner.next();
        System.out.print("Enter the amount to transfer: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (user.getAccount().getBalance() >= amount) {
                Account recipientAccount = findRecipientAccount(recipientAccountNumber);// Retrieve recipient's account based on the account number
                if (recipientAccount != null) {
                    user.getAccount().withdraw(amount);
                    recipientAccount.deposit(amount);
                    Transaction transaction = new Transaction("Transfer to " + recipientAccountNumber, amount);
                    transactionHistory.add(transaction);
                    System.out.println("Transfer successful. Remaining balance: $" + user.getAccount().getBalance());
                } else {
                    System.out.println("Recipient account not found.");
                }
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid amount.");
        }
    }
}
