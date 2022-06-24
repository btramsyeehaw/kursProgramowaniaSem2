interface JednoArgumentowe {
    public double pole(double arg);
    public double obwod(double arg);
}
interface DwuArgumentowe {
    public double pole(double arg1, double arg2);
    public double obwod(double arg1, double arg2);
}

public class Figury {
    public enum JednoParametrowe implements JednoArgumentowe{
        KOLO {
            public double pole (double arg){
                return 2*arg*Math.PI;
            }
            public double obwod (double arg){
                return Math.PI*arg*arg;
            }
        },
        PIECIOKAT {
            public double obwod(double arg) {
                return 5*arg;
            }
            public double pole(double arg) {
                return 0.25*arg*arg*Math.sqrt(25+10*Math.sqrt(5));
            }
        },
        SZESCIOKAT {
            public double obwod(double arg){
                return 6*arg;
            }
            public double pole(double arg) {
                return 1.5*Math.sqrt(3)*arg*arg;
            }
        },
        KWADRAT {
            public double obwod(double arg){
                return 4*arg;
            }
            public double pole(double arg) {
                return arg*arg;
            }
        };
    }
    public enum DwuParametrowe implements DwuArgumentowe{
        PROSTOKAT {
            public double pole(double arg1, double arg2){
                return arg1*arg2;
            }
            public double obwod(double arg1, double arg2){
                return 2*arg1+2*arg2;
            }
        },
        ROMB {
            public double pole (double arg1, double arg2){
                return arg1*arg1*Math.sin(Math.toRadians(arg2));
            }
            public double obwod (double arg1, double arg2){
                return 4*arg1;
            }
        };
    }
}

