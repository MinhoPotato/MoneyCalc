import javafx.application.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.beans.value.*;
import javafx.event.*; 
import javafx.animation.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * Money Calc takes how much money you make in a month, year, week, hour and calculates it
 * 
 * Delaney Noel
 * 1/21/2024
 */
public class moneyCalc extends Application
{
    public static void main(String[] args) 
    {
        try
        {
            // creates Stage, calls the start method
            launch(args);
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }

    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        mainStage.setTitle("Money Calculator");
        mainStage.setResizable(true);
        BorderPane root = new BorderPane();
        VBox box = new VBox();
        box.setPadding(new Insets(16));
        box.setSpacing(16);
        box.setAlignment(Pos.CENTER);
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        //set up our stage
        Label info = new Label("I Make ");
        TextField amount = new TextField();
        ComboBox currency = currencies();
        //call currencies method
        Label spacing = new Label(" in a ");
        ComboBox amountOfTime = times();
        //call amount of time method
        //basic set up for the info section that will be put into hBox
        HBox input = new HBox();
        input.setPadding(new Insets(16));
        input.setSpacing(16);
        input.setAlignment(Pos.CENTER);
        input.getChildren().addAll(info, amount, currency, spacing, amountOfTime);
        box.getChildren().add(input);
        //set up hBox then put into our VBox
        HBox workingHour = new HBox();
        workingHour.setPadding(new Insets(16));
        workingHour.setSpacing(16);
        workingHour.setAlignment(Pos.CENTER);
        Label myHours = new Label("And I work ");
        TextField amountOfHours = new TextField();
        Label amountHours = new Label(" hours in a week");
        workingHour.getChildren().addAll(myHours, amountOfHours, amountHours);
        box.getChildren().add(workingHour);
        //we need to know the amount of working hours in order to calculate everything below
        
        Button Calc = new Button("Calculate!");
        Label hour = new Label("You make X in an hour");
        Label day = new Label("You make X in a day");
        Label week = new Label("You make X in a week");
        Label month = new Label("You make X in a month");
        Label year = new Label("You make X in a year");
        box.getChildren().addAll(Calc, hour, day, week, month, year);
        //add all these to our box, these hold our results
        Calc.setOnAction(
            (ActionEvent event) ->
                {
                    try{
                        double amountNum = Double.valueOf(amount.getText());
                        String currencyString = (String) currency.getSelectionModel().getSelectedItem();
                        String TimeFrame = (String) amountOfTime.getSelectionModel().getSelectedItem();
                        
                        if(currencyString == null || TimeFrame == null){
                            throw new Exception("Null Data!");
                            //catch if currency or time Frame is null
                        }
                        
                        double hoursVal = Double.valueOf(amountOfHours.getText());
                        VBox results = calculate(amountNum, currencyString, hoursVal, TimeFrame);
                        //run this into our method and put into a new vBox
                        box.getChildren().clear();
                        //clear the old vBox
                        box.getChildren().addAll(input, workingHour, Calc, results);
                        //readd everything to the box!
                    }
                    catch(Exception e){
                        Alert error = new Alert(AlertType.ERROR);
                        error.setTitle("Error encountered!");
                        error.setHeaderText("Error encountered!");
                        error.setContentText("Please check all values are properly entered and not null!");
                        error.showAndWait();
                        //an error to catch any bad data
                    }
                }
            );
        
        //set our box to the center
        root.setCenter(box);
        //show the stage
        mainStage.show();
        mainStage.sizeToScene();
    }
    
    public ComboBox currencies(){
        ComboBox c = new ComboBox();
        
        c.getItems().add("US Dollars");
        c.getItems().add("Japanese Yen");
        c.getItems().add("Euros");
        c.getItems().add("Canadian Dollars");
        c.getItems().add("Russian Ruble");
        //some currency types
        return c;
    }
    
    public ComboBox times(){
        ComboBox h = new ComboBox();
        
        h.getItems().add("Hour");
        h.getItems().add("Day");
        h.getItems().add("Week");
        h.getItems().add("Month");
        h.getItems().add("Year");
        //our different time frames
        return h;
    }
    
    public VBox calculate(double baseRate, String currency, double hoursPerWeek, String timeFrame){
        VBox myBox = new VBox();
        myBox.setPadding(new Insets(16));
        myBox.setSpacing(16);
        myBox.setAlignment(Pos.CENTER);
        //set up a new v box to hold the results in
        
        double hourlyWage = 0;
        double hour;
        double day;
        double week;
        double month;
        double year;
        
        if(timeFrame == "Hour"){
            hourlyWage = baseRate;
        }
        else if(timeFrame == "Day"){
            hourlyWage = baseRate / (hoursPerWeek / 7);
        }
        else if(timeFrame == "Week"){
            hourlyWage = baseRate / hoursPerWeek;
        }
        else if(timeFrame == "Month"){
            hourlyWage = baseRate / ((hoursPerWeek / 7) * (365/12));
        }
        else{
            hourlyWage = ((baseRate / (365/7)) / hoursPerWeek);
        }
        //math to calculate our hourly wage
        
        hour = hourlyWage;
        day = (hoursPerWeek/7) * hourlyWage;
        week = day * 7;
        month = day * (365/12);
        year = week * (365/7);
        //calc all of our values.
        
        myBox.getChildren().add(new Label("You make " + String.format("%.2f", hour) + " " + currency + " in an hour."));
        myBox.getChildren().add(new Label("You make " + String.format("%.2f", day) + " " + currency + " in a day."));
        myBox.getChildren().add(new Label("You make " + String.format("%.2f", week) + " " + currency + " in a week."));
        myBox.getChildren().add(new Label("You make " + String.format("%.2f", month) + " " + currency + " in a month."));
        myBox.getChildren().add(new Label("You make " + String.format("%.2f", year) + " " + currency + " in a year."));
        //add all these labels to our VBox
        
        return myBox;
    }
}
