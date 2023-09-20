import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
public class CustomerScreenController implements Initializable{
    @FXML
    private ComboBox<String> cmbRooms;

    @FXML
    private DatePicker startDatePicker;

    @FXML 
    private DatePicker endDatePicker;

    @FXML
    private Label lbWelcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {
        lbWelcome.setText(lbWelcome.getText()+Main.loginName);
        RoomList roomList = new RoomList();
        ObservableList<String> roomOb = FXCollections.observableArrayList(roomList.getRoomList());
        cmbRooms.setItems(roomOb);

        startDatePicker.setOnAction(event -> {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (endDate==null || endDate.isBefore(startDate)) {
                endDatePicker.setValue(startDate.plusDays(1));
            }
        });
        endDatePicker.setOnAction(event -> {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (startDate==null || startDate.isAfter(endDate)) {
                startDatePicker.setValue(endDate.minusDays(1));
            }
        });
    }
    @FXML 
    private void HandleLogOutButton()
    {
        Main.setRoot("LoginScreen");
    }
    @FXML 
    private void HandleBookButtonAction()
    {
        if (cmbRooms.getValue()==null||startDatePicker.getValue()==null||endDatePicker.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Information is lacking, fail to book a room.");
            alert.showAndWait();
        }
        else
        {
            double price = CalPrice(startDatePicker.getValue(), endDatePicker.getValue());
            if (checkValidRoom(cmbRooms.getValue(), startDatePicker.getValue(), endDatePicker.getValue())==false)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The room is already booked in this day, please choose another room.");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Comfirmation");
                alert.setHeaderText("Confirm Booking");
                alert.setContentText(String.format("Room: %s\nStart Date: %s\nEnd Date: %s\nPrice: $%s", cmbRooms.getValue(), startDatePicker.getValue(), endDatePicker.getValue(), price));
                ButtonType confirmButton = new ButtonType("Confirm");
                ButtonType cancelButton = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(confirmButton, cancelButton);

                alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == confirmButton) {
                    try (PrintWriter writer = new PrintWriter(new FileWriter("bookingInfo.csv",true))) 
                    {
                        writer.println(Main.loginName + "," + cmbRooms.getValue() + "," + startDatePicker.getValue() + "," + endDatePicker.getValue()+ "," + price);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Successfully booked.");
                        alert1.showAndWait();
                    } catch (IOException e) {
                        System.err.println("Error writing data: " + e.getMessage());
                    }
                } else if (buttonType == cancelButton) {
                    System.out.println("Action canceled.");
                }
            });
                
            }
            
        }
    }
    public boolean checkValidRoom(String room, LocalDate startDate, LocalDate endDate)
    {
        BookingArrayList bk = new BookingArrayList();
        boolean cond = true;
        try
        {
            for (BookingInfomation i: bk.getBookingList())
            {
                if (room.equals(i.getRoomNum())&&isDateRangeOverlap(i.getStartDate(),i.getEndDate(),startDate,endDate))
                {
                    cond=false;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return cond;
    }
    public static boolean isDateRangeOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }
    public double CalPrice(LocalDate startDate, LocalDate endDate)
    {
        long dateDifferent = ChronoUnit.DAYS.between(startDate, endDate);
        BookingInfomation bk = new BookingInfomation();
        double price = bk.defaultPrice*dateDifferent;
        return price;
    }
    

}
