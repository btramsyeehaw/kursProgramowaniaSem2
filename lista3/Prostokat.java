public class Prostokat extends Czworokat{
    public double obwod(){
        return bok1+bok2+bok3+bok4;
    }
    public double pole() {
        return Math.max(bok1,bok2)*Math.min(bok3,bok4);
    }
}
