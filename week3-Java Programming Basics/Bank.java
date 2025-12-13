package week3;
public class Bank {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();

        acc.deposit(500);
        acc.withdraw(200);
        acc.checkBalance();
    }
}
