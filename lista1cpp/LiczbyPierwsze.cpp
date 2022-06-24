#include<iostream>
#include "LiczbyPierwsze.hpp"
using namespace std;

LiczbyPierwsze::LiczbyPierwsze(int n) {
    this->n = n;
}

int LiczbyPierwsze::liczba(int n, int m){
    int lp[n-1];
    for (int i = 2; i<=n; i++){
        lp[i-2] = i;
    }

    for (int j = 0; j<n-1; j++) {
        if (lp[j]!=-1){
            int x = lp[j];
            int y = j;
            while (y<n-1){
                if (y!=j){
                    lp[y] = -1;
                }
                y = y+x;
            }
        }
    }

    int l = 0;
    for (int k = 0; k<n-2; k++){
        if (lp[k]!=-1){
            l++;
        }
    }

    int lpierwsze[l];
    l = 0;
    for (int k = 0; k<n-2;k++){
        if(lp[k]!=-1){
            lpierwsze[l]=lp[k];
            l++;
        }
    }

    if (l<m || m<0){
        return -1;
    }
    else {
        return lpierwsze[m];
    }
}