import java.util.Scanner;
 
public class priority{
        
    public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
 
            int x,n,p[],pp[],bt[],wt[],tat[],awt,atat,i;
 
            p = new int[10];
            pp = new int[10];
            bt = new int[10];
            wt = new int[10];
            tat = new int[10];
 
   //n is number of process
   //p is process
   //pp is process priority
   //bt is process burst time
   //wt is wait time
   //tat is turnaround time
   //awt is average waiting time
   //atat is average turnaround time
 
 
   System.out.print("Enter the number of process : ");
   n = sc.nextInt();
    System.out.print("\n\t Enter burst time : time priorities\n ");
 
   for(i=0;i<n;i++)
    {
       System.out.print("\nProcess["+(i+1)+"]:");
      bt[i] = sc.nextInt();
      pp[i] = sc.nextInt();
      p[i]=i+1;
    }
 
//sorting on the basis of priority
  for(i=0;i<n-1;i++)
   {
     for(int j=i+1;j<n;j++)
     {
       if(pp[i]>pp[j])
       {
     x=pp[i];
     pp[i]=pp[j];
     pp[j]=x;
     x=bt[i];
     bt[i]=bt[j];
     bt[j]=x;
     x=p[i];
     p[i]=p[j];
     p[j]=x;
      }
   }
}
wt[0]=0;
awt=0;
tat[0]=bt[0];
atat=tat[0];
for(i=1;i<n;i++)
 {
   wt[i]=tat[i-1];
   awt+=wt[i];
   tat[i]=wt[i]+bt[i];
   atat+=tat[i];
 }
 
//Displaying the process
 
  System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
for(i=0;i<n;i++)
  System.out.print("\n   "+p[i]+"\t\t   "+bt[i]+"\t\t     "+wt[i]+"\t\t     "+tat[i]+"\t\t     "+pp[i]+"\n");
awt/=n;
atat/=n;
  System.out.print("\n Average Wait Time : "+awt);
  System.out.print("\n Average Turn Around Time : "+atat);
 
        }
}

