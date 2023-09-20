import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginScreenController {
    public Account acc;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() throws Exception{
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add your login validation logic here
        boolean isValid = validateLogin(username, password);

        if (isValid) {
            // On successful login, switch to the Home Screen
            Main.loginName = username;
            Main.accountType = acc.getAccountType();
            if (Main.accountType.equals("Employee"))
            {
                Main.setRoot("EmployeeScreen");
            }
            if (Main.accountType.equals("Customer"))
            {
                Main.setRoot("CustomerScreen");
            }
            
        } else {
            // Show an error message or handle unsuccessful login
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You type the wrong password or username.");
            alert.showAndWait();
        }
    }
    @FXML
    public void handleRegisterationScreen()
    {
        Main.setRoot("RegisterationScreen");
    }

    private boolean validateLogin(String username, String password) throws Exception{
        boolean cond = false;
        AccountList ac = new AccountList();
        ArrayList<Account> arr = ac.getAccountList();
        for (Account a:arr)
        {
            if (username.equals(a.getUserName())&&password.equals(a.getPassword()))
            {
                cond=true;
                acc=a;
                break;
            }
        }
        return cond;
    }
}
