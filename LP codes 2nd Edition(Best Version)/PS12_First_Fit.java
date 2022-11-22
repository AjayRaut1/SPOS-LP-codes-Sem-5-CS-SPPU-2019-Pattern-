import java.util.Scanner;
public class FirstFit {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter no. of partitions: ");
            int npart = sc.nextInt();

            System.out.print("Enter size of partitions: ");
            int[] part = new int[npart];
            for(int i=0;i<npart;i++){
                part[i] = sc.nextInt();

            }
            System.out.print("Enter no. of processes: ");
            int npro = sc.nextInt();

            System.out.print("Enter size of processes: ");
            int[] pro = new int[npro];
            for(int i=0;i<npro;i++) {
                pro[i] = sc.nextInt();
            }
            int[] filled = new int[npart];
            for(int i=0;i<npro;i++){
                int k=-1;
                for(int j=0;j<npart;j++){
                    if(part[j]>=pro[i])
                        if(filled[j]!=1){
                            k=j;
                            filled[k]=1;
                            break;
                        }
                }
                if(k==-1) System.out.println("First Fit not found for process "+(i+1)+".");
                else System.out.println("First Fit for process "+(i+1)+" is "+part[k]+" and Hole of "+(part[k]-pro[i])+" is created.");
            }
        }
    }