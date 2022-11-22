import java.util.*;

class Process {
    int pid;
    int bt;
    int rem_bt;
    int wt;
    int tat;

    Process(int pid, int bt) {
        this.pid = pid;
        this.bt = bt;
        this.rem_bt = bt;
    }

}

public class RoundRobin {

    static Process proc[];
    static int quantum;
    static int p;
    static double avgwt = 0;
    static double avgtat = 0;

    public static void input(Scanner sc) {

        System.out.println("Enter no of process");
        p = sc.nextInt();
        proc = new Process[p];
        System.out.println("Enter Time Quantam");
        quantum = sc.nextInt();
        for (int i = 0; i < p; i++) {
            System.out.println("Enter Process number and Burst Time: ");
            int pid = sc.nextInt();
            int bt = sc.nextInt();
            proc[i] = new Process(pid, bt);
        }
    }

    public static void roundrobin() {
        int t = 0;
        while (true) {
            boolean done = true;
            for (int i = 0; i < p; i++) {
                if (proc[i].rem_bt > 0) {
                    done = false;
                    if (proc[i].rem_bt > quantum) {
                        t += quantum;
                        proc[i].rem_bt -= quantum;
                    } else {
                        t += proc[i].rem_bt;
                        proc[i].wt = t - proc[i].bt;
                        proc[i].rem_bt = 0;
                    }
                }
            }
            if (done == true) {
                break;
            }
        }

        for (int i = 0; i < p; i++) {
            proc[i].tat = proc[i].wt + proc[i].bt;
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
        System.out.println("Process\tBurstTime\tWaitingTime\tTurnAroundTime");
        for (int i = 0; i < p; i++) {
            System.out.println(proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + proc[i].wt + "\t\t" + proc[i].tat);
        }
        System.out.println("Average Waiting Time: " + avgwt);
        System.out.println("Average Turn Around Time: " + avgtat);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        roundrobin();
        System.out.println("\nOutput of RoundRobin(Premptive) Scheduling: ");
        print();
        sc.close();
    }
}
