import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class GraphicalCalendar extends BorderPane {
   private Calendar currentMonth;

   public int cDay = 0;
   public int cMonth = 0;

   public GraphicalCalendar() {
      currentMonth = new GregorianCalendar();
      currentMonth.set(Calendar.DAY_OF_MONTH, 1);

      drawCalendar();/*from ww  w. j  ava2s  . c  o  m*/

     

   }

   private void drawCalendar() {
      drawHeader();
      drawBody();
      drawFooter();
   }

   

   private void drawHeader() {
      String monthString = getMonthName(currentMonth.get(Calendar.MONTH));
      String yearString = String.valueOf(currentMonth.get(Calendar.YEAR));
      Text tHeader = new Text(monthString + ", " + yearString);
      tHeader.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
      //tHeader.setStrikethrough(true);

      Text loginname = new Text("ID:" + Main.loginName.toString());
      loginname.setFont(Font. font("Verdana", FontPosture.ITALIC, 15));
      loginname.setFill(Color.GRAY);

      Button btnLogout = new Button("Logout");

      btnLogout.setOnAction((e) -> {
         Main.setRoot("LoginScreen");
       });



      BorderPane header = new BorderPane();
      header.setLeft(loginname);
      //BorderPane.setAlignment(loginname, Pos.CENTER_RIGHT);
      header.setCenter(tHeader);
      header.setRight(btnLogout);

    
      setTop(header);
      setAlignment(header, Pos.CENTER);
      
   }

   private void drawBody() {
      GridPane gpBody = new GridPane();
      gpBody.setHgap(3);
      gpBody.setVgap(3);
      gpBody.setAlignment(Pos.CENTER);
      //gpBody.setMinHeight(500);

     

      // Draw days of the week
      for (int day = 1; day <= 7; day++) {
         Text tDayName = new Text(getDayName(day));
         tDayName.setFont(Font. font("Verdana",  15));
         gpBody.getColumnConstraints().add(new ColumnConstraints(100));
         gpBody.add(tDayName, day - 1, 0);
      }

      // Draw days in month
      int month = currentMonth.get(Calendar.MONTH);
      int currentDay = currentMonth.get(Calendar.DAY_OF_MONTH);
      int daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
      int dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
      int row = 1;
      //gpBody.getRowConstraints().add(new RowConstraints(100));

      System.out.println("currentDay=" + currentDay + " currentMonth=" + month + " dayOfWeek=" + dayOfWeek);
      for (int i = currentDay; i <= daysInMonth; i++) {
         if (dayOfWeek == 8) {
            dayOfWeek = 1;
            row++;
            gpBody.getRowConstraints().add(new RowConstraints(75));
         }

         System.out.println("current Month=" + month + " currentDay=" + currentDay);
         String txt = InitializeData.msg[month + 1][currentDay];
         //Text tDate = new Text(String.valueOf(currentDay) + "\nKIM\n" + "\nRoom1 ");
         cDay = currentDay;
         cMonth = month;
         Button button = new Button();
         button.setMaxSize(150, 200);
         button.setText(String.valueOf(currentDay) + "\n" + txt);
         //button.setOnAction( (ActionEvent e) -> {dayDetail(e, currentDay);} );
         button.setOnAction( (ActionEvent e) -> {

            System.out.println(e.toString() );
            //System.out.println( );
            System.out.println("date=month" + month + " day=" + cDay);
            //String txt = InitializeData.msg[cMonth+1][cday];

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
               
               alert1.setTitle("Day Detail Info");
               alert1.setHeaderText(null);
               alert1.setContentText(txt);
               alert1.showAndWait();
            

         } );

 
         
         gpBody.add(button, dayOfWeek - 1, row);
         currentDay++;
         dayOfWeek++;
      }
      gpBody.getRowConstraints().add(new RowConstraints(100));

      // Draw previous month days
      dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
      if (currentDay != 1) {
         Calendar prevMonth = getPreviousMonth(currentMonth);
         int daysInPrevMonth = prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
         for (int i = dayOfWeek - 2; i >= 0; i--) {
            Text tDate = new Text(String.valueOf(daysInPrevMonth));
            tDate.setFill(Color.GRAY);
            gpBody.add(tDate, i, 1);
            daysInPrevMonth--;
         }
      }
      
      gpBody.getRowConstraints().add(new RowConstraints(100));

      // Draw next month days
      currentMonth.set(Calendar.DAY_OF_MONTH, currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
      dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek != 7) {
         int day = 1;
         for (int i = dayOfWeek; i < 7; i++) {
            Text tDate = new Text(String.valueOf(day));
            tDate.setFill(Color.GRAY);
            gpBody.add(tDate, i, row);
            day++;
         }
      }

      setCenter(gpBody);
      setMargin(gpBody, new Insets(30));
   }

   private void drawFooter() {
      Button btPrev = new Button("Prev");
      Button btNext = new Button("Next");

      btPrev.setOnAction(e -> previous());
      btNext.setOnAction(e -> next());

      HBox hbFooter = new HBox(10);
      hbFooter.getChildren().addAll(btPrev, btNext);
      hbFooter.setAlignment(Pos.CENTER);

      setBottom(hbFooter);
      setMargin(hbFooter, new Insets(15));
   }

   /* 
   private void dayDetail(Object e, int cday) {
      System.out.println(e.toString() );
      //System.out.println( );
      System.out.println("date=month" + cMonth + " day=" + cday);
      String txt = InitializeData.msg[cMonth+1][cday];

      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle(cday + " Day Detail Info");
         alert1.setHeaderText(null);
         alert1.setContentText(txt);
         alert1.showAndWait();
   }
   */


   private void previous() {
      getChildren().clear();
      currentMonth = getPreviousMonth(currentMonth);
      drawCalendar();
   }

   private void next() {
      getChildren().clear();
      currentMonth = getNextMonth(currentMonth);
      drawCalendar();
   }

   private GregorianCalendar getPreviousMonth(Calendar cal) {
      int cMonth = cal.get(Calendar.MONTH);
      int pMonth = cMonth == 0 ? 11 : cMonth - 1;
      int pYear = cMonth == 0 ? cal.get(Calendar.YEAR) - 1 : cal.get(Calendar.YEAR);
      return new GregorianCalendar(pYear, pMonth, 1);
   }

   private GregorianCalendar getNextMonth(Calendar cal) {
      int cMonth = cal.get(Calendar.MONTH);
      int pMonth = cMonth == 11 ? 0 : cMonth + 1;
      int pYear = cMonth == 11 ? cal.get(Calendar.YEAR) + 1 : cal.get(Calendar.YEAR);
      return new GregorianCalendar(pYear, pMonth, 1);
   }

   private String getDayName(int n) {
      StringBuilder sb = new StringBuilder();
      switch (n) {
      case 1:
         sb.append("Sunday");
         break;
      case 2:
         sb.append("Monday");
         break;
      case 3:
         sb.append("Tuesday");
         break;
      case 4:
         sb.append("Wednesday");
         break;
      case 5:
         sb.append("Thursday");
         break;
      case 6:
         sb.append("Friday");
         break;
      case 7:
         sb.append("Saturday");
      }
      return sb.toString();
   }

   private String getMonthName(int n) {
      String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
      return monthNames[n];
   }
}



