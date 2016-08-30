#include <stdio.h>
#include <math.h>
#include "cs50.h"

bool consistent(int num1, int num2, double conv, int len, int number1[], int number2[], int numcur, double conversion[][numcur] );



int main(void)
{
     int length = GetInt();
    int num1[length];
      int num2[length];
       double rate[length];



    for (int i=0; i< length; i++) {
                int cur1 ;
        int cur2 ;
                double conv ;

        scanf ("%i %i %lf\n", &cur1, &cur2, &conv);

        //printf ("%i %i %.20lf\n", cur1, cur2, conv);
        num1[i] = cur1;
        num2[i] = cur2;
        rate[i] = conv;

    }

    int numCurrencies = 0;
    for(int i = 0; i < length; i++)
    {
        if(num1[i] > numCurrencies)
        {
            numCurrencies = num1[i];
        }

        if(num2[i] > numCurrencies)
        {
            numCurrencies = num2[i];
        }
    }

    double conTable[numCurrencies][numCurrencies];

    for(int i = 0; i < numCurrencies; i++)
    {
        for(int j = 0; j < numCurrencies; j++)
        {
            conTable[i][j] = -1;

        }
    }

    for(int i = 0; i < length; i++)
    {

            conTable[num1[i]][num2[i]] = rate[i];

    }


    for (int i=0; i<length; i++ ) {
        if (!consistent(num1[i], num2[i], rate[i], length, num1, num2, numCurrencies, conTable )) {printf ("1\n"); return 0;};
    }

             printf ("0\n");
    return 0;

    /*
     for (int i=0; i< 100; i++) {
          for (int j=0; j< 100; i++) {
              if (rateMap[i][j] != 1) printf ("%i %i %f\n", i, j, rateMap[i][j]);
          }
     }
     */


}

bool consistent(int tnum1, int tnum2, double conv, int len, int number1[], int number2[],  int numcur, double conversion[numcur][numcur]) {

    if (conversion[tnum1][tnum2] == -1)  { //printf ("Tcall: %i %i %lf\n", tnum1, tnum2, conv);
                                          return true; }
    else {
        //printf ("call: %i %i %lf\n", tnum1, tnum2, conv);
    }

        if (tnum1 == tnum2 ) {
            if (conv == conversion[tnum1][tnum1]) return true;
           else return false;
        }else {
             for (int i=0; i< numcur; i++) {
                 if (i <= tnum2) continue;
                 if (conversion[tnum2][i] == -1) continue;
                 // printf ("Rcall: %i %i %lf\n", tnum1, i, conv * conversion[tnum2][i]);
                 return consistent(tnum1, i, conv * conversion[tnum2][i], len, number1, number2, numcur, conversion);
             }
        }

    return true;
}
