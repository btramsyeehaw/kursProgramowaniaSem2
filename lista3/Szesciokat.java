public class Szesciokat extends Figura {
    double bok;
    public double obwod(){
        return 6*bok;
    }
    public double pole() {
        return 1.5*Math.sqrt(3)*bok*bok;
    }
}