import java.util.*;
import java.io.*;

// Main class
public class StudentManagementSystem {

    // Inner Student class
    static class Student implements Serializable {
        private String name;
        private String rollNumber;
        private String grade;
        private int age;

        public Student(String name, String rollNumber, String grade, int age) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.age = age;
        }

        public String getName() { return name; }
        public String getRollNumber() { return rollNumber; }
        public String getGrade() { return grade; }
        public int getAge() { return age; }

        public void setName(String name) { this.name = name; }
        public void setGrade(String grade) { this.grade = grade; }
        public void setAge(int age) { this.age = age; }

        public String toString() {
            return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade + ", Age: " + age;
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadFromFile();
        int choice;

        do {
            showMenu();
            choice = getInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> searchStudent();
                case 4 -> displayAllStudents();
                case 5 -> removeStudent();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    static void showMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Remove Student");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }

    static void addStudent() {
        System.out.println("\n--- Add Student ---");
        String name = getString("Enter Name: ");
        String roll = getString("Enter Roll Number: ");
        String grade = getString("Enter Grade: ");
        int age = getInt("Enter Age: ");

        if (findStudent(roll) != null) {
            System.out.println("Student with this roll number already exists.");
            return;
        }

        students.add(new Student(name, roll, grade, age));
        saveToFile();
        System.out.println("Student added successfully.");
    }

    static void editStudent() {
        System.out.println("\n--- Edit Student ---");
        String roll = getString("Enter Roll Number: ");
        Student s = findStudent(roll);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current Info: " + s);
        String name = getOptional("Enter New Name (leave blank to keep): ");
        String grade = getOptional("Enter New Grade (leave blank to keep): ");
        String ageStr = getOptional("Enter New Age (leave blank to keep): ");

        if (!name.isEmpty()) s.setName(name);
        if (!grade.isEmpty()) s.setGrade(grade);
        if (!ageStr.isEmpty()) s.setAge(Integer.parseInt(ageStr));

        saveToFile();
        System.out.println("Student updated.");
    }

    static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        String roll = getString("Enter Roll Number: ");
        Student s = findStudent(roll);

        if (s != null) {
            System.out.println("Student Found: " + s);
        } else {
            System.out.println("Student not found.");
        }
    }

    static void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        String roll = getString("Enter Roll Number: ");
        Student s = findStudent(roll);

        if (s != null) {
            students.remove(s);
            saveToFile();
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Utility functions
    static Student findStudent(String roll) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) return s;
        }
        return null;
    }

    static String getString(String msg) {
        String input;
        do {
            System.out.print(msg);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) System.out.println("Input cannot be empty.");
        } while (input.isEmpty());
        return input;
    }

    static String getOptional(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    static int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
