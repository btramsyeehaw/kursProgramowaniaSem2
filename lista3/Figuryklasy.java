interface daneFigury {
    public double obwod();
    public double pole();
}

abstract class Figura implements daneFigury {}

abstract class Czworokat extends Figura{
    double bok1;
    double bok2;
    double bok3;
    double bok4;
    double kat;
}

