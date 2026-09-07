package Frontend;

import Backend.QLTV;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QLTV qltv = new QLTV();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== QUAN LY THU VIEN ==========");
            System.out.println("a. Them moi tai lieu");
            System.out.println("b. Xoa tai lieu theo ma tai lieu");
            System.out.println("c. Hien thi thong tin tai lieu");
            System.out.println("d. Tim kiem tai lieu theo loai");
            System.out.println("e. Thoat");
            System.out.println("======================================");
            System.out.print("Nhap lua chon: ");
            String input = sc.nextLine();

            switch (input.toLowerCase()) {
                case "a":
                    qltv.themTaiLieu();
                    break;
                case "b":
                    qltv.xoaTaiLieuTheoID();
                    break;
                case "c":
                    qltv.hienThiTaiLieu();
                    break;
                case "d":
                    qltv.timTaiLieuTheoLoai();
                    break;
                case "e":
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}