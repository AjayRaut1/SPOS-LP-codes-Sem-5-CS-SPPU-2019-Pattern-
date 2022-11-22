// Program FCFS :
class fcfs1 {
	public static void main(String args[]) {
		// String ar[] = { "Procss  ", "  Bus Time  ", "  Waiting Time  ", "  Tun Around Time" };
		String p[] = { "p1", "p2", "p3" };
		int wt[] = new int[3];
		int tat[] = new int[3];
		int bt[] = { 24, 3, 4 };

		int i;
		// calculating waiting time
		wt[0] = 0;
		for (i = 1; i < 3; i++) {
			wt[i] = wt[i - 1] + bt[i - 1];
		}
		// calculating turn around time
		for (i = 0; i < 3; i++) {
			tat[i] = wt[i] + bt[i];
		}
		System.out.print("");
		System.out.println("          OUTPUT         ");
		System.out.print("");
		System.out.println("Process   BusTime   waitingTime   tatTime");
		int totaltat = 0;
		int totalwt = 0;
		for (i = 0; i < 3; i++) {
			System.out.println(p[i] + "         " + bt[i] + "         " + wt[i] +"         "+ tat[i]);
		}
		for (i = 0; i < 3; i++) {
			totalwt = totalwt + wt[i];
		}
		totaltat = tat[0] + tat[1] + tat[2];
		float avgwt = (float) totalwt / 3;
		float avgtat = (float) totaltat / 3;
		System.out.println("Tun around Time =" + totaltat);
		System.out.println("Average Waiting Time = " + avgwt);
		System.out.println("Average Waiting Time = " + avgtat);

	}
}

// OUTPUT:

// Process BusTime waitingTime
// p1 24 0
// p2 3 24
// p3 4 27
// Tun around Time =82
// Average Waiting Time = 17.0
// Average Waiting Time = 27.333334