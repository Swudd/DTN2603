package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class TapChi extends TaiLieu{
    private int soPhatHanh;
    private String thangPhatHanh;

    public TapChi(String maTaiLieu, String nhaXuatBan, int soBanPhatHanh, int soPhatHanh,LoaiTaiLieu loaiTaiLieu, String thangPhatHanh) {
        super(maTaiLieu, nhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

}
