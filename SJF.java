import java.util.*;

public class SRTF {
    public static void main (String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println ("enter no of process:");
        int n= sc.nextInt();
        int pid[] = new int[n]; //  pid of process
        int arrivaltime[] = new int[n];
        int bursttime[] = new int[n];
        int completetime[] = new int[n];
        int turnaroundtime[] = new int[n];
        int waitingtime[] = new int[n];
        int flag[] = new int[n];  // flag it checks process is completed or not, 0 not completed ,1 is completed
        int k[]= new int[n];   //  stores brust time
        int i, st=0, total=0;
        float avgwaitingtime=0, avgturnaround=0;

        for (i=0;i<n;i++)
        {
            pid[i]= i+1;
            System.out.println ("enter process " +(i+1)+ " arrival time:");
            arrivaltime[i]= sc.nextInt();
            System.out.println("enter process " +(i+1)+ " burst time:");
            bursttime[i]= sc.nextInt();
            k[i]= bursttime[i];
            flag[i]= 0;
        }

        while(true){
            int min=99,c=n;
            if (total==n)
                break;

            for ( i=0;i<n;i++)
            {
                if ((arrivaltime[i]<=st) && (flag[i]==0) && (bursttime[i]<min))
                {
                    min=bursttime[i];
                    c=i;
                }
            }

            if (c==n)
                st++;
            else
            {
                bursttime[c]--;
                st++;
                if (bursttime[c]==0)
                {
                    completetime[c]= st;
                    flag[c]=1;
                    total++;
                }
            }
        }

        for(i=0;i<n;i++)
        {
            turnaroundtime[i] = completetime[i] - arrivaltime[i];
            waitingtime[i] = turnaroundtime[i] - k[i];
            avgwaitingtime+= waitingtime[i];
            avgturnaround+= turnaroundtime[i];
        }

        System.out.println("pid  arrival  burst  complete turn waiting");
        for(i=0;i<n;i++)
        {
            System.out.println(pid[i] +"\t"+ arrivaltime[i]+"\t"+ k[i] +"\t"+ completetime[i] +"\t"+ turnaroundtime[i] +"\t"+ waitingtime[i]);
        }

        System.out.println("\naverage tat is "+ (float)(avgturnaround/n));
        System.out.println("average wt is "+ (float)(avgwaitingtime/n));
        sc.close();
    }
}
