public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("Advanced Programming", 2);
        library.addBook("Data Structure and Algorithms", 1);

        Thread reader1 = new Thread(() -> {
            library.borrowBook("Nguyen Thi Hanh", "Advanced Programming");
            // Perform other operations
            library.returnBook("Nguyen Thi Hanh", "Advanced Programming");
        });

        Thread reader2 = new Thread(() -> {
            library.borrowBook("Bui Trung Kien", "Data Structure and Algorithms");
            // Perform other operations
            library.returnBook("Bui Trung Kien", "Data Structure and Algorithms");
        });

        Thread reader3 = new Thread(() -> {
            library.borrowBook("Nguyen Huong Giang", "Data Structure and Algorithms");
            // Perform other operations
            library.returnBook("Nguyen Huong Giang", "Data Structure and Algorithms");
        });

        reader1.start();
        reader2.start();
        reader3.start();
    }
}
