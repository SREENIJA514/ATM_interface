public class Main {
    public static void main(String[] args) {
        Account userAccount = new Account("1234567890", 1000.0); // Replace with actual account details
        User user = new User("user123", "1234", userAccount); // Replace with actual user details
        ATM atm = new ATM(user);
        atm.displayMenu();
    }
}
