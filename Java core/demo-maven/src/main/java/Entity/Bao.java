package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Bao extends TaiLieu{
    private String ngayPhatHanh;

    public Bao(String maTaiLieu, String nhaXuatBan, int soBanPhatHanh,LoaiTaiLieu loaiTaiLieu, String ngayPhatHanh) {
        super(maTaiLieu, nhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
