#include <iostream>
#include "LiczbyPierwsze.hpp"
using namespace std;

int main(int argc, char *argv[]) {
    try {
        int x = std::stoi(argv[1]);
        static LiczbyPierwsze* test = new LiczbyPierwsze(x);
        for (int i = 2; i<argc; i++){
            try {
                int n = std::stoi(argv[i]);
                if(test->liczba(x,n)!= -1){
                    cout<<n<<" - "<<test->liczba(x,n)<<endl;
                }
                else {
                    cout<<n<<" - liczba spoza zakresu"<<endl;
                }
            }
            catch (exception &err) {
                cout<<argv[i]<<" - nieprawidlowa dana"<<endl;
            }
        }
        delete test;
    }
    catch (exception &err) {
        cout<<"nieprawidlowy zakres"<<endl;
    }
}