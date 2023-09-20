import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    public static String loginName;
    public static String accountType;

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        showLoginScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showLoginScreen() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("LoginScreen.fxml"));
            primaryStage.setTitle("Login Screen");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void setRoot(String fxmlFile) {
        try {
            System.out.println(("Main setRoot, fxmlFile=" + fxmlFile));
            if (fxmlFile == "CustomerScreen"){
                System.out.println(("Main setRoot, fxmlFile=" + fxmlFile));
                Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile + ".fxml"));
                primaryStage.setScene(new Scene(root, 550, 300));
                primaryStage.setTitle("Hotel Reservation - TrongNhan, WanTae");
            } else if (fxmlFile == "EmployeeScreen"){
                /* 
                System.out.println(("Main setRoot, fxmlFile=" + fxmlFile));
                Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile + ".fxml"));
                primaryStage.setScene(new Scene(root, 400, 300));
                primaryStage.setTitle(fxmlFile);
                */

                InitializeData.InitializeData();
                
                GraphicalCalendar gc = new GraphicalCalendar();

                Scene scene = new Scene(gc);
                primaryStage.setTitle("Hotel Reservation - TrongNhan, WanTae");
                primaryStage.setScene(scene);
                primaryStage.show();


            } 
            else if (fxmlFile =="RegisterationScreen")
            {
                Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile + ".fxml"));
                primaryStage.setScene(new Scene(root));
            }
            else {
                System.out.println(("set root = " + fxmlFile));
                 //Main.primaryStage = primaryStage;
                showLoginScreen();
            }
        } catch (IOException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

}