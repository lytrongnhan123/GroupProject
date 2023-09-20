import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingArrayList {
    private ArrayList<BookingInfomation> bookingList;
    
    public BookingArrayList()
    {
        try
        {
            String filePath = "BookingInfo.csv";
            File file = new File(filePath);
            Scanner input = new Scanner(file);
            ArrayList<BookingInfomation> bookingList = new ArrayList<>();
            input.delimiter();
            while (input.hasNext())
            {
                String line=input.nextLine();
                String[] data = line.split(",");
                String userName = data[0];
                String room = data[1];
                LocalDate startDate = LocalDate.parse(data[2]);
                LocalDate endDate = LocalDate.parse(data[3]);
                Double price = Double.parseDouble(data[4]);
                bookingList.add(new BookingInfomation(userName,room,startDate,endDate,price));
            }
            this.bookingList=bookingList;
            input.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        
    }
    public ArrayList<BookingInfomation> getBookingList() throws FileNotFoundException
    {
        return bookingList;
    }
    
}
