#include<bits/stdc++.h>
using namespace std;
int pageFaults(int pages[], int n, int capacity)
{
unordered_set<int> s;
unordered_map<int, int> indexes;
int page_faults = 0;
for (int i=0; i<n; i++)
{
if (s.size() < capacity)
{
if (s.find(pages[i])==s.end())
{
s.insert(pages[i]);
page_faults++;
}
indexes[pages[i]] = i;
}
else
{
if (s.find(pages[i]) == s.end())
{
int lru = INT_MAX, val;
for (auto it=s.begin(); it!=s.end(); it++)
{
if (indexes[*it] < lru)
{
lru = indexes[*it];
val = *it;
}
}
s.erase(val);
s.insert(pages[i]);
page_faults++;
}
indexes[pages[i]] = i;
}
}
int hits=n-page_faults;
cout<<"\nTotal Page Faults are : "<<page_faults<<"\n";
cout<<"Total Page Hits are : "<<hits<<"\n";
float hit_ratio=(float(hits)/float(n))*100;
float fault_ratio=(float(page_faults)/float(n))*100;
cout<<"Ratio of page fault is : "<<fault_ratio<<"\n";
cout<<"Ratio of page hit is : "<<hit_ratio<<"\n";
return page_faults;
}
int main()
{
int pages[] = {0,1,2,3,0,1,4,0,1,2,3,4};
int n = sizeof(pages)/sizeof(pages[0]);
int capacity = 3;
pageFaults(pages, n, capacity);
return 0;
}
// Output:
// Total Page Faults are : 10
// Total Page Hits are : 2
// Ratio of page fault is : 83.3333
// Ratio of page hit is : 16.6667