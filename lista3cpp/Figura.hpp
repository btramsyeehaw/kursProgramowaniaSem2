#pragma once
class Figura {
    public:
        virtual double obwod() = 0;
        virtual double pole() = 0;
        Figura();
        virtual ~Figura();
};