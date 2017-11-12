package validation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student1.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student1);

        Student student2 = new Student("Sorah Yang", new BirthDate(7, 10, 1990),
                "gsdsfsdj@dhfkj.com", "306434533209", "Korean",
                6, 4.0, true);
        student2.setScholarship(2500);
        student2.addSubjects(new String[]{"Design", "Business", "Economics", "History"});
        students.add(student2);

        Student student3 = new Student("Akanen Mioshi", new BirthDate(15, 8, 1994),
                "gsdj@dhfkj.com", "306434309343", "Japanese",
                5,3.9, false);
        student3.addSubjects(new String[]{"Design", "Literature", "Writing", "Chinese"});
        students.add(student3);

        for (Student student : students)
            System.out.println(student.toString() + "\n");
    }
}
