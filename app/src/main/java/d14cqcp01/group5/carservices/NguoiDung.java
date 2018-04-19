package d14cqcp01.group5.carservices;

public class NguoiDung
{
    private String hoten;
    private String email;
    private String pass;
    private String sdt;

    public NguoiDung() {
    }

    public NguoiDung(String hoten, String email, String pass, String sdt) {
        this.hoten = hoten;
        this.email = email;
        this.pass = pass;
        this.sdt = sdt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
