/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trail;

/**
 *
 * @author Eng. ZAINAB
 */
public class Process {
    private String pid;
    private int brust_time;
    private int Priority;
    private int arrival_time;
    private int waitingTime;
    private int turnaroundTime;
    private int startTime;
    private int finishTime;
    
    public Process(){
        this.pid="";
        this.brust_time=0;
        this.Priority=0;
        this.arrival_time=0;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    //constructor with just id and arrival time set to 0
    public Process(String pi, int a){
        this.pid=pi;
        this.arrival_time=a;
        
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }
    //constructor without priority
    public Process(String pi,int a,int bt){
        this.pid=pi;
        this.brust_time=bt;
        this.arrival_time=a;
        
    }

    //constructor with priority
    public Process(String pi,int bt,int a, int p){
        this.pid=pi;
        this.brust_time=bt;
        this.arrival_time=a;
        this.Priority=p;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getBrust_time() {
        return brust_time;
    }

    public void setBrust_time(int brust_time) {
        this.brust_time = brust_time;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int Priority) {
        this.Priority = Priority;
    }
   
}
