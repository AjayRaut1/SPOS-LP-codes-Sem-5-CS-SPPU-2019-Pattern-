#ifndef  _CALCULATORDLL_h_
#define  _CALCULATORDLL_h_
#ifdef CALCULATORDLL_EXPORTS
#define CALCULATORDLL_API __declspec(dllexport)
 
#else
#define CALCULATORDLL_API __declspec(dllimport)

#endif
CALCULATORDLL_API int Addition(int x,int y);
#endif