#include <bits/stdc++.h>
using namespace std;
bool search(int key, vector<int>& fr)
{
for (int i = 0; i < fr.size(); i++)
if (fr[i] == key)
return true;
return false;
}
int predict(int pg[], vector<int>& fr, int pn, int index)
{
int res = -1, farthest = index;
for (int i = 0; i < fr.size(); i++) {
int j;
for (j = index; j < pn; j++) {
if (fr[i] == pg[j]) {
if (j > farthest) {
farthest = j;
res = i;
}
break;
}
}
if (j == pn)
return i;
}
return (res == -1) ? 0 : res;
}
void optimalPage(int pg[], int pn, int fn)
{
vector<int> fr;
int hit = 0;
for (int i = 0; i < pn; i++) {
if (search(pg[i], fr)) {
hit++;
continue;
}
if (fr.size() < fn)
fr.push_back(pg[i]);
else {
int j = predict(pg, fr, pn, i + 1);
fr[j] = pg[i];
}
}
cout << "No. of hits = " << hit << endl;
int miss=pn-hit;
cout << "No. of misses = " <<miss << endl;
float hit_ratio=(float(hit)/pn)*100;
float miss_ratio=(float(miss)/pn)*100;
cout << "Ratio of misses = " <<miss_ratio << endl;
cout << "Ratio of hits = " <<hit_ratio << endl;
}
int main()
{
int fn;
cout<<"Enter no of farmes : ";
cin>>fn;
int pn;
cout<<"Enter length of string : ";
cin>>pn;
int pg[pn];
cout<<"Enter values : ";
for(int i=0;i<pn;i++)
cin>>pg[i];
optimalPage(pg, pn, fn);
return 0;
}
// Output:
// Enter no of farmes : 3
// Enter length of string : 14
// Enter values : 7 0 1 2 0 3 0 4 2 3 0 3 2 3
// No. of hits = 7
// No. of misses = 7
// Ratio of misses = 50
// Ratio of hits = 50