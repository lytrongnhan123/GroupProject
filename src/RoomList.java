import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomList {
    private ArrayList<String> roomList;
    
    
    public RoomList() 
    {
        try
        {
            String filePath = "Rooms.csv";
            File file = new File(filePath);
            Scanner input = new Scanner(file);
            ArrayList<String> roomList = new ArrayList<>();
            input.delimiter();
            while (input.hasNext())
            {
                String roomNum = input.nextLine();
                roomList.add(roomNum);
            
            }
        this.roomList=roomList;
        input.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File Not Found");
        }
    }
    public ArrayList<String> getRoomList() {
        return roomList;
    }
    
}
