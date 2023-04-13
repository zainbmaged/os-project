package application;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PreemptivePriorityScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.println("Enter the details for process " + i + ":");
            System.out.print("Priority: ");
            int priority = scanner.nextInt();
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes.add(new Process(i, priority, arrivalTime, burstTime));
        }

        scheduleProcesses(processes);
    }

    private static void scheduleProcesses(List<Process> processes) {
        int totalTime = 0;
        int completedProcesses = 0;
        int n = processes.size();
        double averageWaitingTime = 0;
        double averageTurnaroundTime = 0;

        while (completedProcesses < n) {
            int minPriority = Integer.MAX_VALUE;
            int currentIndex = -1;

            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= totalTime && processes.get(i).remainingTime > 0) {
                    if (processes.get(i).priority < minPriority) {
                        minPriority = processes.get(i).priority;
                        currentIndex = i;
                    }
                }
            }

            if (currentIndex != -1) {
                processes.get(currentIndex).remainingTime -= 1;
                totalTime += 1;

                if (processes.get(currentIndex).remainingTime == 0) {
                    completedProcesses += 1;
                    processes.get(currentIndex).turnaroundTime = totalTime - processes.get(currentIndex).arrivalTime;
                    processes.get(currentIndex).waitingTime = processes.get(currentIndex).turnaroundTime - processes.get(currentIndex).burstTime;

                    averageWaitingTime += processes.get(currentIndex).waitingTime;
                    averageTurnaroundTime += processes.get(currentIndex).turnaroundTime;
                }
            } else {
                totalTime += 1;
            }
        }

        averageWaitingTime /= n;
        averageTurnaroundTime /= n;

        System.out.printf("Average waiting time: %.2f\n", averageWaitingTime);
        System.out.printf("Average turnaround time: %.2f\n", averageTurnaroundTime);
    }
}
