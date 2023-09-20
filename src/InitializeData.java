import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class InitializeData {
    
   
    public static String[][] msg = new String[13][100];

    public static void InitializeData(){

        for (int i = 0; i < 13; i++){
            for (int j= 0 ; j < 32; j++){
                msg[i][j] = "";
            }
        }


        BookingArrayList bka = new BookingArrayList();
        try{
            ArrayList<BookingInfomation> arr3 = bka.getBookingList();
            for (int i=0; i < arr3.size(); i++){

                System.out.println("Booking Array=" + arr3.get(i).toString());
                registerData(arr3.get(i).getUserName(), arr3.get(i).getRoomNum(), 
                   arr3.get(i).getStartDate()  ,arr3.get(i).getEndDate(), arr3.get(i).getPrice() );

            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
}


    public static void registerData(String username, String roomno, LocalDate startDate, LocalDate endDate, double price) {

            int start_month = startDate.getMonthValue();
            int start_day = startDate.getDayOfMonth();
            long dateDifferent = ChronoUnit.DAYS.between(startDate, endDate);
            int count = (int) (dateDifferent);

            int dd = start_day;
            int mm = start_month;
            System.out.println("username="+ username + " roomno=" + roomno+ " sDate=" + startDate + " eDate=" + endDate + " StartDay=" + start_month + " Start day=" + start_day + " count=" + count);

            for (int i = 0; i < count; i++){
               
                String out = "Room:" + roomno + " User:" + username + "\n";                 
                if (msg[mm][dd] == "") {
                    msg[mm][dd] = out; 
                } else {
                    msg[mm][dd] = msg[mm][dd] + out;
                }

                System.out.println("m=" + mm + " d=" + dd + msg[mm][dd]);
                dd =  dd + 1;

                if ((mm == 0) && (dd==31)) { mm=mm + 1; dd=1;} // if the end of august, it will be september 
                if ((mm == 1) && (dd==30)) { mm=mm + 1; dd=1;} // if the end of september, it wll be october
                if ((mm == 2) && (dd==31)) { mm=mm + 1; dd=1;} // if the end of october, it will be Nomember
                if ((mm == 3) && (dd==30)) { mm=mm + 1; dd=1;} // if the end of Nomember, it wll be December

            }
        }
        
    }
