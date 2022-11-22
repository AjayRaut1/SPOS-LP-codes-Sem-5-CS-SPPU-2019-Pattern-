import java.util.*;

class LRU {
    int p[], n, fr[], m, fs[], index, k, l, flag1 = 0, flag2 = 0, pf = 0, framesize = 3, i, j;
    Scanner src = new Scanner(System.in);

    void read() {
        System.out.println("Enter page table size");
        n = src.nextInt();
        p = new int[n];
        System.out.println("Enter the Reference String ");
        for (int i = 0; i < n; i++)
            p[i] = src.nextInt();
        System.out.println("Enter the Number of frames ");
        m = src.nextInt();
        fr = new int[m];
        fs = new int[m];
    }

    void display() {
        System.out.println("\n");
        for (i = 0; i < m; i++) {
            if (fr[i] == -1)
                System.out.println("[ ]");
            else
                System.out.println("[" + fr[i] + "]");
        }
    }

    void lru() {
        for (i = 0; i < m; i++) {
            fr[i] = -1;
        }
        for (j = 0; j < n; j++) {
            flag1 = 0;
            flag2 = 0;
            for (i = 0; i < m; i++) {
                if (fr[i] == p[j]) {
                    flag1 = 1;
                    flag2 = 1;
                    break;
                }
            }
            if (flag1 == 0) {
                for (i = 0; i < m; i++) {
                    if (fr[i] == -1) {
                        fr[i] = p[j];
                        flag2 = 1;
                        break;
                    }
                }
            }
            if (flag2 == 0) {
                for (i = 0; i < 3; i++)
                    fs[i] = 0;
                for (k = j - 1, l = 1; l <= framesize - 1; l++, k--) {
                    for (i = 0; i < 3; i++) {
                        if (fr[i] == p[k])
                            fs[i] = 1;
                    }
                }
                for (i = 0; i < 3; i++) {
                    if (fs[i] == 0)
                        index = i;
                }
                fr[index] = p[j];
                pf++;
            }
            System.out.print("Page : " + p[j]);
            display();
        }
        System.out.println("\n Number of page fault:" + pf);
    }

    public static void main(String args[]) {
        LRU a = new LRU();
        a.read();
        a.lru();
        a.display();
    }
}