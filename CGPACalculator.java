import java.util.Scanner;
import java.util.ArrayList;

public class CGPACalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the CGPA Calculator!");

        double totalGradePoints = 0.0;
        int totalGradeUnits = 0;

        ArrayList<Course> courses = new ArrayList<>();
        
        boolean initial = true;

         while (true) {
            System.out.print("Enter course code: ");
            String courseCode = input.nextLine();

            if (courseCode.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter course units: ");
            int courseUnits = Integer.parseInt(input.nextLine());

            System.out.print("Enter score: ");
            int score = Integer.parseInt(input.nextLine());

            Course course = new Course(courseCode, courseUnits, score);
            courses.add(course);

            totalGradeUnits += course.getCourseUnits();
            totalGradePoints += calculateGradePoints(course.getScore()) * (course.getCourseUnits());

            if (!initial){
                System.out.println("If that's all, type 'done' to finish. Else continue inputing your results:");
            }
            initial = false;
        }

        // Display table header
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        for (Course course : courses) {
            String grade = calculateGrade(course.getScore());
            int gradeUnit = calculateGradePoints(course.getScore());
            System.out.printf("| %-26s| %-23d| %-11s| %-20d|%n", course.getCourseCode(), course.getCourseUnits(), grade, gradeUnit);
        }
        // Display table footer
        System.out.println("|---------------------------------------------------------------------------------------|");

        if (totalGradeUnits > 0) {
            double cgpa = totalGradePoints / totalGradeUnits;
            System.out.printf("Your CGPA is = %.2f to 2 decimal places.%n", cgpa);
        } else {
            System.out.println("No courses entered. CGPA cannot be calculated.");
        }

        input.close();
    }

    private static int calculateGradePoints(int score) {
        if (score >= 70 && score <= 100) {
            return 5;
        } else if (score >= 60 && score <= 69) {
            return 4;
        } else if (score >= 50 && score <= 59) {
            return 3;
        } else if (score >= 45 && score <= 49) {
            return 2;
        } else if (score >= 40 && score <= 44) {
            return 1;
        } else {
            return 0;
        }
    }

    private static String calculateGrade(int score) {
        if (score >= 70 && score <= 100) {
            return "A";
        } else if (score >= 60 && score <= 69) {
            return "B";
        } else if (score >= 50 && score <= 59) {
            return "C";
        } else if (score >= 45 && score <= 49) {
            return "D";
        } else if (score >= 40 && score <= 44) {
            return "E";
        } else {
            return "F";
        }
    }
}