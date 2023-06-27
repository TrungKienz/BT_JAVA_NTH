import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileReadWriteExample {
    public static void main(String[] args) {
        Student student = new Student("Hanh", 20198295, "4.0");

        try {
            FileOutputStream fileOut = new FileOutputStream("student.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(student);
            objectOut.close();
            fileOut.close();
            System.out.println("Student information written to student.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileIn = new FileInputStream("student.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Student savedStudent = (Student) objectIn.readObject();
            objectIn.close();
            fileIn.close();

            System.out.println("Name: " + savedStudent.getName());
            System.out.println("Student ID: " + savedStudent.getID());
            System.out.println("Grade: " + savedStudent.getGrade());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}