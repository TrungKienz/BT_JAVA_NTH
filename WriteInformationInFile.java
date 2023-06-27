import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteInformationInFile {

    public static void main(String[] args) {
        String[] students = {"Name: Hanh", "MSSV: 20198295", "phuong: Thanh Nhan", "tp: Ha Noi"};

        try {
            FileWriter writer = new FileWriter("student_class.txt");
            // Write each student name to the file
            for (String student : students) {
                writer.write(student + "\n");
            }
            // Close the writer
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        // Create a Scanner object to read from the file
        try {
            File file = new File("student_class.txt");
            Scanner scanner = new Scanner(file);
            // Read each line of the file and print it
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            // Close the scanner
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}