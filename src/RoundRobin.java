package trail;




//import java.util.ArrayList;
import java.util.*;


public class RoundRobin {
    public static Output calc(ArrayList<Process> Processes,int  QuantumTime) {
        ArrayList<Process> executedProcesses = new ArrayList<>();
        ArrayList<Process> finallist = new ArrayList<>();
        int i=0;
        int currentTime = Processes.get(0).getArrival_time() ;
        Queue<Process> queue = new LinkedList<>();
      //  queue.add(Processes.remove(0));
        
        ArrayList<Integer> waitingTimes = new ArrayList<>();
        ArrayList<Integer> turnaroundTimes = new ArrayList<>();
        
       while (!Processes.isEmpty() || !queue.isEmpty()) { 
        
            // Execute process with first arrival 
            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Record start time for the process
                if (currentProcess.getStartTime() == 0) {
                    currentProcess.setStartTime(currentTime);
                }
              if(currentProcess.getRemainingBurstTime() >= QuantumTime) {
                // Execute process for quantum time 
                currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - QuantumTime );
                currentTime += QuantumTime;
                currentProcess.setBrust_time(QuantumTime) ;
                finallist.add(currentProcess);
          
               // add processes to ready queue
                while (!Processes.isEmpty() && Processes.get(0).getArrival_time() <= currentTime) {
                  Processes.get(0).setRemainingBurstTime( Processes.get(0).getBrust_time());
             
                  queue.add(Processes.remove(0));
             }
                 
                // Add process back to the queue if it still has remaining burst time
                if ((currentProcess.getRemainingBurstTime() > 0)   &&  (currentProcess.getRemainingBurstTime() <  QuntamTime)) {
                       }
                else if ((currentProcess.getRemainingBurstTime() > 0)   &&  !(currentProcess.getRemainingBurstTime() <  QuntamTime)) {
                        queue.add(currentprocess);   }
                else{
                  // Record finish time for the process
                    currentProcess.setFinishTime(currentTime);
                    executedProcesses.add(currentProcess);

                    // Record waiting time and turnaround time for the process
                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time1() - currentProcess.getArrival_time();
                    waitingTimes.add(waitingTime);
                    int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getArrival_time();
                    turnaroundTimes.add(turnaroundTime);
                     }
              //burst<Quantumtime 
              }else {
                    currentTime += currentProcess.getRemainingBurstTime();
                    currentProcess.setRemainingBurstTime(0);
                    //currentProcess.setBrust_time(currentProcess.getRemainingBurstTime());
                    finallist.add(currentProcess);//same burst time
                    
                    // Record finish time for the process
                    currentProcess.setFinishTime(currentTime);
                    executedProcesses.add(currentProcess);
                    //check for new process
                    while (!Processes.isEmpty() && Processes.get(0).getArrival_time() <= currentTime) {
                    Processes.get(0).setRemainingBurstTime( Processes.get(0).getBrust_time());
                    queue.add(Processes.remove(0));
                       }
                    

                    // Record waiting time and turnaround time for the process
                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time1() - currentProcess.getArrival_time();
                    waitingTimes.add(waitingTime);
                    int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getArrival_time();
                    turnaroundTimes.add(turnaroundTime);
                }
            } else {//empty queue
                while (!Processes.isEmpty() && Processes.get(0).getArrival_time() <= currentTime) {
                Processes.get(0).setRemainingBurstTime( Processes.get(0).getBrust_time());
                queue.add(Processes.remove(0));
                    }
            }
        }

     /*   // Set RemainingBurstTime of each process with its BurstTime
        for (Process process : finallist) {
            process.setRemainingBurstTime(process.getBrust_time());}
        */

        // Calculate average waiting time and average turnaround time
        double avgWaitingTime = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double avgTurnaroundTime = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return new Output(finallist, avgWaitingTime, avgTurnaroundTime);
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();

        processes.add(new Process("p1", 0, 5));
        processes.add(new Process("p2", 1, 3));
        processes.add(new Process("p3", 2, 1));
        processes.add(new Process("p4", 3, 2));
        processes.add(new Process("p5", 4, 3));

        Output output = RoundRobin.calc(processes, 2);

        System.out.println("Final list of processes:");
        for (Process process : output.getProcesses()) {
            System.out.println(process.getPid());
        }
        System.out.println("Average waiting time: " + output.getAvg_waiting());
        System.out.println("Average turnaround time: " + output.getAvg_turnaround());
        
    }
}
