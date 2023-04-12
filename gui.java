/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// steps to create javafx project
//1- create ordinary java project with ANT
//2- Right-click on project -> select properties -> library -> in compile -> class-path ->click on small+ sign
// ->add library javafx
//3- then in library -> in Run -> module-path ->click on small+ sign->add library javafx
package demo;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author Eng. ZAINAB
 */
public class Demo extends Application {

    /**
     * @param args the command line arguments
     */
    //---------intializing objects---------------------------------------------//
   
     Button b1;
    //-------------------------------------------------------------------------//
     public static void main(String[] args) {
    launch(args);
  }  

    @Override
    public void start(Stage primarystage) throws Exception {
        primarystage.setTitle("Scheduler CPU");
        
       
        
        
        //-------------top menue------------------------//
   
        HBox top= new HBox();
        
        
//        ///---------------top menue---------------------//
//        
//        //-------------side menue------------------------//
//   
          VBox center= new VBox();
          center.setPadding(new Insets(10,10,10,10));

       
        
//        
//        ///---------------side menue---------------------//
//        //-------------bottom menue------------------------//
   
        VBox bottom= new VBox();
        bottom.setPadding(new Insets(10,10,10,10));
        
        ///---------------bottom menue---------------------//
        
        /// --------------------------Gride pane-----------------------------//
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(8);
        pane.setHgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        //process field
        Label process = new Label("Enter Processes' Number :");
        GridPane.setConstraints(process, 0, 1);
        TextField pfield = new TextField();
        GridPane.setConstraints(pfield, 0, 2);
        // schadualing mechanism drop menu
        Label sml = new Label("Choose Schadualing option:");
        GridPane.setConstraints(sml, 0, 3);
        ChoiceBox<String> sm = new ChoiceBox();
        sm.getItems().addAll("live schadualing","Immediatly run all");
        sm.setValue("live schadualing");
        GridPane.setConstraints(sm, 0,4 );
        //schadualing Type
        Label stl = new Label("Choose Schadualing Type:");
        GridPane.setConstraints(stl, 0, 5);
        ChoiceBox<String> st = new ChoiceBox();
        st.getItems().addAll("FCFS","SJF-Preemptive","SJF-nonPreemptive","Priority-Preemptive","Priority-nonPreemptive","Round-Robin");
        st.setValue("FCFS");
        GridPane.setConstraints(st, 0,6 );
        Button bs = new Button("Submit");
        GridPane.setConstraints(bs, 1,6 );
        
        
        Button ba = new Button("Run");
        GridPane.setConstraints(ba, 1,9 );
        ba.setDisable(true);
        
        
        //add a process dynamicaly
        
        //-------------------------------------table properties-------------------------------------------//
        // process id
        TableColumn<Process, String> p_col =new TableColumn("Process_id");
        TableView table = new TableView<>();
        p_col.setMaxWidth(300);
        p_col.setCellValueFactory(new PropertyValueFactory("pid"));
        // process burst time
        TableColumn<Process, String> p_col2 =new TableColumn("Burst-Time");
        p_col2.setMaxWidth(100);
        p_col2.setCellValueFactory(new PropertyValueFactory("brust_time"));
        table.getColumns().addAll(p_col,p_col2);
        
        bs.setOnAction(e-> {
        
        if(st.getValue()== "Round-Robin"){
        Label r = new Label("Please set time quntam:");
        TextField tfield = new TextField();
        GridPane.setConstraints(r, 0, 7);
        
        GridPane.setConstraints(tfield, 0, 8);
        pane.getChildren().addAll(r,tfield);
        
        
        }ba.setDisable(false);
        bs.setDisable(true);
        });
        
        ba.setOnAction(e -> { 
           if( is_int(pfield.getText()) && pfield.getText()!="" ){
               if(st.getValue()== "Priority-Preemptive" || st.getValue()== "Priority-nonPreemptive" ){
        // process prioroty
        TableColumn<Process, String> p_col3 =new TableColumn("Priority");
        p_col3.setMaxWidth(100);
        p_col3.setCellValueFactory(new PropertyValueFactory("Priority"));
        table.getColumns().addAll(p_col3);
        }
               
              
            table.setItems(getProcess(Integer.parseInt(pfield.getText())));
            center.getChildren().addAll(table);
           }
            
           ba.setDisable(true);
        
        });
        
        ///--------------------------------------------for th Gantt chart-------------// 
        Label gantt = new Label("Gantt Chart:");
        Rectangle chart = new Rectangle();
        chart.setWidth(700);
        chart.setFill(Color.WHITE);
        chart.setStroke(Color.BLACK); 
        chart.setHeight(100);
        bottom.getChildren().addAll(gantt,chart);
        /// --------------------------Gride pane-----------------------------//
        
        //-------------Border pane to divide the seen------------------------//
        BorderPane bpane = new BorderPane();
        bpane.setTop(top);
        bpane.setBottom(bottom);
        bpane.setLeft(pane);
        bpane.setCenter(center);
        
        ///---------------Border pane to divide the seen---------------------//
        pane.getChildren().addAll(process,pfield,sm, sml,st,stl, ba,bs);
        Scene scene =new Scene(bpane,800, 500);
        primarystage.setScene(scene);
        primarystage.show();
    }
    ////--------------------------defining functoions------------------------//
    
    private boolean is_int(String s){
        try{
            int n = Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error:"+ s+ " is not a number");
            return false;
        }
    }
    ///function for adding intial processes 
    public ObservableList<Process> getProcess(int n){
        ObservableList<Process> process = FXCollections.observableArrayList();
        for(int i=0;i<n;i++){
        process.add(new Process("P"+i));
        }
       return process;
    }
    
    
}
