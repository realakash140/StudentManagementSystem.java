
    import java.io.*;
import java.util.*;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveStudentsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    public void loadStudentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String rollNumber = parts[1];
                    String grade = parts[2];
                    Student student = new Student(name, rollNumber, grade);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
    }
}

public class StudentManagementSystemApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem managementSystem = new StudentManagementSystem();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Adding a new student");
            System.out.println("2. Removeing a student");
            System.out.println("3. Searching for a student");
            System.out.println("4. Display all students infromation");
            System.out.println("5. Save students to file");
            System.out.println("6. Load students from file");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the name of the student: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the roll number of the student: ");
                    String rollNumber = scanner.nextLine();
                    System.out.print("Enter the grade of the student: ");
                    String grade = scanner.nextLine();

                    Student student = new Student(name, rollNumber, grade);
                    managementSystem.addStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case "2":
                    System.out.print("Enter the roll number of the student to remove: ");
                    rollNumber = scanner.nextLine();
                    managementSystem.removeStudent(rollNumber);
                    System.out.println("Student removed successfully!");
                    break;

                case "3":
                    System.out.print("Enter the roll number of the student to search: ");
                    rollNumber = scanner.nextLine();
                    Student searchedStudent = managementSystem.searchStudent(rollNumber);
                    if (searchedStudent != null) {
                        System.out.println("Student found:");
                        System.out.println(searchedStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "4":
                    System.out.println("All students:");
                    managementSystem.displayAllStudents();
                    break;

                case "5":
                    System.out.print("Enter the filename to save students to: ");
                    String filenameSave = scanner.nextLine();
                    managementSystem.saveStudentsToFile(filenameSave);
                    System.out.println("Students saved to file successfully!");
                    break;

                case "6":
                    System.out.print("Enter the filename to load students from: ");
                    String filenameLoad = scanner.nextLine();
                    managementSystem.loadStudentsFromFile(filenameLoad);
                    System.out.println("Students loaded from file successfully!");
                    break;

                case "7":
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

