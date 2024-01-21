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

/**
 * Money Calc takes how much money you make in a month, year, week, hour and calcualtes it
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
        
        
        Label hour = new Label("You make X in an hour");
        Label day = new Label("You make X in a day");
        Label week = new Label("You make X in a week");
        Label month = new Label("You make X in a month");
        Label year = new Label("You make X in a year");
        box.getChildren().addAll(hour, day, week, month, year);
        //add all these to our box, these hold our results
        
        
        //set our box to the center
        root.setCenter(box);
        //show the stage
        mainStage.show();
        mainStage.sizeToScene();
    }
    
    public ComboBox currencies(){
        ComboBox c = new ComboBox();
        
        c.getItems().add("US Dollars");
        
        return c;
    }
    
    public ComboBox times(){
        ComboBox h = new ComboBox();
        
        h.getItems().add("Hour");
        
        return h;
    }
}
