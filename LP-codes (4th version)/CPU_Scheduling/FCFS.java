import java.util.*;

class Process {
	int pid;
	int at;
	int bt;
	int wt;
	int tat;

	Process(int pid, int at, int bt) {
		this.pid = pid;
		this.at = at;
		this.bt = bt;
	}

	public int arrivtime() {
		return at;
	}
}

public class FCFS {

	static Process proc[];
	static int p;
	static double avgwt = 0;
	static double avgtat = 0;

	public static void input(Scanner sc) {
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

	public static void fcfs() {
		Arrays.sort(proc, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				return a.arrivtime() - b.arrivtime();
			}
		});

		proc[0].wt = 0;
		for (int i = 1; i < p; i++) {
			proc[i].wt = proc[i - 1].bt + proc[i - 1].wt;
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
		fcfs();
		System.out.println("Input Sorted According to ArrivalTime: ");
		System.out.println("ProcessNo.\tBurstTime\tArrivalTime");
		for (int i = 0; i < p; i++) {
			System.out.println(proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + proc[i].at);
		}

		System.out.println("\nOutput of FCFS Scheduling: ");
		print();
		sc.close();
	}
}
