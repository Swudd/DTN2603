import java.util.List;
import java.util.Scanner;

public class Exercise4 {
    public static int question1(String str){
        if(str == null){
            return 0;
        }
        return str.trim().split("\\s+").length;
    }

    public static String question2(String s1, String s2){
        return s1+ s2;
    }

    public static String capitalizeName(String name) {
        String[] words = name.trim().split("\\s+");
        String result = "";

        for (String word : words) {
            result += word.substring(0, 1).toUpperCase()
                    + word.substring(1).toLowerCase() + " ";
        }

        return result.trim();
    }

    public static void question3(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        System.out.println(capitalizeName(name));
    }

    public static void question4(){
        System.out.println("Vui lòng nhập tên người dùng: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase();
        for(int i = 0; i < name.length(); i++){
            System.out.println("Kí tự thứ " + i + " là: " + name.charAt(i));
        }
    }

    public static void question5(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập họ: ");
        String ho = sc.nextLine();

        System.out.print("Nhập tên: ");
        String ten = sc.nextLine();

        System.out.println("Họ và tên đầy đủ: " + ho + " " + ten);
    }

    public static void question6() {
        System.out.println("Vui lòng nhập họ tên đầy đủ: ");

        Scanner sc = new Scanner(System.in);
        String fullName = sc.nextLine();

        String[] words = fullName.trim().split("\\s+");

        String ho = words[0];
        String ten = words[words.length - 1];

        String tenDem = "";

        for (int i = 1; i < words.length - 1; i++) {
            tenDem += words[i] + " ";
        }

        tenDem = tenDem.trim();

        System.out.println("Họ là: " + ho);
        System.out.println("Tên đệm là: " + tenDem);
        System.out.println("Tên là: " + ten);
    }

    public static String normalizeName(String name) {
        name = name.trim().replaceAll("\\s+", " ");

        String[] words = name.split(" ");
        String result = "";

        for (String word : words) {
            result += word.substring(0, 1).toUpperCase()
                    + word.substring(1).toLowerCase() + " ";
        }

        return result.trim();
    }

    public static String question7(){
        System.out.println("Nhập họ và tên: ");
        Scanner sc = new Scanner(System.in);
        String fullName = sc.nextLine();
        normalizeName(fullName);
        System.out.println("Ho ten:  " + fullName);
        return fullName;
    }

    public static void question8(List<Group> groups) {
        System.out.println("-".repeat(28));
        System.out.printf("%-5s %-20s%n", "ID", "Group Name");
        System.out.println("-".repeat(28));
        for (Group g : groups) {
            if (g.groupName.contains("Java")) {
                System.out.printf("|%-5s|%-20s%n", g.groupId, g.groupName);
            }
        }
    }

    public static void question9(List<Group> groups) {
        System.out.println("-".repeat(28));
        System.out.printf("|%-5s|%-20s%n", "ID", "Group Name");
        System.out.println("-".repeat(28));
        boolean found = false;
        for (Group g : groups) {
            if (g.groupName.equals("Java")) {
                System.out.printf("%-5s %-20s%n", g.groupId, g.groupName);
                found = true;
            }
        }
        if (!found) {
            System.out.println("(Không có group nào tên đúng là \"Java\")");
        }
    }

    public static void question10(String str1, String str2) {
        String reversed = new StringBuilder(str1).reverse().toString();
        if (reversed.equals(str2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public static void question11(String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                count++;
            }
        }

        System.out.println(count);
    }

    public static void question12(String str) {
        String result = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }

        System.out.println(result);
    }

    public static boolean question13(String str) {
        if (str == null) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String question14(String str, char oldChar, char newChar) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == oldChar) {
                result += newChar;
            } else {
                result += str.charAt(i);
            }
        }

        return result;
    }

    public static String question15(String str) {
        String[] words = str.trim().split(" ");
        String result = "";

        for (int i = words.length - 1; i >= 0; i--) {
            result += words[i];

            if (i > 0) {
                result += " ";
            }
        }

        return result;
    }

    public static void question16(String str, int n) {
        if (n <= 0 || str.length() % n != 0) {
            System.out.println("KO");
            return;
        }

        for (int i = 0; i < str.length(); i += n) {
            System.out.println(str.substring(i, i + n));
        }
    }
}
