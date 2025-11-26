package week3.LibraryManagementSystem;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: addMember(); break;
                case 4: viewMembers(); break;
                case 5: borrowBook(); break;
                case 6: returnBook(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid Choice!");
            }
        } while (choice != 0);
    }

    // ---------------- Add Book ----------------
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        books.add(new Book(id, title));
        System.out.println("Book Added Successfully!");
    }

    // ---------------- View Books ----------------
    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books Available.");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println("ID: " + b.id + " | Title: " + b.title +
                    " | Status: " + (b.isBorrowed ? "Borrowed" : "Available"));
        }
    }

    // ---------------- Add Member ----------------
    static void addMember() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();

        members.add(new Member(id, name));
        System.out.println("Member Added Successfully!");
    }

    // ---------------- View Members ----------------
    static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No Members Registered.");
            return;
        }

        System.out.println("\n--- Member List ---");
        for (Member m : members) {
            System.out.println("ID: " + m.memberId + " | Name: " + m.name);
        }
    }

    // ---------------- Borrow Book ----------------
    static void borrowBook() {
        System.out.print("Enter Book ID to Borrow: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (!b.isBorrowed) {
                    b.isBorrowed = true;
                    System.out.println("Book Borrowed Successfully!");
                } else {
                    System.out.println("Book Already Borrowed.");
                }
                return;
            }
        }

        System.out.println("Book Not Found!");
    }

    // ---------------- Return Book ----------------
    static void returnBook() {
        System.out.print("Enter Book ID to Return: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (b.isBorrowed) {
                    b.isBorrowed = false;
                    System.out.println("Book Returned Successfully!");
                } else {
                    System.out.println("Book was not borrowed.");
                }
                return;
            }
        }

        System.out.println("Book Not Found!");
    }
}
