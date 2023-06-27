import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentManagementSystemv2 {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static HashMap<Integer, Student> studentIdMap = new HashMap<>();
    private static HashMap<String, ArrayList<Student>> studentNameMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    static class Student {
        private int id;
        private String name;
        private double gpa;

        public Student(int id, String name, double gpa){
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        public int getId(){
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa){
            this.gpa = gpa;
        }

        public String toString(){
            return "ID: " + id + "\nName: " + name + "\nGPA: " + gpa;
        }
    }

    private static boolean isUnique (int id) {
        Student student = studentIdMap.get(id);
        if (student == null){
            return true;
        } else {
            return false;
        }
    }

    private static void addStudent() {
        System.out.println("\n*----------------------*Add a student*----------------------*");
        
        System.out.println("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean checkUnique = isUnique(id);
        if (checkUnique == false){
            System.out.println("ID is not unique !!, Please enter ID again!");
        } else {
            System.out.println("Name: ");
            String name = scanner.nextLine();

            System.out.println("Student GPA: ");
            double gpa = scanner.nextDouble();
            
            Student student = new Student (id, name, gpa);
            studentList.add(student);
            studentIdMap.put(id, student);
            
            ArrayList<Student> studentsByName = studentNameMap.getOrDefault(name, new ArrayList<>());
            studentsByName.add(student);
            studentNameMap.put(name, studentsByName);

            System.out.println("Student added!!");
        }
    }

    private static void removeStudent (){
        System.out.println("\n*----------------------*Remove a student*----------------------*");
        
        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentIdMap.get(id);
        if (student != null) {
            studentList.remove(student);
            studentIdMap.remove(id);
            ArrayList<Student> studentsByName = studentNameMap.get(student.getName());
            studentsByName.remove(student);
            System.out.println("Student removed!!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentById(){
        System.out.println("\n*----------------------*Search a student by ID*----------------------*");

        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentIdMap.get(id);
        if (student != null) {
            System.out.println("Student record found: ");
            System.out.println(student.toString());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByName(){
        System.out.println("\n*----------------------*Search a student by name*----------------------*");

        System.out.println("Input student name: ");
        String name = scanner.nextLine();

        ArrayList<Student> studentsByName = studentNameMap.get(name);
        if (studentsByName != null){
            System.out.println("Student record found: ");
            System.out.println(studentsByName.toString());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void modifyStudentByID(){
        System.out.println("\n*----------------------*Modify a student*----------------------*");

        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentIdMap.get(id);
        if (student != null){
            System.out.println("Input new student name: ");
            String name =  scanner.nextLine();
            if (!name.isBlank()){
                student.setName(name);
            }
            System.out.println("Input new student GPA: ");
            String gpaInput = scanner.nextLine();
            if (gpaInput.isBlank()) {
                double gpa = Double.parseDouble(gpaInput);
                student.setGpa(gpa);
            }
            System.out.println("Student modified!");
            return;
        }
        System.out.println("Student not modified");
    }

public static void main (String[] args) {
        boolean quit = false;

        while (!quit) {
            System.out.println("\n*----------------------*Student Management System*----------------------*");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by Name");
            System.out.println("5. Modify student information");
            System.out.println("6. Exit");

            System.out.println("Select an option (from 1 to 6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3: 
                    searchStudentById();
                    break;
                case 4:
                    searchStudentByName();
                    break;
                case 5:
                    modifyStudentByID();
                    break;
                case 6: 
                    quit = true;
                    break;
                default: 
                    System.out.println("Invalid choice, Please enter your choice !!!");
                
            }
        }
    }
}