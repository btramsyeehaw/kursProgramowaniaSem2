#include "Figury.hpp"
#include "Kolo.hpp"
#include "Szesciokat.hpp"
#include "Pieciokat.hpp"
#include "Kwadrat.hpp"
#include "Prostokat.hpp"
#include "Romb.hpp"

Figura::Figura() {}
Figura::~Figura() {}
Czworokat::Czworokat() {}
Czworokat::~Czworokat() {}

Kolo::Kolo() {
    double Promien;
}
Kolo::~Kolo(){}
double Kolo::obwod() {
    return 2*Promien*M_PI;
}
double Kolo::pole() {
    return M_PI*Promien*Promien;
}
Szesciokat::Szesciokat() {
    double bok;
}
Szesciokat::~Szesciokat() {}
double Szesciokat::obwod() {
    return 6*bok;
}
double Szesciokat::pole() {
    return 1.5*sqrt(3)*bok*bok;
}
Pieciokat::Pieciokat() {
    double bok;
}
Pieciokat::~Pieciokat(){}
double Pieciokat::obwod() {
    return 5*bok;
}
double Pieciokat::pole() {
    return 0.25*bok*bok*sqrt(25+10*sqrt(5));
}
Kwadrat::Kwadrat() {}
Kwadrat::~Kwadrat() {}
double Kwadrat::obwod() {
    return 4*bok1;
}
double Kwadrat::pole() {
    return bok1*bok1;
}
Prostokat::Prostokat() {}
Prostokat::~Prostokat() {}
double Prostokat::obwod() {
    return bok1+bok2+bok3+bok4;
}
double Prostokat::pole() {
    return std::min(bok1,bok2)*std::max(bok3,bok4);
}
Romb::Romb() {}
Romb::~Romb() {}
double Romb::obwod() {
    return 4*bok1;
}
double Romb::pole() {
    return bok1*bok1*sin(kat);
}