public class SJF {
    static int k[];
    //private ArrayList <Integer> []finishTime1;
    //private  ArrayList<Integer> []startTime1;
    static int count=0;
    public static Output set(List<Process> processes) {
        int n = processes.size();
        k = new int[n];
        int i = 0;
        //int st=0,total = 0;
        float avgwaitingtime = 0;
        float avgturnaround = 0;
        int complete = 0, time = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        ArrayList<Process> process = new ArrayList();
        for (Process x : processes) {
            process.add(new Process(x.getPid(), x.getArrival_time(), x.getBrust_time()));
        }


        for (Process v : process) {
            k[i] = v.getBrust_time();
            i++;
        }
        while (complete != n) { int j=0 ;
        for (Process f : process)
        {
            if ((f.getArrival_time() <= time) && (k[j] < minm) && k[j] > 0) {
                if(time>0) process.get(shortest).setfinishTime1(time);
                minm = k[j];
                shortest = j;
                check = true;
                f.setstartTime1(time);
            } //if(k[j]==minm) {f.setfinishTime1(time);}
             j++;
        }
            if (check == false) {
                time++;
                continue;
            }

            // Reduce remaining time by one
           k[shortest]--;

            // Update minimum
            minm = k[shortest];
            if (minm == 0)
            {minm = Integer.MAX_VALUE;}

            // If a process gets completely
            // executed
            if (k[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = time + 1;

                process.get(shortest).setfinishTime1(finish_time);
                // Calculate waiting time

                process.get(shortest).setWaitingTime(finish_time - process.get(shortest).getBrust_time() - process.get(shortest).getArrival_time());

                if (process.get(shortest).getWaitingTime() < 0)
                { process.get(shortest).setWaitingTime(0);}
            }
            // Increment time
            time++;
        }
i=0;
        for ( Process x : process)
        {process.get(i).setTurnaroundTime(process.get(i).getBrust_time() +process.get(i).getWaitingTime());i++;
    }

        for (i = 0; i < n; i++) {
            avgwaitingtime += process.get(i).getWaitingTime();
            avgturnaround += process.get(i).getTurnaroundTime();
            System.out.println(" " + process.get(i).getPid() + "\t\t"
                    + process.get(i).getArrival_time()+ "\t\t"+ process.get(i).getBrust_time() + "\t\t " + process.get(i).getWaitingTime()
                    + "\t\t" + process.get(i).getTurnaroundTime());
        }
        avgwaitingtime=avgwaitingtime/n;
        avgturnaround=avgturnaround/n;
        System.out.println("Average waiting time = " +
                avgwaitingtime );
        System.out.println("Average turn around time = " +
               avgturnaround );


 return new Output(process,avgwaitingtime,avgturnaround);}}
