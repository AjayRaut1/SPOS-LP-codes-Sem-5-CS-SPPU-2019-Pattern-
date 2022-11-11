import java.util.*;
class SJF
{
			String r[];
			int n;
			int bt[],at[],st[],wt[],ft[],tt[];
			float awt,att;

			void accept()
			{
					int i;
					Scanner sc = new Scanner(System.in);
					System.out.println("enter n");
					n = sc.nextInt();
					r = new String[n];
					bt = new int[n];
					at = new int[n];
					st = new int[n];
					wt = new int[n];
					ft = new int[n];
					tt = new int[n];

					for(i=0;i<n;i++)
					{
							r[i] = "P"+(i+1);
							System.out.println("enter BT And AT");
							bt[i] = sc.nextInt();
							at[i] = sc.nextInt();
					}
			}

			void sort()
			{
					int i,j,t;
					String m;
					for(i=0;i<=n-2;i++)
					{
							for(j=i+1;j<=n-1;j++)
							{
										if(at[i] > at[j])
										{
													t = at[i];
													at[i] = at[j];
													at[j] = t;
													
													t = bt[i];
													bt[i] = bt[j];
													bt[j] = t;
						
													m = r[i];
													r[i] = r[j];
													r[j] = m;
										}
							}
					}
					for(i=1;i<=n-2;i++)
					{
							for(j=i+1;j<=n-1;j++)
							{
										if(bt[i] > bt[j])
										{
													t = at[i];
													at[i] = at[j];
													at[j] = t;
													
													t = bt[i];
													bt[i] = bt[j];
													bt[j] = t;
						
													m = r[i];
													r[i] = r[j];
													r[j] = m;
										}
							}
					}

			}
			void calculateAWT()
			{
					int i;
					float s ;
					st[0] = at[0];
					wt[0] = st[0] - at[0];
					s = wt[0];	
					for(i=1;i<n;i++)
					{
							if(st[i-1] + bt[i-1] > at[i])
							{
									st[i] = 	st[i-1] + bt[i-1];
							}
							else
							{
									st[i] = at[i];
							}
							wt[i]  =st[i] - at[i];
							s = s + wt[i];
					}
					awt = s/(float)n;
			}
			void calculateATT()
			{
					int i;
					float s = 0;
					for(i=0;i<n;i++)
					{
							ft[i] = st[i] + bt[i];
							tt[i] = ft[i] - at[i];
							s = s + tt[i];
					}

					att = s/(float)n;
			}
			void display()
			{
					int i;
					System.out.println("Name   BT  AT  ST  WT  FT  TT");
					for(i=0;i<n;i++)
					{
							System.out.println(" "+r[i]+"   " + bt[i] + "   " + at[i]+"   "+st[i]+ "    "+wt[i]+"   "+ft[i]+"   "+tt[i]);
					}	

					System.out.println();
					System.out.println();
					System.out.println("Average AWT =  " + awt);
					System.out.println("Average ATT =  " + att);
			}

}


class SJFExample
{
		public static void main(String arp[])
		{
					SJF x = new SJF();
					x.accept();
					x.sort();
					x.calculateAWT();
					x.calculateATT();
					x.display();
		}
}

		