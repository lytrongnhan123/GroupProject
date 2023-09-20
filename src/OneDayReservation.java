import java.time.LocalDate;
import java.util.ArrayList;



public class OneDayReservation {
    public  int numberOfRoom;
    
    private String roomNo;
    private String userId;
    private LocalDate start;
    private LocalDate enDate;
    private double price;

    //public ArrayList<OneDayReservation> oneday = new ArrayList<OneDayReservation>();
    //OneDayReservation[] a = new OneDayReservation[10];

    public OneDayReservation() {
    }
  
    public OneDayReservation(String userId, String roomNo) {
        this.roomNo = roomNo;
        this.userId = userId;       
        numberOfRoom++;
    }

    public OneDayReservation(String roomNo, String userId, double price ) {
        this.roomNo = roomNo;
        this.userId = userId;
        this.price = price;
        numberOfRoom++;
    }

    public OneDayReservation(String roomNo, String userId, LocalDate start, LocalDate enDate) {
        this.roomNo = roomNo;
        this.userId = userId;
        this.start = start;
        this.enDate = enDate;
        numberOfRoom++;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public double getPrice() {
        return price;
    }

    public OneDayReservation(String roomNo, String userId, LocalDate start, LocalDate enDate, double price) {
        this.roomNo = roomNo;
        this.userId = userId;
        this.start = start;
        this.enDate = enDate;
        this.price = price;
        numberOfRoom++;
    }


    

    public String getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnDate() {
        return enDate;
    }
    public void setEnDate(LocalDate enDate) {
        this.enDate = enDate;
    }



   

    @Override
    public String toString() {
        return "OneDayReservation [numberOfRoom=" + numberOfRoom + ", roomNo=" + roomNo + ", userId=" + userId
                + ", start=" + start + ", enDate=" + enDate + ", price=" + price + "]";
    }

    public OneDayReservation get(int i) {
        return null;
    }

    public void add(OneDayReservation oneDayReservation) {
    }

    public int size() {
        return 0;
    }
    
   
}
