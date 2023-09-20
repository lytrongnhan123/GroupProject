import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class RegisterationScreenController implements Initializable{
    public String verificationNum = "33543354";
    @FXML
    private TextField usernameField;
    @FXML 
    private PasswordField passwordField;
    @FXML 
    private PasswordField confirmPasswordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField verificationField;
    @FXML 
    private RadioButton customerRadioButton;
    @FXML 
    private RadioButton employeeRadioButton;
    @FXML
    private ToggleGroup accountTypeToggleGroup;
    @FXML
    private Label usernameCond;
    @FXML
    private Label passwordCond;
    @FXML
    private Label confirmPasswordCond;
    @FXML
    private Label emailCond;
    @FXML
    private Label phoneNumberCond;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {
        verificationField.setDisable(true);
        accountTypeToggleGroup = new ToggleGroup();
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        employeeRadioButton.setToggleGroup(accountTypeToggleGroup);
        accountTypeToggleGroup.selectToggle((RadioButton) accountTypeToggleGroup.getToggles().get(0));
        accountTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                if (selectedRadioButton.getText().equals("Customer")) {
                    verificationField.clear();
                    verificationField.setDisable(true);
                } else if (selectedRadioButton.getText().equals("Employee")) {
                    verificationField.setDisable(false);
                }
            }
        });
    }
    @FXML 
    private void HandleLogOutButton()
    {
        Main.setRoot("LoginScreen");
    }
    @FXML
    private void HandleRegisterationButtonAction()
    {
        RadioButton selectedRadioButton = (RadioButton) accountTypeToggleGroup.getSelectedToggle();
        if (verifyAccount(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText(), 
        emailField.getText(), phoneNumberField.getText(),accountTypeToggleGroup))
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Confirm Account Creation");
            ButtonType confirmButton = new ButtonType("Confirm");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(confirmButton, cancelButton);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == confirmButton) {
                    try (PrintWriter writer = new PrintWriter(new FileWriter("Accounts.csv",true))) 
                    {
                        String id="";
                        
                        if (verificationField.isDisabled()==true)
                        {
                            id=GenerateId("001");
                        }
                        else
                        {
                            id=GenerateId("002");
                        }
                        writer.println(id + "," + usernameField.getText() + "," + passwordField.getText() + "," + phoneNumberField.getText() + "," + emailField.getText()+ "," + selectedRadioButton.getText());
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Successfully registered!");
                        alert1.showAndWait();
                    } catch (IOException e) {
                        System.err.println("Error writing data: " + e.getMessage());
                    }
                } else if (buttonType == cancelButton) {
                    System.out.println("Action canceled.");
                }
            });
        }
        else 
        {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Information. Fail to create account");
                alert.showAndWait();
        }
    }
    
    public String GenerateId(String code)
    {   
        String id="";
        try{
            AccountList ac = new AccountList();
            ArrayList<Account> acList = ac.getAccountList();
        
            
            for (Account a:acList)
            {
                Random random = new Random();
                int threeDigitNumber = random.nextInt(900) + 100;
                id = code+threeDigitNumber;
                while (id.equals(a.getUserId()));
                {
                    threeDigitNumber = random.nextInt(900) + 100;
                    id = code+threeDigitNumber;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }        
        return id;
    }
    public boolean verifyAccount(String username, String password, String confirmPassword, String email, String phoneNumber,ToggleGroup radToggleGroup)
    {
        return verifyUsername(username)&&verifyPassword(password)
        &&verifyConfirmPassword(confirmPassword, password)
        &&verifyEmail(email)&&verifyPhoneNumber(phoneNumber)
        &&verifyRadioButtons(radToggleGroup);
    }
    public boolean verifyUsername(String username)
    {
        boolean cond=true;
        try{
            if (username.length()>=5)
            {
                AccountList ac = new AccountList();
                ArrayList<Account> acList = ac.getAccountList();
        
            
                for (Account a:acList)
                {
                    if (username.equals(a.getUserName()))
                    {
                        cond=false;
                        usernameCond.setText("*Username has already exist!");
                        break;
                    }
                    else
                    {
                        cond=true;
                        usernameCond.setText("*");
                    }
                }
            }
            else
            {
                usernameCond.setText("*Must be equal or longer than 5 character");
                cond=false;
            }
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }   
        return cond; 
    }
    public boolean verifyPassword(String password)
    {
        if (password.length()>=5)
        {
            passwordCond.setText("*");
            return true;
        }
            
        else
        {
            passwordCond.setText("*Password must be longer than 5 characters");
            return false;
        }
    }
    public boolean verifyConfirmPassword(String confirmPassword, String password)
    {
        if (confirmPassword.equals(password))
        {
            confirmPasswordCond.setText("*");
            return true;
        }
        else
        {
            confirmPasswordCond.setText("*This is different from password");
            return false;
        }
    }
    public boolean verifyPhoneNumber(String phoneNumber)
    {
        if (phoneNumber.length()==10)
        {
            phoneNumberCond.setText("*");
            return true;
        }
        else
        {
            phoneNumberCond.setText("*Invalid Phone Number");
            return false;
        }
    }
    public boolean verifyEmail(String email)
    {
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()==false)
        {
            emailCond.setText("*Invalid Email");
            return false;
        }
        else
        {
            emailCond.setText("*");
            return true;
        }
    }
    public boolean verifyRadioButtons(ToggleGroup a)
    {
        boolean cond = true;
        RadioButton selectedRadioButton = (RadioButton) a.getSelectedToggle();
        if (selectedRadioButton.getText().equals("Employee")&&verificationField.getText().equals(verificationNum))
        {
            cond=true;
        }
        else if (selectedRadioButton.getText().equals("Customer"))
        {
            cond=true;
        }
        else
        {
            cond=false;
        }
        return cond;
    }
}
