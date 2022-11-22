import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FIFO f = new FIFO();
        System.out.println(f.fifo(sc));
        sc.close();
    }

    int fifo(Scanner sc) {
        System.out.println("Enter no. of pages: ");
        int n = sc.nextInt();
        int is[] = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = sc.nextInt();
        }
        System.out.println("Enter no. of Frames: ");
        int frames = sc.nextInt();
        HashSet<Integer> s = new HashSet<>(frames);
        Queue<Integer> q = new LinkedList<>();

        int pagefaults = 0;
        System.out.println("Incoming \t Pages");
        for (int i = 0; i < n; i++) {
            if (s.size() < frames) {
                if (!s.contains(is[i])) {
                    s.add(is[i]);
                    pagefaults++;
                    q.add(is[i]);
                }
            } else {
                if (!s.contains(is[i])) {
                    int val = (int) q.peek();
                    q.poll();
                    s.remove(val);
                    s.add(is[i]);
                    q.add(is[i]);
                    pagefaults++;

                }

            }
            System.out.print(is[i] + "\t");
            System.out.print(q + "\n");
        }
        return pagefaults;
    }
}