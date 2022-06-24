#include<iostream>
#include "WierszTrojkataPascala.hpp"
using namespace std;

    //Konstruktor
    WierszTrojkataPascala::WierszTrojkataPascala(int n){
        this->n = n;
        vector<int>* w = new vector<int>;
    }

    vector <int> WierszTrojkataPascala::wiersz (int n){
        for (int i = 0; i<n; i++){
            w.push_back(liczba(n,i));
        }
        return w;
    }

    int WierszTrojkataPascala::liczba (int n, int m){
        if (m == 0){
            return 1;
        }
        else if (m==1){
            return n;
        }
        else if (m>1 && m>((n/2)+1)){ 
            return liczba(n, n-m);
        }
        else if (m>1 && m<=((n/2)+1)){
            return liczba(n-1, m-1)+liczba(n-1, m);
        }
        return 0;
    }




