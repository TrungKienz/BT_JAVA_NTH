import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Library {
    private Map<String, Semaphore> bookSemaphores;

    public Library() {
        bookSemaphores = new HashMap<>();
    }

    public void addBook(String bookTitle, int maxInstances) {
        Semaphore bookSemaphore = new Semaphore(maxInstances);
        bookSemaphores.put(bookTitle, bookSemaphore);
    }

    public void borrowBook(String readerName, String bookTitle) {
        Semaphore bookSemaphore = bookSemaphores.get(bookTitle);
        if (bookSemaphore == null) {
            System.out.println("Book not found: " + bookTitle);
            return;
        }

        try {
            bookSemaphore.acquire();
            System.out.println(readerName + " has borrowed book: " + bookTitle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(String readerName, String bookTitle) {
        Semaphore bookSemaphore = bookSemaphores.get(bookTitle);
        if (bookSemaphore == null) {
            System.out.println("Book not found: " + bookTitle);
            return;
        }

        System.out.println(readerName + " has returned book: " + bookTitle);
        bookSemaphore.release();
    }
}
