package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 5/5/2018.
 */

public class CoachListModel {
    private String id;
    private String icon;
    private String companyId;
    private String customer;
    private float stars;
    private String from;
    private String to;
    private String timeStart;
    private String timeEnd;
    private String price;
    private String type;
    private String vacantSeats;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(String vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public String finalAddress(){
        return "carservices-cb0bd/CoachList";
    }
    public CoachListModel(String id, String icon, String companyId, String customer, float stars,
                                      String from, String to, String timeStart, String timeEnd, String price,
                                      String type, String buttonInfo) {
        this.id = id;
        this.icon = icon;
        this.companyId = companyId;
        this.customer = customer;
        this.stars = stars;
        this.from = from;
        this.to = to;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcompanyId() {
        return companyId;
    }

    public void setcompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getstars() {
        return stars;
    }

    public void setstars(float stars) {
        this.stars = stars;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

}
