package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Sach extends TaiLieu{
    private String tacGia;
    private int soTrang;

    public Sach(String maTaiLieu, String nhaXuatBan, int soBanPhatHanh,LoaiTaiLieu loaiTaiLieu, String tacGia, int soTrang) {
        super(maTaiLieu, nhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.tacGia = tacGia;
        this.soTrang = soTrang;
    }

}
