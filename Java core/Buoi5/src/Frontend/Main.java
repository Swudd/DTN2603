package Frontend;

import Backend.QLAccount;
import Backend.QLDepartment;
import Backend.QLPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QLAccount qlAccount = new QLAccount();
        QLDepartment qlDepartment = new QLDepartment();
        QLPosition qlPosition = new QLPosition();

        while (true) {
            System.out.println("1. Hien thi account");
            System.out.println("2. Hien thi department");
            System.out.println("3. Hien thi position");
            System.out.println("4. exit");
            System.out.print("Nhap lua chon: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    qlAccount.showAccount();
                    break;
                case 2:
                    qlDepartment.showDepartment();
                    break;
                case 3:
                    qlPosition.showPosition();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}