public class Kolo extends Figura {
    double Promien;
    public double obwod() {
        return 2*Promien*Math.PI;
    }
    public double pole() {
        return Math.PI*Promien*Promien;
    }
}