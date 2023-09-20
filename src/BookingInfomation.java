import java.time.LocalDate;

public class BookingInfomation {
    private String userName;
    private String roomNum;
    private LocalDate startDate;
    private LocalDate endDate;
    public double defaultPrice = 59.99;
    private double price;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public BookingInfomation() {
    }
    public BookingInfomation(String userName, String roomNum, LocalDate startDate, LocalDate endDate, double price) {
        this.userName = userName;
        this.roomNum = roomNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString()
    {
        return String.format("Username: %s\nRoom: %s\nStart Date: %s\nEnd Date: %s\nPrice: %s", userName, roomNum, startDate, endDate, price);
    }
    
    
    

    
}
