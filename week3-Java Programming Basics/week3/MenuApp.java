package week3;

import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Say Hello");
            System.out.println("2. Add Numbers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Hello User!");
            } else if (choice == 2) {
                System.out.print("Enter two numbers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();
                System.out.println("Sum = " + (a + b));
            } else if (choice == 0) {
                System.out.println("Goodbye!");
            }

        } while (choice != 0);
    }
}

