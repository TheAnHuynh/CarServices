package d14cqcp01.group5.carservices;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class XeKhach {
    public final static String XE_GIUONG_NAM = "Xe gường nằm"; // 39 chỗ
    public final static String XE_16_CHO = "XE 16 chỗ";
    public final static String XE_24_CHO = "Xe 24 chỗ";

    private String id;
    private String companyId;

    private Integer price;
    private String journey;
    private String from;
    private String to;
    private String timeStart;
    private String timeEnd;

    private float stars;

    private String type;
    private Integer vacantSeats;
    private ArrayList<String> ticketList;

    public XeKhach(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
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

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(Integer vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public ArrayList<String> getTicketList() {
        return ticketList;
    }

    public void setTicketList(ArrayList<String> ticketList) {
        this.ticketList = ticketList;
    }
    public long getStartTimeInMilliseconds(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy HH:mm");
        try {
            Date date = sdf.parse(timeStart);
            long millis = date.getTime();
            return millis;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().getTime();
    }

    public long getTimeEndInMilliseconds(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy HH:mm");
        try {
            Date date = sdf.parse(timeEnd);
            long millis = date.getTime();
            return millis;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().getTime();
    }


    @Override
    public String toString() {
        return "ID:" + getId() + "\n" +
                "Company ID: " + getCompanyId() + "\n" +
                "From: " + getFrom() + "\n" +
                "To: " + getTo() + "\n" +
                "Journey:  " + getJourney() + "\n" +
                "Time Start: " + getTimeStart().toString() + "\n" +
                "Time End: " + getTimeEnd().toString() + "\n" +
                "Price: " + getPrice() + "\n" +
                "Type: " + getType() + "\n" +
                "Stars: " + getStars() + "\n" +
                "Vacanseats: " + getVacantSeats() + "\n";
    }
}
