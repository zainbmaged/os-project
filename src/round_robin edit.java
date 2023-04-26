import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DecimalFormat;


public class RoundRobin {
  
  
  
  public static void sortArrival(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getArrival_time() == ((Process) o2).getArrival_time()) {
                return 0;
            } else if (((Process) o1).getArrival_time() < ((Process) o2).getArrival_time()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static void sortBrust(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getBrust_time() == ((Process) o2).getBrust_time()) {
                return 0;
            } else if (((Process) o1).getBrust_time() < ((Process) o2).getBrust_time()) {
                return -1;
            } else {
                return 1;
            }
        });
    }


    private static List<Process> filter(List<Process> pro, int arrival_time) {
        List<Process> processes_out = new ArrayList<>();
        pro.stream().filter((i) -> (i.getArrival_time() <= arrival_time && i.getRemainingBurstTime() != 0)).forEachOrdered((i) -> {
            processes_out.add(i);
        });
        sortBrust(processes_out);
        return processes_out;
    }

    private static void sortchartarrivale(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getChartarrival() == ((Process) o2).getChartarrival()) {
                return 0;
            } else if (((Process) o1).getChartarrival() < ((Process) o2).getChartarrival()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static Output Calc(List<Process> input_process, int QuantumTime) {
        int time = input_process.get(0).getArrival_time();
        List<Process> tempList1 = new ArrayList<>();
        List<Process> FinalList = new ArrayList<>();
        
        while (true) {
            List<Process> tempList2 = filter(input_process, time);
            if (tempList1.size() == input_process.size()) {
                break;
            }
            if (tempList2.isEmpty()) {
                time++;
            } else {
                sortchartarrivale(tempList2);
                if (tempList2.get(0).getRemainingBurstTime() >= QuantumTime) {
                    time += QuantumTime;
                    Finallist.add(tempList2.get(0));
                    Finallist.get(0).setBrust_time(QuantumTime);
                    tempList2.get(0).setRemainingBurstTime(tempList2.get(0).getRemainingBurstTime() - QuantumTime);
                    tempList2.get(0).setChartarrival(time);

                    if (tempList2.get(0).getRemainingBurstTime() == 0) {
                        tempList2.get(0).setFinishTime(time);
                        tempList1.add(tempList2.get(0));
                    }
                } else {
                    time += tempList2.get(0).getRemainingBurstTime();
                    int remain_time=tempList2.get(0).getRemainingBurstTime();
                    tempList2.get(0).setRemainingBurstTime(0);
                    Finallist.add(tempList2.get(0));
                    Finallist.get(0).setBrust_time(remain_time);
                    if (tempList2.get(0).getRemainingBurstTime() == 0) {
                        tempList2.get(0).setFinishTime(time);
                        tempList1.add(tempList2.get(0));
                    }
                }
            }
        }
        
        for (int i = 0; i < tempList1.size(); i++) {
            tempList1.get(i).setTurnaroundTime(tempList1.get(i).getFinishTime() - tempList1.get(i).getArrival_time());
            tempList1.get(i).setWaitingTime(tempList1.get(i).getTurnaroundTime() - tempList1.get(i).getBrust_time());
        }
        sortArrival(tempList1);
        double avg_w = 0;
        double avg_t = 0;
        for (Process i : tempList1) {
            avg_w += i.getWaitingTime();
            avg_t += i.getTurnaroundTime();
        }
        return new Output((ArrayList<Process>) Finallist, FormatDouble(avg_w / tempList1.size()), FormatDouble(avg_t / tempList1.size()));
    }
    public static double FormatDouble(double x) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(x));
    }
}