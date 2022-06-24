public class Pieciokat extends Figura {
    double bok;
    public double obwod() {
        return 5*bok;
    }
    public double pole() {
        return 0.25*bok*bok*Math.sqrt(25+10*Math.sqrt(5));
    }
}