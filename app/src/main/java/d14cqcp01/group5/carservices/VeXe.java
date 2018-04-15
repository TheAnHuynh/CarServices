package d14cqcp01.group5.carservices;

public class VeXe {
    public static int DA_DAT = -1;
    public static int DANG_CHON = 0;
    public static int CHUA_DAT = 1;

    private String maVe;
    private int trangThai;

    public VeXe() {
        trangThai = CHUA_DAT;
    }

    public VeXe(String maVe, int trangThai) {
        this.maVe = maVe;
        this.trangThai = trangThai;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return maVe + " " + trangThai;
    }
}
