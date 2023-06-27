import java.io.*;

class Student implements Serializable {
    private String name;
    private int ID;
    private String grade;

    public Student(String name, int ID, String grade) {
        this.name = name;
        this.ID = ID;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getGrade() {
        return grade;
    }
}
