#include <iostream>
#include <string>
#include "Figury.hpp"
#include "Figura.hpp"
#include "Kolo.hpp"
#include "Szesciokat.hpp"
#include "Pieciokat.hpp"
#include "Kwadrat.hpp"
#include "Prostokat.hpp"
#include "Romb.hpp"
#include <bits/stdc++.h>
using namespace std;

int main(int argc, char *argv[]){
    string x = argv[1];
    Figura* figury[x.size()];
    int l = 2;
    try {
        for (int i = 0; i<x.size();i++){
            if (x.at(i) == 'o') {
                Kolo* y = new Kolo();
                y->Promien = stof(argv[l]);
                l++;
                figury[i] = y;
                cout<<("Kolo created")<<endl;
            }
            else if(x.at(i)=='p') {
                Pieciokat* y = new Pieciokat();
                y->bok = stof(argv[l]);
                l++;
                figury[i] = y;
                cout<<("Pieciokat created")<<endl;
            }
            else if(x.at(i)=='s') {
                Szesciokat* y = new Szesciokat();
                y->bok = stof(argv[l]);
                l++;
                figury[i] = y;
                cout<<("Szesciokat created")<<endl;
            }
            else if(x.at(i)=='c') {
                if (stof(argv[l+4])==90) {
                    if (stof(argv[l])==stof(argv[l+1]) && stof(argv[l+1])==stof(argv[l+2]) && stof(argv[l+2])==stof(argv[l+3])){
                        Kwadrat* y = new Kwadrat();
                        y->bok1 = stof(argv[l]);
                        l = l+5;
                        figury[i] = y;
                        cout<<("Kwadrat created")<<endl;
                    }
                    else {
                        double arr[] = {atof(argv[l]),atof(argv[l+1]),atof(argv[l+2]),atof(argv[l+3])};
                        sort(arr,arr+4);
                        if (arr[0]==arr[1]&&arr[2]==arr[3]){
                            Prostokat* y = new Prostokat();
                            y->bok1 = stof(argv[l]);
                            y->bok2 = stof(argv[l+1]);
                            y->bok3 = stof(argv[l+2]);
                            y->bok4 = stof(argv[l+3]);
                            l = l+5;
                            figury[i] = y;
                            cout<<("Prostokat created")<<endl;
                        }
                        else {
                            cout<<"nierowne boki"<<endl;
                            return 0;
                        }
                    }
                }
                else if(stof(argv[l+4])<180 && stof(argv[l+4])>0){
                    Romb* y = new Romb();
                    y->bok1 = stof(argv[l]);
                    y->kat = stof(argv[l+4])*M_PI/180;
                    l = l+5;
                    figury[i] = y;
                    cout<<("Romb created")<<endl;
                }
                else {
                    cout<<("nieprawidlowy kat")<<endl;
                    return 0;
                }
            }
            else {
                cout<<("Niepoprawne dane")<<endl;
                return 0;
            }
        }
    }

    catch (exception &err) {
        cout<<("Niepoprawne dane");
        return 0;
    }
    if (l < argc) {
        cout<<("UWAGA! Podano za duzo argumentow")<<endl;
    }
    for (int j = 0; j<x.length();j++){
        cout<<"figura "<<j<<" Pole: "<<figury[j]->pole()<<" Obwod: "<<figury[j]->obwod()<<endl;
    }
    for (int j = 0; j<x.length();j++){
        figury[j]->~Figura();
    }
}   