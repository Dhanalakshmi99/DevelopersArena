package week3;
import java.util.ArrayList;
public class StudentManagement {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("John", 1));
        students.add(new Student("Emma", 2));

        System.out.println("Student List:");
        for (Student s : students) {
            System.out.println(s.roll + " - " + s.name);
        }
    }
}

