/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trail;

import java.util.ArrayList;

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
    private ArrayList<Integer> finishTime1;
     private int RemainingTime;
     private   ArrayList<Integer> startTime1; 
     private int Chartarrival;
    public Process(){
        this.pid="";
        this.brust_time=0;
        this.Priority=0;
        this.arrival_time=0;
    }

     private Process(String processName, int arrivalTime, int burstTime, int startTime, int finishTime, int priorityLevel, int waitingTime, int turnaroundTime) {
        this.pid = processName;
        this.arrival_time = arrivalTime;
        this.brust_time = burstTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.Priority = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        this.Chartarrival = arrivalTime;
        this.RemainingTime = burstTime;
    }

    public Process(String processName, int arrivalTime, int burstTime, int priorityLevel) {
        this(processName, arrivalTime, burstTime, 0, 0, priorityLevel, 0, 0);
    }

    public Process(String processName, int arrivalTime, int burstTime) {
        this(processName, arrivalTime, burstTime, 0, 0, 0, 0, 0);
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
   public int getRemainingBurstTime() {
        return RemainingTime;
    }

    public void setRemainingBurstTime(int RemainingTime) {
        this.RemainingTime = RemainingTime;
    }
    public void setfinishTime1(int a) {
        finishTime1=new ArrayList<Integer>();
        finishTime1.add(a);
    }
    public ArrayList<Integer> getfinishTime1() {

        return finishTime1;
    }
    public ArrayList<Integer> getstartTime1() {

        return startTime1;
    }
    public void setstartTime1(int a) {
        startTime1=new ArrayList<Integer>();
        startTime1.add(a);

    }
    public int getChartarrival() {
        return Chartarrival;
    }

    public void setChartarrival(int Chartarrival) {
        this.Chartarrival = Chartarrival;
    }

}
