package application;

import java.util.PriorityQueue;
import java.util.Scanner;

class Process {
    int id;
    int priority;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    Process(int id, int priority, int arrivalTime, int burstTime) {
        this.id = id;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}
