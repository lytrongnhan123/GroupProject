import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        /*AccountList ac = new AccountList();
        ArrayList<Account> arr = ac.getAccountList();
        System.out.println(arr.get(1).toString());
        RoomList rl = new RoomList();
        ArrayList<String> arr2 = rl.getRoomList();
        System.out.println(arr2);
        */
        BookingArrayList bka = new BookingArrayList();
        ArrayList<BookingInfomation> arr3 = bka.getBookingList();
        for(BookingInfomation i:arr3)
        {
            System.out.println(i.toString());
            System.out.println("------------------");
        }
    }
    
}
