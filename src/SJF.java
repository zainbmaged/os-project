import java.util.*;


public class SJF {
    static int flag[];
    static int k[];

    public static Output set(List<Process> processes) {
        int n = processes.size();
        flag = new int[n];
        k = new int[n];
        int i = 0, st = 0, total = 0;
        float avgwaitingtime = 0;
        float avgturnaround = 0;

        // int completetime[] = new int[n];
        List<Process> process = new ArrayList();
        for (Process x : process) {
            process.add(new Process(x.getPid(), x.getArrival_time(), x.getBrust_time()));
        }
        //Collections.sort(process, new Comparator<Process>() {
        //  public int compare(Process p1, Process p2) {
        //    return p1.getBrust_time() - p2.getBrust_time();
        // }
        // });

        for (Process v : process) {
            k[i] = v.getBrust_time();
            flag[i] = 0;
            i++;
        }

        while (true) {
            int min = 99, c = n, j = 0;
            if (total == n)
                break;

            for (Process f : process) {
                if ((f.getArrival_time() <= st) && (flag[j] == 0) && (f.getBrust_time() < min)) {
                    min = f.getBrust_time();
                    c = i;
                }
                j++;
            }

            if (c == n)
                st++;
            else {
                int g = process.get(c).getBrust_time();
                process.get(c).setBrust_time(g - 1);
                st++;
                if (process.get(c).getBrust_time() == 0)
                {
                    process.get(c).setBrust_time(st);
                    flag[c] = 1;
                    total++;
                }
            }
        }
        i = 0;
        for (Process r : process) {
            r.setTurnaroundTime(r.getFinishTime() - r.getArrival_time());
            r.setWaitingTime(r.getTurnaroundTime() - k[i]);
            avgwaitingtime += r.getWaitingTime();
            avgturnaround += r.getTurnaroundTime();
            i++;
        }


        while (true) {
            int min = 99, c = n;
            if (total == n)
                break;
            i = 0;
            for (Process o : process) {
                if ((o.getArrival_time() <= st) && (flag[i] == 0) && (o.getBrust_time() < min)) {
                    min = o.getBrust_time();
                    c = i;
                }
                i++;
            }

            if (c == n)
                st++;
            else {
                int t = process.get(c).getBrust_time();
                process.get(c).setBrust_time(t - 1);
                st++;
                if (process.get(c).getBrust_time() == 0) {
                    process.get(c).setFinishTime(st);
                    flag[c] = 1;
                    total++;
                }
            }
        }
        return new Output((ArrayList<Process>) process, (avgwaitingtime / process.size()),
                (avgturnaround / process.size()));
    }
}
