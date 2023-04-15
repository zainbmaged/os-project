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
public class Output {
     private final ArrayList<Process> processes;
    private double avg_turnaround;
    private double avg_waiting;

    public Output(ArrayList<Process> processes, double avg_waiting, double avg_turnaround) {
        this.processes = processes;
        this.avg_waiting = avg_waiting;
        this.avg_turnaround = avg_turnaround;
    }

    @Override
    public String toString() {
        return "Output{" + "\nprocesses=" + processes + "\navg_waiting=" + avg_waiting + "\navg_turnaround=" + avg_turnaround + "\n}";
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public double getAvg_turnaround() {
        return avg_turnaround;
    }

    public double getAvg_waiting() {
        return avg_waiting;
    }

}
