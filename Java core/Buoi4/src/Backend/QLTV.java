package Backend;

import Entity.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QLTV implements IQLTV{

    private List<TaiLieu> taiLieus= new ArrayList<>();
    private Scanner sc=new Scanner(System.in);


    @Override
    public void themTaiLieu() {
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = sc.nextLine();
        System.out.println("Nhập tên NXB: ");
        String tenNhaXuatBan = sc.nextLine();
        System.out.println("Nhập số bản phát hành: ");
        int soBanPhatHanh = sc.nextInt();
        sc.nextLine();

        System.out.println("Mời bạn chọn loại tài liệu: 1.Sách  2. Báo  Khác. Tạp chí");
        String choice = sc.nextLine();
        TaiLieu taiLieu;
        LoaiTaiLieu loaiTaiLieu = null;
        String tenTacGia = null;
        Integer soTrang = null;
        String soPhatHanh = null;
        Integer thangPhatHanh = null;
        StringBuilder sub_column = new StringBuilder();
        StringBuilder sub_value = new StringBuilder();
        switch (choice) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                tenTacGia = sc.nextLine();
                System.out.println("Nhập số trang: ");
                soTrang = sc.nextInt();
                sc.nextLine();
                loaiTaiLieu = LoaiTaiLieu.SACH;
                sub_column.append(" ten_tac_gia, so_trang");
                sub_value.append(tenTacGia).append(", ").append(soTrang);
                break;
            case "2":
                System.out.println("Nhập ngày phát hành: ");
                int ngayPH = sc.nextInt();
                System.out.println("Nhập tháng phát hành: ");
                int thangPH = sc.nextInt();
                System.out.println("Nhập năm phát hành: ");
                int namPH = sc.nextInt();
                sc.nextLine();
                loaiTaiLieu = LoaiTaiLieu.BAO;
                sub_column.append(" ngay_phat_hanh");
                sub_value.append(String.format("'%d-%d-%d'", namPH, thangPH, ngayPH));
                break;
            default:
                System.out.println("Nhập số phát hành: ");
                soPhatHanh = sc.nextLine();
                System.out.println("Nhập tháng phát hành: ");
                thangPhatHanh = sc.nextInt();
                loaiTaiLieu = LoaiTaiLieu.TAP_CHI;
                sub_column.append(" so_phat_hanh, thang_phat_hanh");
                sub_value.append(soPhatHanh).append(", ").append(thangPhatHanh);
                sc.nextLine();
        }
// lưu đối tượng taiLieu vào DB
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "1234";
        try {
            // kết nối
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, "+ sub_column +") \n" +
                    "\tVALUES (?, ?, ?, ?, "+ sub_value +")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTaiLieu);
            preparedStatement.setString(2, tenNhaXuatBan);
            preparedStatement.setInt(3, soBanPhatHanh);
            preparedStatement.setString(4, loaiTaiLieu.name());

            int c = preparedStatement.executeUpdate();// c: trả ra số row thay đổi khi thêm sửa xóa
            if (c > 0) {
                System.out.println("Thêm tài liệu thành công!");
            } else {
                System.out.println("Thêm tài liệu không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void xoaTaiLieuTheoID() {
        System.out.print("Nhap ma tai lieu can xoa: ");
        String ma = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "1234";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM tai_lieu WHERE ma_tai_lieu = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("xoa tai lieu thanh cong");
            }else {
                System.out.println("xoa tai lieu that bai");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        taiLieus.removeIf(tl -> tl.getMaTaiLieu().equals(ma));
    }

    @Override
    public void hienThiTaiLieu() {
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.out.println("Kết nối DB thành công");
        } else {
            System.out.println("Kết nối DB không thành công");
        }


        String sql = "Select * from tai_lieu";
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String nxb = resultSet.getString("ten_nxb");
                int soBan = resultSet.getInt("so_ban_phat_hanh");
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, nxb, soBan, LoaiTaiLieu.BAO);
                taiLieus.add(taiLieu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        System.out.println("+----------+--------------------+----------+");
        System.out.printf("|%10s|%20s|%10s|\n", "Ma tai lieu", "Nha xuat ban", "so ban");
        System.out.println("+----------+--------------------+----------+");
        for (TaiLieu tl : taiLieus) {
            System.out.printf("|%10s|%20s|%10s|\n", tl.getMaTaiLieu(), tl.getNhaXuatBan(), tl.getSoBanPhatHanh());
        }

        System.out.println("+----------+--------------------+----------+");

    }


    @Override
    public void timTaiLieuTheoLoai() {
        System.out.println("Nhap loai tai lieu:  1: Sach   2: Tap Chi    khac: Bao");
        int type = sc.nextInt();
        String loai;
        switch (type) {
            case 1:
                loai = "SACH";
                break;
            case 2:
                loai = "TAP_CHI";
                break;
            default:
                loai = "BAO";
                break;
        }

        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        List<TaiLieu> ketQua = new ArrayList<>();

        String sql = "Select * from tai_lieu where loai_tai_lieu = ?";

        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, loai);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String nxb = resultSet.getString("ten_nxb");
                int soBan = resultSet.getInt("so_ban_phat_hanh");
                TaiLieu tl = new TaiLieu(maTaiLieu, nxb, soBan, LoaiTaiLieu.BAO);
                ketQua.add(tl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("+----------+--------------------+----------+");
        System.out.printf("|%10s|%20s|%10s|\n",
                "Ma tai lieu", "Nha xuat ban", "So ban");
        System.out.println("+----------+--------------------+----------+");

        for (TaiLieu tl : ketQua) {
            System.out.printf("|%10s|%20s|%10s|\n",
                    tl.getMaTaiLieu(),
                    tl.getNhaXuatBan(),
                    tl.getSoBanPhatHanh());
        }

        System.out.println("+----------+--------------------+----------+");

    }

    public void suaTenNXBTheoMaTaiLieu() {
        System.out.println("Nhap ma tai lieu can sua: ");
        String maTaiLieu = sc.nextLine();
        System.out.println("Nhap nxb can sua: ");
        String nxb = sc.nextLine();
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "1234";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE tai_lieu SET ten_nxb=? WHERE ma_tai_lieu=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nxb);
            statement.setString(2, maTaiLieu);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Thanh cong");
            } else {
                System.out.println("that bai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
