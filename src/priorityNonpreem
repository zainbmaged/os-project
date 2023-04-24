package trail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NonPreemptivePriority {
    public static Output run(ArrayList<Process> processes) {
        ArrayList<Process> executedProcesses = new ArrayList<>();
        int currentTime = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        ArrayList<Integer> waitingTimes = new ArrayList<>();
        ArrayList<Integer> turnaroundTimes = new ArrayList<>();

        while (!processes.isEmpty() || !queue.isEmpty()) {
            // Add arrived processes to the queue
            while (!processes.isEmpty() && processes.get(0).getArrival_time() <= currentTime) {
                queue.add(processes.remove(0));
            }

            // Execute process with highest priority
            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Record start time for the process
                if (currentProcess.getStartTime() == 0) {
                    currentProcess.setStartTime(currentTime);
                }

                // Execute the process until it is completed
                while (currentProcess.getRemainingBurstTime() > 0) {
                    currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - 1);
                    currentTime++;

                    // Add arrived processes to the queue while the current process is executing
                    while (!processes.isEmpty() && processes.get(0).getArrival_time() <= currentTime) {
                        queue.add(processes.remove(0));
                    }
                }

                // Record finish time for the process
                currentProcess.setFinishTime(currentTime);
                executedProcesses.add(currentProcess);

                // Record waiting time and turnaround time for the process
                int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time() - currentProcess.getChartarrival();
                waitingTimes.add(waitingTime);
                int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getChartarrival();
                turnaroundTimes.add(turnaroundTime);
            } else {
                currentTime++;
            }
        }

        // Calculate average waiting time and average turnaround time
        double avgWaitingTime = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double avgTurnaroundTime = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return new Output(executedProcesses, avgWaitingTime, avgTurnaroundTime);
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("p1", 0, 1, 2));
        processes.add(new Process("p2", 1, 7, 6));
        processes.add(new Process("p3", 2, 3, 3));
        processes.add(new Process("p4", 3, 6, 5));
        processes.add(new Process("p5", 4, 5, 4));
        processes.add(new Process("p6", 5, 15, 10));
        processes.add(new Process("p7", 6, 8, 9));

        Output output = NonPreemptivePriority.run(processes);

        System.out.println(output);
    }
}
