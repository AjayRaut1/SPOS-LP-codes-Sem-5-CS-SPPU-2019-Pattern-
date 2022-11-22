import java.util.Scanner;
public class BestFit {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter no. of partitions: ");
            int npart = sc.nextInt();

            System.out.print("Enter size of partitions: ");
            int[] part = new int[npart];
            for(int i=0;i<npart;i++) {
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
            int[] diff = new int[npart];
            for(int i=0;i<npro;i++){
                for(int j=0;j<npart;j++){
                    diff[j] = part[j]-pro[i];
                    if(filled[j]==1)
                        diff[j]=-1;
                }
                int min=32767,k=0;
                for(int j=0;j<npart;j++){
                    if(diff[j]<min&&diff[j]>=0){
                        min=diff[j];
                        k=j;
                    }
                }
                filled[k]=1;

                if(diff[k]<0) System.out.println("Best Fit not found for process "+(i+1)+".");
                else System.out.println("Best Fit for process "+(i+1)+" is "+part[k]+" and Hole of "+diff[k]+" is created.");
            }
        }
}