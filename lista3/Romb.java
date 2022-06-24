public class Romb extends Czworokat {
    public double obwod() {
        return 4*bok1;
    }
    public double pole() {
        return bok1*bok1*Math.sin(Math.toRadians(kat));
    }
}
