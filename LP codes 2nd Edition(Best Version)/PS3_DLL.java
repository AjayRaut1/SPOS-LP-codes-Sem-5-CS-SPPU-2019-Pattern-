//Step 1 : After creating Calculator dll , paste below code inside CalculatorDLL.h file

#ifndef  _CALCULATORDLL_h_
#define  _CALCULATORDLL_h_
#ifdef CALCULATORDLL_EXPORTS
#define CALCULATORDLL_API __declspec(dllexport)
 
#else
#define CALCULATORDLL_API __declspec(dllimport)

#endif
CALCULATORDLL_API int Addition(int x,int y);
#endif

//Step 2 : After creating CalculatorDLL.h file create CalculatorDLL.c file and paste below code

#ifndef _CALCULATORDLL_c_
#define _CALCULATORDLL_c_
 
#include "CalculatorDLL.h" 

int Addition(int x,int y){
	int z;
	z=x+y;
	return z;
}
#endif

//Step 3 : After creating Calculator app paste the below code inside CalculatorAPP.c file

#include<stdio.h>
#include"C:\Users\AjjuBhai\OneDrive\Documents\Visual Studio 2012\Projects\CalculatorDLL\CalculatorDLL\CalculatorDLL.h"

int main()
{
	int a;
	a = Addition(10,5);
	printf("Addition=%d\n",a);
	return 0;
}


