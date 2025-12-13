package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private FileHandler fileHandler;

    public Library() {
        fileHandler = new FileHandler();
        books = fileHandler.loadBooks();
        members = fileHandler.loadMembers();
    }

    public void addBook(Book book) {
        books.add(book);
        fileHandler.saveBooks(books);
        System.out.println("Book added successfully!");
    }

    public void registerMember(Member member) {
        members.add(member);
        fileHandler.saveMembers(members);
        System.out.println("Member registered successfully!");
    }

    public Book findBook(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public Member findMember(String id) {
        return members.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void borrowBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null || !book.isAvailable()) {
            System.out.println("Borrowing failed.");
            return;
        }

        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        book.setDueDate(LocalDate.now().plusWeeks(2));
        member.borrowBook(isbn);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book borrowed. Due date: " + book.getDueDate());
    }

    public void displayBooks() {
        books.forEach(System.out::println);
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayStatistics() {
        long available = books.stream().filter(Book::isAvailable).count();
        long overdue = books.stream().filter(Book::isOverdue).count();

        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + available);
        System.out.println("Borrowed Books: " + (books.size() - available));
        System.out.println("Members: " + members.size());
        System.out.println("Overdue Books: " + overdue);
    }
}
