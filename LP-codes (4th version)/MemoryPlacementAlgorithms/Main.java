import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Best Fit");
        System.out.println("2. Worst Fit");
        System.out.println("3. First Fit");
        System.out.println("4. Next Fit");
        System.out.println("5. Exit");
        BestFit bf = new BestFit();
        WorstFit wf = new WorstFit();
        FirstFit ff = new FirstFit();
        NextFit nf = new NextFit();
        int ch;
        String c = "Y";
        while (c.equals("Y")) {
            System.out.println("Enter Choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    bf.bestfit(sc);
                    break;
                case 2:
                    wf.worstfit(sc);
                    break;
                case 3:
                    ff.firstfit(sc);
                    break;
                case 4:
                    nf.nextfit(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter Valid Choice..");
                    break;

            }
            System.out.println("Do you want to continue?[Y/N]");
            c = sc.next();
        }
    }

}
