import java.util.Arrays;
import java.util.Scanner;

class BestFit {
    void bestfit(Scanner sc) {
        System.out.println("Enter No of memory blocks: ");
        int n = sc.nextInt();
        int mb[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter size of block " + (i + 1) + " :");
            mb[i] = sc.nextInt();
        }
        System.out.println("Enter no of processes : ");
        int p = sc.nextInt();
        int ps[] = new int[p];
        for (int i = 0; i < p; i++) {
            System.out.println("Enter size of process " + (i + 1) + " :");
            ps[i] = sc.nextInt();
        }
        int allocation[] = new int[p];
        Arrays.fill(allocation, -1);
        for (int i = 0; i < p; i++) {
            int bstindex = -1;
            for (int j = 0; j < n; j++) {
                if (mb[j] >= ps[i]) {
                    if (bstindex == -1) {
                        bstindex = j;
                    } else if (mb[bstindex] > mb[j]) {
                        bstindex = j;
                    }
                }
            }
            if (bstindex != -1) {
                allocation[i] = bstindex + 1;
                mb[bstindex] -= ps[i];
            }
        }
        print(allocation, ps);
    }

    static void print(int allocation[], int ps[]) {
        System.out.println("Process No.\t" + "Process Size\t" + "Memory Block Alloted");
        for (int i = 0; i < ps.length; i++) {
            if (allocation[i] != -1) {
                System.out.println((i + 1) + ".\t\t" + ps[i] + "\t\t" + allocation[i]);
            } else {
                System.out.println((i + 1) + ".\t\t" + ps[i] + "\t\tNot Alloted(Insufficient Memory)");
            }

        }
    }
}