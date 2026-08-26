public class Exercise1 {
    public static int question1(float luong){
        int result = Math.round(luong);
        System.out.println("Luong da lam tron:" + result);
        return result;
    }

    public static String question2(int number) {
        String result = String.format("%05d", number);
        return result;
    }

    public static String question3(String number) {
        String result = number.substring(number.length() - 2);
        System.out.println("Question 3: " + result);
        return result;
    }

    public  static float question4(int num1, int num2) {
        float result = (float) num1/num2;
        System.out.println("Thuong cua 2 so:" + result);
        return result;
    }
}
