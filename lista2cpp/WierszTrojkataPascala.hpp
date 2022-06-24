#pragma once
#include <string>
#include <vector>
using namespace std;

class WierszTrojkataPascala {
  private:
    vector<int> w;
  public:
    int n;
  //Konstruktor
    WierszTrojkataPascala(int n);
  //Metody
    int liczba(int n, int m);
    vector <int>  wiersz (int n);
};