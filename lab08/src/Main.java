public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Додавання книг
        Book book1 = new Book("978-1-111", "Java Programming", "John Smith", 2020);
        Book book2 = new Book("978-1-222", "Data Structures", "Jane Doe", 2019);
        Book book3 = new Book("978-1-333", "Algorithms", "John Smith", 2021);
        Book book4 = new Book("978-1-444", "Design Patterns", "Bob Johnson", 2018);
        Book book5 = new Book("978-1-555", "Clean Code", "John Smith", 2022);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Спроба додати дублікат (не додасться через HashSet)
        System.out.println("Додаємо дублікат: " +
                library.addBook(new Book("978-1-111", "Duplicate", "Someone", 2023)));

        // Додавання читачів
        Reader reader1 = new Reader("R001", "Олександр Петренко", "alex@example.com");
        Reader reader2 = new Reader("R002", "Марія Іваненко", "maria@example.com");
        Reader reader3 = new Reader("R003", "Іван Сидоренко", "ivan@example.com");

        library.addReader(reader1);
        library.addReader(reader2);
        library.addReader(reader3);

        // Видача книг
        library.borrowBook(reader1, book1);
        library.borrowBook(reader1, book3);
        library.borrowBook(reader2, book2);

        // Виведення інформації
        System.out.println("\nКниги читача " + reader1.getName() + ":");
        for (Book book : library.getBorrowedBooks(reader1)) {
            System.out.println("  " + book);
        }

        System.out.println();
        library.printStatistics();

        // Повернення книги
        library.returnBook(reader1, book1);
        System.out.println("\nПісля повернення книги:");
        System.out.println("Книги читача " + reader1.getName() + ": " +
                library.getBorrowedBooks(reader1).size());

        // Демонстрація роботи з Map
        System.out.println("\nКількість книг John Smith: " +
                library.getBookCountForAuthor("John Smith"));
    }
}