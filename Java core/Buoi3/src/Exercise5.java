import java.util.Arrays;
import java.util.Comparator;

public class Exercise5 {

    // Question 1
    public static void question1(Department[] departments) {
        System.out.println(departments[0].toString());
    }

    // Question 2
    public static void question2(Department[] departments) {
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    // Question 3
    public static void question3(Department[] departments) {
        System.out.println(departments[0].hashCode());
    }

    // Question 4
    public static void question4(Department[] departments) {
        if (departments[0].departmentName.equals("Phòng A")) {
            System.out.println("Có");
        } else {
            System.out.println("Không");
        }
    }

    // Question 5
    public static void question5(Department[] departments) {
        if (departments[0].departmentName
                .equals(departments[1].departmentName)) {
            System.out.println("Bằng nhau");
        } else {
            System.out.println("Không bằng nhau");
        }
    }

    // Question 6
    public static void question6(Department[] departments) {
        Arrays.sort(departments,
                Comparator.comparing(d -> d.departmentName));

        for (Department department : departments) {
            System.out.println(department.departmentName);
        }
    }

    // Question 7
    public static void question7(Department[] departments) {
        Arrays.sort(departments,
                (d1, d2) -> d1.departmentName
                        .compareToIgnoreCase(d2.departmentName));

        for (Department department : departments) {
            System.out.println(department.departmentName);
        }
    }
}
