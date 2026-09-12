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
            System.out.println("1. Manage account");
            System.out.println("2. Manage department");
            System.out.println("3. Manage position");
            System.out.println("4. exit");
            System.out.print("Nhap lua chon: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    qlAccount.manageAccount();
                    break;
                case 2:
                    qlDepartment.manageDepartment();
                    break;
                case 3:
                    qlPosition.managePosition();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}