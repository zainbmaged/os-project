package trail;




//import java.util.ArrayList;
import java.util.*;


public class RoundRobin {
    public static Output calc(ArrayList<Process> Processes,int  QuantumTime) {
        ArrayList<Process> executedProcesses = new ArrayList<>();
        ArrayList<Process> finallist = new ArrayList<>();
        int currentTime = Processes.get(0).getArrival_time() ;
        Queue<Process> queue = new LinkedList<>();
        queue.add(Processes.remove(0));
        
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
                for (Process x :Processes )
                  {if (x.getArrival_time() <= currentTime){
                      queue.add(Processes.remove(x)); }
                   }
                 
                // Add process back to the queue if it still has remaining burst time
                if (currentProcess.getRemainingBurstTime() > 0) {
                    queue.add(currentProcess);
                }else{
                  // Record finish time for the process
                    currentProcess.setFinishTime(currentTime);
                    executedProcesses.add(currentProcess);

                    // Record waiting time and turnaround time for the process
                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time1() - currentProcess.getChartarrival();
                    waitingTimes.add(waitingTime);
                    int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getChartarrival();
                    turnaroundTimes.add(turnaroundTime);
                     }
              //burst<Quantumtime 
              }else {
                    currentTime=currentTime+currentProcess.getRemainingBurstTime();
                    currentProcess.setRemainingBurstTime(0);
                    finallist.add(currentProcess);//same burst time
                    //check for new process
                    for (Process x1 :Processes )
                     {if (x1.getArrival_time() <= currentTime){
                     queue.add(Processes.remove(x1)) ;}
                     }
                    // Record finish time for the process
                    currentProcess.setFinishTime(currentTime);
                    executedProcesses.add(currentProcess);

                    // Record waiting time and turnaround time for the process
                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getBrust_time1() - currentProcess.getChartarrival();
                    waitingTimes.add(waitingTime);
                    int turnaroundTime = currentProcess.getFinishTime() - currentProcess.getChartarrival();
                    turnaroundTimes.add(turnaroundTime);
                }
            } else {//empty queue
                if (!Processes.isEmpty()){
                currentTime= Processes.get(0).getArrival_time();
                for (Process x2 :Processes )
                  {if (x2.getArrival_time() <= currentTime){
                     queue.add(Processes.remove(x2)); }
                   }
                 }
            }
        }

        // Set RemainingBurstTime of each process with its BurstTime
        for (Process process : executedProcesses) {
            process.setRemainingBurstTime(process.getBrust_time());}
        

        // Calculate average waiting time and average turnaround time
        double avgWaitingTime = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double avgTurnaroundTime = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return new Output(finallist, avgWaitingTime, avgTurnaroundTime);
    }


    
     public static void main(String[] args) {
    ArrayList<Process> processes = new ArrayList<>();
    processes.add(new Process("p1", 0, 4, 3));
    processes.add(new Process("p2", 1, 3, 2));
 
    
   
    

    Output output = RoundRobin.calc(processes,2);

    System.out.println(output);
}
}
                
