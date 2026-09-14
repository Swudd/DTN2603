package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaiLieu {
    private String maTaiLieu;
    private String nhaXuatBan;
    private int soBanPhatHanh;
    private LoaiTaiLieu loaiTaiLieu;

}
