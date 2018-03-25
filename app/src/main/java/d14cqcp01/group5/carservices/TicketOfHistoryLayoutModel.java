package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 3/15/2018.
 */

public class TicketOfHistoryLayoutModel {
    private String id;
    private String iconBrand;
    private String brand;
    private String customer;
    private float rating;
    private String from;
    private String to;
    private String timeStart;
    private String timeEnd;
    private String moreinfo;
    private String moreinfosupport;
    private String buttonInfo;

    public TicketOfHistoryLayoutModel(String id, String iconBrand, String brand, String customer, float rating,
                                      String from, String to, String timeStart, String timeEnd, String moreinfo,
                                      String moreinfosupport, String buttonInfo) {
        this.id = id;
        this.iconBrand = iconBrand;
        this.brand = brand;
        this.customer = customer;
        this.rating = rating;
        this.from = from;
        this.to = to;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.moreinfo = moreinfo;
        this.moreinfosupport = moreinfosupport;
        this.buttonInfo = buttonInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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

    public String getMoreinfo() {
        return moreinfo;
    }

    public void setMoreinfo(String moreinfo) {
        this.moreinfo = moreinfo;
    }

    public String getMoreinfosupport() {
        return moreinfosupport;
    }

    public void setMoreinfosupport(String moreinfosupport) {
        this.moreinfosupport = moreinfosupport;
    }

    public String getButtonInfo() {
        return buttonInfo;
    }

    public void setButtonInfo(String buttonInfo) {
        this.buttonInfo = buttonInfo;
    }

}
