package Backend;

import Entity.Bao;
import Entity.Sach;
import Entity.TaiLieu;
import Entity.TapChi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QLTV implements IQLTV{

    private List<TaiLieu> taiLieus= new ArrayList<>();
    private Scanner sc=new Scanner(System.in);

    public QLTV() {
        TaiLieu sach = new Sach(12, "Kim Dong", 76, "Bob", 100);
        TaiLieu tapChi = new TapChi(12, "hikari", 34, 8462, "2025/7/2");
        TaiLieu Bao = new Bao(52, "GAGAGAGAGA BUNKO", 32, "2022/12/4");
        taiLieus.add(sach);
        taiLieus.add(tapChi);
        taiLieus.add(Bao);
    }

    @Override
    public void themTaiLieu() {
        System.out.println("Chon loai tai lieu: 1: Sach    2: Tap Chi    Khac: Bao");

        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ma tai lieu: ");
        int ma = Integer.parseInt(sc.nextLine());

        for (TaiLieu tl : taiLieus) {
            if (tl.getMaTaiLieu() == ma) {
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
        taiLieus.removeIf(tl -> tl.getMaTaiLieu() == ma);
    }

    @Override
    public void hienThiTaiLieu() {
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

        List<TaiLieu> ketQua = new ArrayList<>();

        for (TaiLieu tl : taiLieus) {
            if ((type == 1 && tl instanceof Sach) || (type == 2 && tl instanceof TapChi) || (type == 3 && tl instanceof Bao)) {
                ketQua.add(tl);
            }
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
}
