#include <bits/stdc++.h>
using namespace std;
const int N=100005;
int n;
int frame_size;
int pages[N];
int mark[N];
void fifo_page_replacement(void)
{
queue<int> Q;
int page_faults=0;
int hits=0;
for(int i=0; i<n; i++)
{
if(mark[pages[i]]==true)
{
hits++;
}
else
{
Q.push(pages[i]);
mark[pages[i]]=true;
if(Q.size()>frame_size)
{
int p= Q.front();
mark[p]=false;
Q.pop();
}
page_faults++;
/*cout<<"Reference to page "<<pages[i]<<" caused a page fault\n";*/
}
}
cout<<"\nTotal Page Faults are : "<<page_faults<<"\n";
cout<<"Total Page Hits are : "<<hits<<"\n";
float hit_ratio=(float(hits)/float(n))*100;
float fault_ratio=(float(page_faults)/float(n))*100;
cout<<"Ratio of page fault is : "<<fault_ratio<<"\n";
cout<<"Ratio of page hit is : "<<hit_ratio<<"\n";
return;
}
int main()
{
int page_faults=10;
cout<<"Enter Number of Frames : ";
cin>>frame_size;
cout<<"Enter Page string Length : ";
cin>>n;
cout<<"Enter Numbers :\n";
for(int i=0; i<n; i++)
cin>>pages[i];
fifo_page_replacement();
return 0;
}
// Output:
// Enter Number of Frames : 3
// Enter Page string Length : 14
// Enter Numbers :
// 7 0 1 2 0 3 0 4 2 3 0 3 2 3
// Total Page Faults are : 10
// Total Page Hits are : 4
// Ratio of page fault is : 71.4286
// Ratio of page hit is : 28.5714