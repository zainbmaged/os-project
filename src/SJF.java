public class SJF {
    static int k[];

    public static Output set(List<Process> processes) {
        int n = processes.size();
        k = new int[n];
        int i = 0;
        //int st=0,total = 0;
        float avgwaitingtime = 0;
        float avgturnaround = 0;
        int complete = 0, time = 0;
        int shortest = 0, finish_time;
        boolean check = false;

        ArrayList<Process> process = new ArrayList();
        ArrayList<Process> trial = new ArrayList();
        for (Process x : processes) {
            process.add(new Process(x.getPid(), x.getArrival_time(), x.getBrust_time()));
        }


        for (Process v : process) {
            k[i] = v.getBrust_time();
            i++;
        }int minm = k[0];int j;
        while (complete != n) {  System.out.println("Reem");
            j = 0;
      
             for (Process f : process) {


            if ((time == 0) && (k[j] < minm) && (k[j] > 0) && (f.getArrival_time() <= time)) {
                trial.add(new Process(f.getPid(), time));trial.get(trial.size()-1).setStartTime(time);
                minm = k[j];
                shortest = j;
                check = true;

            } //if((time==0) && (k[j]==minm) && (k[j] > 0)){trial.add(new Process(f.getPid(), time));trial.get(trial.size()-1).setStartTime(time);}
            if ((f.getArrival_time() <= time) && (k[j] < minm) && (k[j] > 0) && (time>0)) {

                    trial.get(trial.size()-1).setFinishTime(time);
                    trial.add(new Process(f.getPid(), time));
                    trial.get(trial.size()-1).setStartTime(time);
                 minm = k[j];
                shortest = j;
                check = true;
            }


            j++;
            }
            if (!check) {if (trial.size() == 0 || !Objects.equals(trial.get(trial.size() - 1).getPid(), process.get(shortest).getPid()))
            {trial.add(new Process(process.get(shortest).getPid(),time)) ; trial.get(trial.size()-1).setStartTime(time);
           }}

            // Reduce remaining time by one
            k[shortest]--;

            // Update minimum
            minm = k[shortest];
            if (minm == 0) {
                minm = k[0];
            }

            // If a process gets completely
            // executed
            if (k[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process

                finish_time = time + 1;
                trial.get(trial.size() - 1).setFinishTime(finish_time);
                //process.get(shortest).setFinishTime(finish_time);
                // Calculate waiting time

                process.get(shortest).setWaitingTime(finish_time - process.get(shortest).getBrust_time() - process.get(shortest).getArrival_time());

                if (process.get(shortest).getWaitingTime() < 0) {
                    process.get(shortest).setWaitingTime(0);
                }
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


 return new Output(trial,avgwaitingtime,avgturnaround);}}
