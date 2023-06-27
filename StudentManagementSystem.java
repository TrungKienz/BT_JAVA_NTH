import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private static ArrayList<Student> studentList = new ArrayList();
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

    private static boolean isUnique (int id){
        int studentAvaliable = 0;
        for (int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if (student.getId() == id){
                return false;
            }
        }
        return true;
    }

    private static void addStudent() {
        System.out.println("\n*----------------------*Add a student*----------------------*");
        
        System.out.println("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean checkUnique = isUnique(id);
        if (checkUnique == false) {
            System.out.println("ID is not unique !!, Please enter ID again!");
        } else {
            System.out.println("Name: ");
            String name = scanner.nextLine();

            System.out.println("Student GPA: ");
            double gpa = scanner.nextDouble();
            
            Student student = new Student (id, name, gpa);
            studentList.add(student);

            System.out.println("Student added!!");
        }
    }

    private static void removeStudent (){
        System.out.println("\n*----------------------*Remove a student*----------------------*");
        
        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if(student.getId() == id){
                studentList.remove(i);
                System.out.println("Student removed !!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void searchStudent(){
        System.out.println("\n*----------------------*Search a student*----------------------*");

        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if (student.getId() == id){
                System.out.println("Student record found: ");
                System.out.println(student.toString());
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void modifyStudent(){
        System.out.println("\n*----------------------*Modify a student*----------------------*");

        System.out.println("Input student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if (student.getId() == id){
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
        }
        System.out.println("Student not modified");
    }

    public static void main (String[] args) {
        boolean quit = false;

        while (!quit) {
            System.out.println("\n*----------------------*Student Management System*----------------------*");
            System.out.println("*1. Add a student                                                        *");
            System.out.println("*2. Remove a student");
            System.out.println("*3. Search for a student");
            System.out.println("*4. Modify student information");
            System.out.println("*5. Exit");

            System.out.println("Select an option (from 1 to 5): ");
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
                    searchStudent();
                    break;
                case 4:
                    modifyStudent();
                    break;
                case 5: 
                    quit = true;
                    break;
                default: 
                    System.out.println("Invalid choice, Please enter your choice !!!");
                
            }
        }
    }
}