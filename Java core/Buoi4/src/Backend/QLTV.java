package Backend;

import Entity.Bao;
import Entity.Sach;
import Entity.TaiLieu;
import Entity.TapChi;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QLTV implements IQLTV{

    private List<TaiLieu> taiLieus= new ArrayList<>();
    private Scanner sc=new Scanner(System.in);


    @Override
    public void themTaiLieu() {
        System.out.println("Chon loai tai lieu: 1: Sach    2: Tap Chi    Khac: Bao");

        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ma tai lieu: ");
        String ma = sc.nextLine();

        for (TaiLieu tl : taiLieus) {
            if (tl.getMaTaiLieu().equals(ma)) {
                System.out.println("Ma tai lieu da ton tai!");
                return;
            }
        }

        System.out.print("Nhap nha xuat ban: ");
        String nxb = sc.nextLine();

        System.out.print("Nhap so ban phat hanh: ");
        int soBan = Integer.parseInt(sc.nextLine());

        switch (type) {
            case 1:
                System.out.print("Nhap ten tac gia: ");
                String tacGia = sc.nextLine();

                System.out.print("Nhap so trang: ");
                int soTrang = Integer.parseInt(sc.nextLine());

                taiLieus.add(new Sach(ma, nxb, soBan, tacGia, soTrang));
                break;

            case 2:
                System.out.print("Nhap so phat hanh: ");
                int soPhatHanh = Integer.parseInt(sc.nextLine());

                System.out.print("Nhap ngay phat hanh: ");
                String ngayTapChi = sc.nextLine();

                taiLieus.add(new TapChi(ma, nxb, soBan, soPhatHanh, ngayTapChi));
                break;

            case 3:
                System.out.print("Nhap ngay phat hanh: ");
                String ngayBao = sc.nextLine();

                taiLieus.add(new Bao(ma, nxb, soBan, ngayBao));
                break;
            default:
                System.out.println("Loai tai lieu khong hop le!");
                return;
        }

        System.out.println("Them tai lieu thanh cong!");
    }

    @Override
    public void xoaTaiLieuTheoID() {
        System.out.print("Nhap ma tai lieu can xoa: ");
        int ma = sc.nextInt();
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
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, nxb, soBan);
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
                TaiLieu tl = new TaiLieu(maTaiLieu, nxb, soBan);
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

    public static void main(String[] args) {
        QLTV qltv= new QLTV();
        //qltv.hienThiTaiLieu();
        qltv.timTaiLieuTheoLoai();
    }
}
