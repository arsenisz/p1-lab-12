import java.util.*;

public class Library {
    private Set<Book> books;
    private Set<Reader> readers;
    private Map<Reader, Set<Book>> borrowedBooks;
    private Map<String, Integer> bookCountByAuthor;

    public Library() {
        this.books = new HashSet<>();
        this.readers = new HashSet<>();
        this.borrowedBooks = new HashMap<>();
        this.bookCountByAuthor = new HashMap<>();
    }

    // Операції з книгами
    public boolean addBook(Book book) {
        boolean added = books.add(book);
        if (added) {
            bookCountByAuthor.put(book.getAuthor(),
                    bookCountByAuthor.getOrDefault(book.getAuthor(), 0) + 1);
        }
        return added;
    }

    public boolean removeBook(Book book) {
        boolean removed = books.remove(book);
        if (removed) {
            String author = book.getAuthor();
            int count = bookCountByAuthor.get(author);
            if (count == 1) {
                bookCountByAuthor.remove(author);
            } else {
                bookCountByAuthor.put(author, count - 1);
            }
        }
        return removed;
    }

    public boolean containsBook(Book book) {
        return books.contains(book);
    }

    public Set<Book> getAllBooks() {
        return new HashSet<>(books);
    }

    // Операції з читачами
    public boolean addReader(Reader reader) {
        return readers.add(reader);
    }

    public boolean removeReader(Reader reader) {
        borrowedBooks.remove(reader);
        return readers.remove(reader);
    }

    public Set<Reader> getAllReaders() {
        return new HashSet<>(readers);
    }

    // Операції з видачею книг
    public boolean borrowBook(Reader reader, Book book) {
        if (!readers.contains(reader) || !books.contains(book)) {
            return false;
        }

        borrowedBooks.putIfAbsent(reader, new HashSet<>());
        return borrowedBooks.get(reader).add(book);
    }

    public boolean returnBook(Reader reader, Book book) {
        Set<Book> readerBooks = borrowedBooks.get(reader);
        if (readerBooks == null) {
            return false;
        }
        return readerBooks.remove(book);
    }

    public Set<Book> getBorrowedBooks(Reader reader) {
        return borrowedBooks.getOrDefault(reader, new HashSet<>());
    }

    // Використання Map для статистики
    public Map<String, Integer> getBookCountByAuthor() {
        return new HashMap<>(bookCountByAuthor);
    }

    public int getBookCountForAuthor(String author) {
        return bookCountByAuthor.getOrDefault(author, 0);
    }

    public Set<String> getAuthorsWithMostBooks() {
        if (bookCountByAuthor.isEmpty()) {
            return new HashSet<>();
        }

        int maxCount = Collections.max(bookCountByAuthor.values());
        Set<String> topAuthors = new HashSet<>();

        for (Map.Entry<String, Integer> entry : bookCountByAuthor.entrySet()) {
            if (entry.getValue() == maxCount) {
                topAuthors.add(entry.getKey());
            }
        }

        return topAuthors;
    }

    public void printStatistics() {
        System.out.println("=== Статистика бібліотеки ===");
        System.out.println("Всього книг: " + books.size());
        System.out.println("Всього читачів: " + readers.size());
        System.out.println("\nКількість книг за авторами:");
        for (Map.Entry<String, Integer> entry : bookCountByAuthor.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\nАвтори з найбільшою кількістю книг: " + getAuthorsWithMostBooks());
    }
}