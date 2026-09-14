package frontend;

import backend.controller.QLAccountController;
import backend.controller.QLDepartmentController;
import backend.controller.QLPositionController;
import entity.Account;
import entity.Department;
import entity.Gender;
import entity.Position;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private QLAccountController AController;
    private QLDepartmentController DController;
    private QLPositionController PController;

    public Function(){
        scanner = new Scanner(System.in);
        AController = new QLAccountController();
        DController = new QLDepartmentController();
        PController = new QLPositionController();
    }

    public void showAllAccounts(){
        System.out.println("==== Hiển thị toàn bộ account ====");
        List<Account> accounts = AController.getAllAccount();
        this.show(accounts);
    }

    public void findAccount(){
        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        List<Account> accounts = AController.getAccountByName(username);
        this.show(accounts);
    }

    public void deleteAccount(){
        System.out.println("Nhập account id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (AController.DeleteAccount(id) == true){
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    public void updateAccount(){
        System.out.println("Nhập account id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập username mới: ");
        String username = scanner.nextLine();
        if(AController.UpdateAccount(username, id) == true){
            System.out.println("Sửa thành công");
        } else {
            System.out.println("Sửa thất bại");
        }
    }

    public void addAccount(){
        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        System.out.println("Nhập fullname: ");
        String fullName = scanner.nextLine();
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Chon gender: 1. Male    2. Female    other.Unknow");
        Gender gender;
        int pickGender = scanner.nextInt();
        scanner.nextLine();
        switch (pickGender) {
            case 1:
                gender = Gender.M;
                break;
            case 2:
                gender = Gender.F;
                break;
            default:
                gender = Gender.U;
                break;
        }

        Department department ;
        while (true) {
            System.out.println("Chọn ID phòng ban muốn thêm vào: ");
            List<Department> departments = DController.showAllDepartments();
            for (Department dep : departments) {
                System.out.printf("ID: %s - Name: %s\n", dep.getDepartmentId(), dep.getDepartmentName());
            }
            if (scanner.hasNextInt()) { // nhập ko phải là số
                String choiceDep = scanner.nextLine();
                Department department1 = departments.stream().filter(dep -> dep.getDepartmentId() == Integer.parseInt(choiceDep))
                        .findFirst().orElse(null);// lambda
                if (Objects.isNull(department1)) { //department == null
                    System.out.println("Chọn sai. Chọn lại phòng ban!");
                } else {
                    department = department1;
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại phòng ban!");
                scanner.nextLine();
            }
        }

        Position position ;
        while (true) {
            System.out.println("Chọn ID chức vụ muốn thêm vào: ");
            List<Position> positions = PController.GetAllPosition();
            for (Position pos : positions) {
                System.out.printf("ID: %s - Name: %s\n", pos.getPositionId(), pos.getPositionName());
            }
            if (scanner.hasNextInt()) { // nhập ko phải là số
                Position position1 = null;
                String choiceDep = scanner.nextLine();
                for (Position pos : positions) {
                    if (pos.getPositionId() == Integer.parseInt(choiceDep)) {
                        position1 = pos;
                        break;
                    }
                }
                if (Objects.isNull(position1)) { // nhập ID ko tồn tại
                    System.out.println("Chọn sai. Chọn lại chức vụ!");
                } else {
                    position = position1;
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại chức vụ!");
                scanner.nextLine();
            }
        }
        LocalDate date = LocalDate.now();
        Account account = new Account(1, email,username, fullName, department, position, date, gender);
        if(AController.AddAccount(account)==true){
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }
    }

    public void show(List<Account> accounts){
        System.out.println("+-----+-------------------------+--------------------+--------------------+--------------------+---------------+----------+-----+");
        System.out.printf("|%5s|%25s|%20s|%20s|%20s|%15s|%10s|%5s|\n", "id", "email", "username", "full_name", "department", "position", "cdate", "gender");
        System.out.println("+-----+-------------------------+--------------------+--------------------+--------------------+---------------+----------+-----+");
        for (Account a : accounts) {
            System.out.printf("|%5s|%25s|%20s|%20s|%20s|%15s|%10s|%5s|\n"
                    , a.getAcountId(), a.getEmail(), a.getUsername(), a.getFullname()
                    , a.getDepartment().getDepartmentName(), a.getPosition().getPositionName(), a.getCreateDate(), a.getGender());
        }

        System.out.println("+-----+-------------------------+--------------------+--------------------+--------------------+---------------+----------+-----+");
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm account");
            System.out.println("2. Xoá account theo account id");
            System.out.println("3. Hiện thị toàn bộ account");
            System.out.println("4. Tìm kiếm account theo account id");
            System.out.println("5. Đổi tên account theo account id");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.addAccount();
                    break;
                case "2":
                    this.deleteAccount();
                    break;
                case "3":
                    this.showAllAccounts();
                    break;
                case "4":
                    this.findAccount();
                    break;
                case "5":
                    this.updateAccount();
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! chọn lại!");
            }
        }
    }
}
