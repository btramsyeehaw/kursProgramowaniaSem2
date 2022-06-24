#include "Figura.hpp"
#pragma once
class Kolo : public Figura {
    public: 
        double Promien;
        double obwod();
        double pole();
        Kolo();
        ~Kolo();
};