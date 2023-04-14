/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package trail;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import java.util.ResourceBundle;
import java.util.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class FXMLController implements Initializable{
    // intializing time
    int time =0;
   Timeline timeline;
    String st;
    String sm;
   
    
    @FXML
    private HBox hbox;
     @FXML
    private HBox hbox1;
    // user choices
    @FXML
    private ChoiceBox<String> SM;

    @FXML
    private ChoiceBox<String> ST;
     @FXML
    private Label AvgTurnaroundTimeLabel;

    @FXML
    private Label AvgWaitingTimeLabel;

    @FXML
    private TextField BurstTime;

    @FXML
    private TextField Priority;
       @FXML
    private TextField processName;
    //Submission Button
    @FXML
    private Button Submit;
    //Run Button
    @FXML
    private Button Run;
     @FXML
     
    private Button add;
     @FXML
    private TextField p_field;
      @FXML
    private Button dynamic;
    //Process Table
    @FXML
    private TableView<Process> table;
    @FXML
    private TableColumn<Process, String> process;
    @FXML
    private TableColumn<Process, Integer> arrival_time;

    @FXML
    private TableColumn<Process, Integer> brust_time;
  
    
    private TableColumn<Process, Integer> p_col3 =new TableColumn("Priority");
        
    
    ObservableList<Process> data = FXCollections.observableArrayList();
    @FXML
    private TextField QuantumTimeTextField;
    @FXML
    private Label quntl;
     @FXML
    private Label priol;
    @FXML
    void submit(MouseEvent event) {
        //saving user choices
        
        sm =SM.getSelectionModel().getSelectedItem();
        st =ST.getSelectionModel().getSelectedItem();
         if (st.equals("Round-Robin")) {
            QuantumTimeTextField.setDisable(false);
            quntl.setDisable(false);
        } else {
            QuantumTimeTextField.setDisable(true);
            quntl.setDisable(true);
        
        }
         if (st == "Priority-Preemptive" || st == "Priority-nonPreemptive") {
               Priority.setDisable(false);
        priol.setDisable(false);
        } else {
              Priority.setDisable(true);
        priol.setDisable(true);
        
        }
 
        
        
        
        
        /// setting table 
        table.setEditable(true);
        
        // process id
       
        process.setMaxWidth(300);
        process.setCellValueFactory(new PropertyValueFactory("pid"));
         //for the process arrival_time
        
        arrival_time.setCellValueFactory(new PropertyValueFactory("arrival_time"));
        // process burst time
        
        brust_time.setCellValueFactory(new PropertyValueFactory("brust_time"));
        brust_time.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        if(st == "Priority-Preemptive" || st == "Priority-nonPreemptive" ){
        // process prioroty
        
        p_col3.setCellValueFactory(new PropertyValueFactory("Priority"));
        p_col3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        table.getColumns().addAll(p_col3);
       
        }
        else{
            table.getColumns().remove(p_col3);
        }
        //intializing process column
        if( is_int(p_field.getText())){
            int n = Integer.parseInt(p_field.getText());
            
            table.setItems(getProcess(n));
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SM.getItems().addAll("live schadualing","Immediatly run all");
        SM.setValue("live schadualing");
        ST.getItems().addAll("FCFS","SJF-Preemptive","SJF-nonPreemptive","Priority-Preemptive","Priority-nonPreemptive","Round-Robin");
        ST.setValue("FCFS");
        
        QuantumTimeTextField.setDisable(true);
        quntl.setDisable(true);
        Priority.setDisable(true);
        priol.setDisable(true);

    }
   
    
    
    @FXML
    void AddProcessAction(MouseEvent event) {
        ObservableList<Process> currentTableData = table.getItems();
        String currentpid = processName.getText();

        
        int i =0;
        for (Process process : currentTableData) {
            if(process.getPid().equals( currentpid)) {
                process.setBrust_time(Integer.parseInt(BurstTime.getText()));
                
                if(st == "Priority-Preemptive" || st == "Priority-nonPreemptive" ){

                process.setPriority(Integer.parseInt(Priority.getText()));
                }
                data.set(i, process);
                //data.add(process);
                table.setItems(currentTableData);
                table.refresh();
                break;
            }

            
              i++;         
        }

    }
       @FXML
    void dynamic(MouseEvent event) {
        timeline.pause();
             try {
            Process r;
            if(st == "Priority-Preemptive" || st == "Priority-nonPreemptive" ){

                 r = new Process(processName.getText(), time,
                    Integer.parseInt(BurstTime.getText()),Integer.parseInt(Priority.getText()));
                
                }
            else{
                 r = new Process(processName.getText(), time,
                    Integer.parseInt(BurstTime.getText()));
                 //Priority.setText("");
            }
            data.add(r);
            
            processName.setText("");
            
            //BurstTime.setText("");
            table.setItems(data);
        } catch (NumberFormatException e) {
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }
    }
    @FXML
    void RUN(MouseEvent event) {
           timeline = new Timeline(new KeyFrame(Duration.seconds(1),e->{
           time++;
            
        }));
        timeline.play();
        timeline.setCycleCount(Animation.INDEFINITE); // loop forever
        
        if (data.isEmpty()) {
            return;
        }
        if (null == st) {
            System.out.println("ERROR");
        } else {
            switch (st) {
                case "FCFS":
                    List<Process> l =change(data);
                    Output fcfs = FirstComeFirstServe.Calc(l);
                    draw(l);
                    AvgWaitingTimeLabel.setText("Avg Waiting Time: " +fcfs.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText("Avg Turnaround Time:  "+fcfs.getAvg_turnaround() + "");
                    break;
                case "SJF-nonPreemptive":
                   // Output sjf = ShortestJobFirst.Calc(change(data));
//                    AvgWaitingTimeLabel.setText(sjf.getAvg_waiting() + "");
//                    AvgTurnaroundTimeLabel.setText(sjf.getAvg_turnaround() + "");
                    break;
                case "SJF-Preemptive":
                  //  Output srtf = ShortestRemainingTime.Calc(change(data));
//                    AvgWaitingTimeLabel.setText(srtf.getAvg_waiting() + "");
//                    AvgTurnaroundTimeLabel.setText(srtf.getAvg_turnaround() + "");
                    break;
                case "Round-Robin":
//                    int q;
//                    try {
//                        q = Integer.parseInt(QuantumTimeTextField.getText());
//                    } catch (NumberFormatException e) {
//                        break;
//                    }
                   // Output rr = RoundRobin.Calc(change(data), q);
//                    AvgWaitingTimeLabel.setText(rr.getAvg_waiting() + "");
//                    AvgTurnaroundTimeLabel.setText(rr.getAvg_turnaround() + "");
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
      
    }
    private boolean is_int(String s){
        try{
            int n = Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error:"+ s+ " is not a number");
            return false;
        }
    }
    //for changing observable list into list
    private List<Process> change(ObservableList<Process> input) {
        List<Process> p = new ArrayList<>();
        input.forEach((i) -> {
            p.add(new Process(i.getPid(), i.getArrival_time(), i.getBrust_time()));
        });
        return p;
    }
    ///function for adding intial processes 
    public ObservableList<Process> getProcess(int n){
        ObservableList<Process> process = FXCollections.observableArrayList();
        for(int i=0;i<n;i++){
        process.add(new Process(""+(i+1),0));
        data.add(new Process(""+(i+1),0));
        }
       return process;
    }
   
    ////---------------------------------functions for live schaudaling----------//
    public void live(List<Process> process , int time) {
    
        for(Process p:process){
            if(p.getArrival_time()<=time){
                p.setBrust_time(p.getBrust_time()-1);
                
            }
        }
    }
    public Rectangle draw_chart( Process p ) {
    
        
                Rectangle rectangle = new Rectangle(p.getBrust_time()*10,40);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK); 
                
                
          return rectangle;
    }
   public void draw( List<Process> process ) {
    
        
                for (Process p :process){
                        hbox.getChildren().add(draw_chart(p));
                        Text text1 = new Text(40, 40,"P"+p.getPid());
                        text1.setFont(Font.font("Courier", FontWeight.BOLD, 
                        FontPosture.ITALIC, 8));
                        Rectangle rectangle = new Rectangle(p.getBrust_time()*10-8,40);
                        rectangle.setFill(Color.WHITESMOKE);
                        rectangle.setStroke(null);
                        hbox1.getChildren().addAll(text1,rectangle);
                        
                        
                    }
    }
}
    
