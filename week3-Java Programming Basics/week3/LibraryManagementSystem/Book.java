package week3.LibraryManagementSystem;

class Book {
    int id;
    String title;
    boolean isBorrowed;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }
}
