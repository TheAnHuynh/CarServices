package d14cqcp01.group5.carservices;

import java.util.Date;

public class XeKhach {
    public final static String XE_GIUONG_NAM = "Xe gường nằm"; // 38 chỗ
    public final static String XE_16_CHO = "XE 16 chỗ";
    public final static String XE_24_CHO = "Xe 24 chỗ";
    public final static String XE_29_CHO = "Xe 29 chỗ";
    public final static String XE_34_CHO = "Xe 34 chỗ";
    public final static String XE_46_CHO = "Xe 46 chỗ";

    private String maNhaXe;
    private String maXeKhach;
    private String anh;
    private Integer giaVe;
    private String thoiGian;
    private String loTrinhChiTiet;
    private String loaiXe;

    public XeKhach(){
    }

    public XeKhach(String maNhaXe, String maXeKhach, String anh, Integer giaVe,
                   String thoiGian, String loTrinhChiTiet, String loaiXe) {
        this.maNhaXe = maNhaXe;
        this.maXeKhach = maXeKhach;
        this.anh = anh;
        this.giaVe = giaVe;
        this.thoiGian = thoiGian;
        this.loTrinhChiTiet = loTrinhChiTiet;
        this.loaiXe = loaiXe;
    }



}
