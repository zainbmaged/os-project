package trail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eng. ZAINAB
 */
public class FirstComeFirstServe {

    static Output Calc(List<Process> change) {
        List<Process> processes = deepCopy(change);
        sortArrival(processes);
        int timeline = 0;
        double avg_waiting = 0;
        double avg_turnaround = 0;
        for (Process i : processes) {
            if (timeline < i.getArrival_time()) {
                timeline = i.getArrival_time();
            }
            //i.setStartTime(timeline);
            timeline += i.getBrust_time();
            //i.setFinishTime(timeline);
            i.setWaitingTime(i.getStartTime() - i.getArrival_time());
            avg_waiting += i.getWaitingTime();
            i.setTurnaroundTime(i.getBrust_time() + i.getWaitingTime());
            avg_turnaround += i.getTurnaroundTime();
        }
        return new Output(processes, FormatDouble(avg_waiting / processes.size()),
                FormatDouble(avg_turnaround / processes.size()));
    }
     
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
     
      public static List<Process> deepCopy(List<Process> oldList) {
        List<Process> newList = new ArrayList();

        for (Process row : oldList) {
            newList.add(new Process(row.getPid(), row.getArrival_time(), row.getBrust_time(), row.getBrust_time()));
        }

        return newList;
    }
      public static double FormatDouble(double x) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(x));
    }
}
