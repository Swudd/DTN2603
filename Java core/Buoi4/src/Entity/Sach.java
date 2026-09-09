package Entity;

public class Sach extends TaiLieu{
    private String tacGia;
    private int soTrang;

    public Sach() {
    }

    public Sach(String maTaiLieu, String nhaXuatBan, int soBanPhatHanh, String tacGia, int soTrang) {
        super(maTaiLieu, nhaXuatBan, soBanPhatHanh);
        this.tacGia = tacGia;
        this.soTrang = soTrang;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
}
