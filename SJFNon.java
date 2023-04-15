/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shortestnonpreemptive;

import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

/**
 *
 * @author Yara Farid
 */
public class SJFNon {

    static Output runSJFNon(ArrayList<Process> processes) {
        ArrayList<Process> proc = (ArrayList<Process>) processes.clone();

        int currentTime = 0, minimum = Integer.MAX_VALUE;
        int shortest = 0;
        boolean started = false;
        boolean input = false; // should be shared variable
        while (!proc.isEmpty()) {
            if (input) {
// take input data from user and push into both dynamic lists processes and proc
                input = false;
                continue;
            }
            for (int j = 0; j < proc.size(); j++) {
                if ((proc.get(j).getArrival_time()<= currentTime) && (proc.get(j).getBrust_time() < minimum)) {
                    minimum = proc.get(j).getBrust_time();
                    shortest = j;
                    started = true;
                }
            }

            if (!started) {
                currentTime += 1;
            } else {
           //    System.out.println("Your current time = " + currentTime + " Your currrent process: " + proc.get(shortest).getPid());
                proc.get(shortest).setStartTime(currentTime);
                currentTime += proc.get(shortest).getBrust_time();
                proc.get(shortest).setFinishTime(currentTime);
                proc.get(shortest).setWaitingTime(currentTime - proc.get(shortest).getArrival_time() - proc.get(shortest).getBrust_time());
                proc.get(shortest).setTurnaroundTime(currentTime - proc.get(shortest).getArrival_time());
                minimum = Integer.MAX_VALUE;
                proc.remove(shortest);
            }
            started = false;
        }
        Collections.sort(processes, new SJFNonCmp());
        double turnAroundSJF = 0;
        for (int i = 0; i < processes.size(); i++) {
            turnAroundSJF += processes.get(i).getTurnaroundTime();
        }
        double waitingSJF = 0;
        for (int i = 0; i < processes.size(); i++) {
            waitingSJF += processes.get(i).getWaitingTime();
        }

        return new Output(processes, (waitingSJF / processes.size()),
                (turnAroundSJF / processes.size()));
    }
}
