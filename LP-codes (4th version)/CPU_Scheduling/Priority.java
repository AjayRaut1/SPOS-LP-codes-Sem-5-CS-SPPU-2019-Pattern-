import java.util.*;

class Process {
	int pid;
	int bt;
	int priority;
	int wt;
	int tat;

	Process(int pid, int bt, int priority) {
		this.pid = pid;
		this.bt = bt;
		this.priority = priority;
	}

	public int prior() {
		return priority;
	}
}

public class Priority {

	static Process proc[];
	static int p;
	static double avgwt = 0;
	static double avgtat = 0;

	public static void input(Scanner sc) {
		System.out.println("Enter no. of processes:");
		p = sc.nextInt();

		proc = new Process[p];

		for (int i = 0; i < p; i++) {

			System.out.println("Enter Process No.: ");
			int pid = sc.nextInt();
			System.out.println("Enter Burst Time: ");
			int bt = sc.nextInt();
			System.out.println("Enter Priority: ");
			int priority = sc.nextInt();
			proc[i] = new Process(pid, bt, priority);
		}
	}

	public static void print() {
		System.out.println("ProcessNo.\tBurstTime\tPriority\tWaitingTime\tTurnAroundTime");
		for (Process i : proc) {
			System.out.println(i.pid + "\t\t" + i.bt + "\t\t" + i.priority + "\t\t" + i.wt + "\t\t" + i.tat);
		}

		System.out.println("Average Waiting Time: " + avgwt);
		System.out.println("Average Turn Around Time: " + avgtat);
	}

	public static void priorityScheduling() {
		Arrays.sort(proc, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				return a.prior() - b.prior();
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		input(sc);
		priorityScheduling();
		System.out.println("Input Sorted According to Priority: ");
		System.out.println("ProcessNo.\tBurstTime\tPriority");
		for (int i = 0; i < p; i++) {
			System.out.println(proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + proc[i].priority);
		}

		System.out.println("\nOutput of Priority Scheduling: ");
		print();
		sc.close();
	}
}
