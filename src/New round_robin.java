package trail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class RoundRobin {
    public static Output calc(ArrayList<Process> processes,int QuantumTime ) {
        ArrayList<Process> executedProcesses = new ArrayList<>();
        int currentTime = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(Process::getArrival_time() ));
        ArrayList<Integer> waitingTimes = new ArrayList<>();
        ArrayList<Integer> turnaroundTimes = new ArrayList<>();

        while (!processes.isEmpty() || !queue.isEmpty()) {
            // Add arrived processes to the queue
            while (!processes.isEmpty() && processes.get(0).getArrival_time() <= currentTime) {
                queue.add(processes.remove(0));
            }

            // Execute process with first arrival 
            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Record start time for the process
                if (currentProcess.getStartTime() == 0) {
                    currentProcess.setStartTime(currentTime);
                }

                // Execute process for Quantum Time
                currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - QuantumTime);
                currentTime = currentTime +QuantumTime;

                // Add process back to the queue if it still has remaining burst time
                if (currentProcess.getRemainingBurstTime() > 0) {
                    queue.add(currentProcess);
                } else {
                    // Record finish time for the process
                    currentProcess.setFinishTime(currentTime);
                    executedProcesses.add(currentProcess);

                    // Record waiting time and turnaround time for the process
                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time() - currentProcess.getChartarrival();
                    waitingTimes.add(waitingTime);
                    int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getChartarrival();
                    turnaroundTimes.add(turnaroundTime);
                }
            } else {
                currentTime++;
            }
        }

        // Set RemainingBurstTime of each process to its BurstTime
        for (Process process : executedProcesses) {
            process.setRemainingBurstTime(process.getBrust_time());
        }

        // Calculate average waiting time and average turnaround time
        double avgWaitingTime = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double avgTurnaroundTime = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return new Output(executedProcesses, avgWaitingTime, avgTurnaroundTime);
    }
} 


    
   
    

   
