import java.util.*;

class Process {
    int pid;
    int at;
    int bt;
    int wt;
    int rt;
    int tat;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
    }

}

public class SJF {

    static Process proc[];
    static int p;
    static double avgwt = 0;
    static double avgtat = 0;

    static void input(Scanner sc) {
        System.out.println("Enter Number of Processes");
        p = sc.nextInt();
        proc = new Process[p];
        for (int i = 0; i < p; i++) {
            System.out.println("Enter ProcessNo., Arrival Time and Burst Time: ");
            int pid = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            proc[i] = new Process(pid, at, bt);
        }
    }

    static void sjf() {
        int complete = 0, t = 0;
        int minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        while (complete != p) {

            for (int j = 0; j < p; j++) {
                if ((proc[j].at <= t) &&
                        (proc[j].rt < minm) && (proc[j].rt > 0)) {
                    minm = proc[j].rt;
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            proc[shortest].rt--;

            minm = proc[shortest].rt;

            if (minm == 0) {
                minm = Integer.MAX_VALUE;
            }

            if (proc[shortest].rt == 0) {
                complete++;
                check = false;

                finish_time = t + 1;

                proc[shortest].wt = finish_time - proc[shortest].bt - proc[shortest].at;

                if (proc[shortest].wt < 0) {
                    proc[shortest].wt = 0;
                }
            }
            t++;

        }

        for (int i = 0; i < p; i++) {
            proc[i].tat = proc[i].bt + proc[i].wt;
        }

        for (Process i : proc) {
            avgwt += i.wt;
        }
        avgwt /= p;
        for (Process i : proc) {
            avgtat += i.tat;
        }
        avgtat /= p;
    }

    public static void print() {
        System.out.println("ProcessNo.\tBurstTime\tArrivalTime\tWaitingTime\tTurnAroundTime");
        for (Process i : proc) {
            System.out.println(i.pid + "\t\t" + i.bt + "\t\t" + i.at + "\t\t" + i.wt + "\t\t" + i.tat);
        }

        System.out.println("Average Waiting Time: " + avgwt);
        System.out.println("Average Turn Around Time: " + avgtat);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        sjf();
        System.out.println("\nOutput of FCFS Scheduling: ");
        print();
        sc.close();
    }
}