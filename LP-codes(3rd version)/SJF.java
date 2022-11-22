import java.util.*; 
class SJF 
{ 
public static void main(String args[]) 
{  
Scanner sc=new Scanner(System.in); 
int n,BT[],WT[],TAT[], OUT[]; 
System.out.println("Enter no of process"); 
n=sc.nextInt(); 
BT=new int[n+1]; 
WT=new int[n+1]; 
TAT=new int[n+1];
OUT=new int[n+1];
float AWT=0;
float ATAT=0;

System.out.println("Enter Burst time for each process"); 
for(int i=0;i<n;i++) 
{ 
System.out.println("Enter BT for process "+(i+1)); 
BT[i]=sc.nextInt(); 
OUT[i]=i+1;
}

for(int i=0;i<n;i++) 
{
WT[i]=0; TAT[i]=0; 
} 
int temp; 
for(int i=0;i<n-1;i++) 
{
for(int j=0;j<n-1;j++) 
{ 
if(BT[j]>BT[j+1])    
{
temp=BT[j]; 
BT[j]=BT[j+1]; 
BT[j+1]=temp; 
temp=OUT[j]; 
OUT[j]=OUT[j+1]; 
OUT[j+1]=temp; 
}
}
} 

for(int i=0;i<n;i++) 
{
    TAT[i]=BT[i]+WT[i]; 
    WT[i+1]=TAT[i]; 
} 
TAT[n]=WT[n]+BT[n]; 

for(int j=0;j<n;j++) {
    TAT[j]=WT[j]+BT[j];
    AWT+=WT[j]; 
}



System.out.println("  PROCESS   BT      WT      TAT     "); 
for(int i=0;i<n;i++) 
System.out.println("    "+ OUT[i] + "       "+BT[i]+"       "+WT[i]+"       "+TAT[i]); 

AWT=AWT/n; 
System.out.println("***********************************************"); 
System.out.println("Avg waiting time="+AWT+"\n***********************************************"); 

for(int i=0;i<n;i++) 
{ 
TAT[i]=WT[i]+BT[i]; 
ATAT=ATAT+TAT[i]; 
}

ATAT=ATAT/n; 
System.out.println("***********************************************"); 
System.out.println("Avg turn around time="+ATAT+"\n***********************************************"); 
} 
}
