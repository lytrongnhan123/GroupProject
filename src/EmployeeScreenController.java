
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeScreenController implements Initializable{

    @FXML
    private Label employeelabel;
    @FXML
    private TableView<BookingInfomation> tableViewData;
    @FXML
    private TableColumn<BookingInfomation, String> column1;
    @FXML
    private TableColumn<BookingInfomation, String> column2;
    @FXML
    private TableColumn<BookingInfomation, LocalDate> column3;
    @FXML
    private TableColumn<BookingInfomation, LocalDate> column4;
    @FXML
    private TableColumn<BookingInfomation, String> column5;

   
    @FXML
    private void handleLogOutButtonAction() {
        // Switch back to the Login Screen when the user clicks the "Logout" button
       // System.out.println(Main.loginName);
       // Display Reservation by monthly
       Main.setRoot("LoginScreen");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeelabel.setText("Employee, " + Main.loginName + "!");
        column1.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
        column3.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        column4.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        column5.setCellValueFactory(new PropertyValueFactory<>("Price"));
        loadCSVData();
    }
    public void loadCSVData()
    {
        BookingArrayList ar = new BookingArrayList();
        try
        {
            List<BookingInfomation> data = ar.getBookingList();
            tableViewData.getItems().addAll(data);
        }   
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
        
        
}