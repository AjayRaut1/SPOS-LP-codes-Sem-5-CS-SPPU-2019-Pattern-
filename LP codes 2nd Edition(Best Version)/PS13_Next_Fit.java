import java.util.Scanner;
class NextFit{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of partitions: ");
        int npart = sc.nextInt();

        System.out.print("Enter size of partitions: ");
        int[] part = new int[npart];
        for(int i=0;i<npart;i++)
            part[i] = sc.nextInt();

        System.out.print("Enter no. of processes: ");
        int npro = sc.nextInt();

        System.out.print("Enter size of processes: ");
        int[] pro = new int[npro];
        for(int i=0;i<npro;i++) {
            pro[i] = sc.nextInt();
        }

        int[] filled = new int[npart];
        int k=0,flag=0;
        for(int i=0;i<npro;i++){
            int j=k;
            do{
                if(part[j]>=pro[i])
                    if(filled[j]!=1){
                        k=j;
                        filled[k]=1;
                        flag=1;
                        break;
                    }
                j++;
                if(j==npart) j=0;
            }while(j!=k);
            if(flag==0) System.out.println("Next Fit not found for process "+(i+1)+".");
            else System.out.println("Next Fit for process "+(i+1)+" is "+part[k]+" and Hole of "+(part[k]-pro[i])+" is created.");
        }
    }
}