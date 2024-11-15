package class_2;
import java.util.Scanner;

class Student {
    String name;
    int rollnumber;
    String course;
    float grade;

    Student(String name, int rollnumber, String course, float grade) {
        this.name = name;
        this.rollnumber = rollnumber;
        this.course = course;
        this.grade = grade;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollnumber);
        System.out.println("Course: " + course);
        System.out.println("Grade: " + grade);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student Information System");
        System.out.println("1. Add Student");
        System.out.println("2. Display Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. EXIT");

        Student[] students = new Student[100];
        int studentCount = 0;

        while (true) {
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1:
                    addStudent(scanner, students, studentCount);
                    studentCount++;
                    break;
                case 2:
                    displayStudentInfo(students, studentCount);
                    break;
                case 3:
                    updateStudentInfo(scanner, students, studentCount);
                    break;
                case 4:
                    studentCount = deleteStudent(students, studentCount, scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void addStudent(Scanner scanner, Student[] students, int studentCount) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        int rollnumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter course: ");
        String course = scanner.nextLine();
        System.out.print("Enter grade: ");
        float grade = scanner.nextFloat();

        students[studentCount] = new Student(name, rollnumber, course, grade);
        System.out.println("Student added successfully!");
    }

    static void displayStudentInfo(Student[] students, int studentCount) {
    for (int i = 0; i < studentCount; i++) {
            System.out.println("Student " + (i + 1) + ":");
         students[i].display();
           System.out.println();
      }
    }

    static void updateStudentInfo(Scanner scanner, Student[] students, int studentCount) {
        System.out.print("Enter roll number to update: ");
        int rollnumber;
        
        // Validate roll number input
        try {
            rollnumber = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a numeric roll number.");
            scanner.nextLine(); // Clear buffer
            return; // Exit the method
        }

        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollnumber == rollnumber) {
                System.out.println("Found student: " + students[i].name);
                System.out.print("Are you sure you want to update this student's information? (yes/no): ");
                String confirmation = scanner.nextLine();
                
                if (confirmation.equalsIgnoreCase("yes")) {
                    System.out.print("Enter a new name (or press Enter to keep current): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        students[i].name = newName;
                    }

                    System.out.print(
                    		 "Enter a new course (or press Enter to keep current): ");
                    String newCourse = scanner.nextLine();
                    if (!newCourse.isEmpty()) {
                        students[i].course = newCourse;
                    }

                    System.out.print("Enter a new grade (or press Enter to keep current): ");
                    String gradeInput = scanner.nextLine();
                    if (!gradeInput.isEmpty()) {
                        try {
                            float newGrade = Float.parseFloat(gradeInput);
                            students[i].grade = newGrade;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid grade entered! Keeping the current grade.");
                        }
                    }

                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("Update canceled.");
                }
                return;
            }
        }
        System.out.println("Student not found! Please enter a valid roll number.");
    }


    static int deleteStudent(Student[] students, int studentCount, Scanner scanner) {
        System.out.print("Enter roll number to delete student: ");
        int rollnumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollnumber == rollnumber) {
                // Shift remaining students
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[studentCount - 1] = null; // Clear last element
                System.out.println("Student deleted successfully!");
                return studentCount - 1; // Decrement count
            }
        }
        System.out.println("Student not found! Please enter a valid roll number.");
        return studentCount; // No change in count
    }
}
