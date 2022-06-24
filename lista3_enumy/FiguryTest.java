import java.util.Arrays;
public class FiguryTest {
    public static void main( String args[]) {
        String x = args[0]; 
        int l = 1;
        try {
            for (int i = 0; i<x.length();i++){
                if (x.charAt(i)=='o') {
                    double promien = Double.parseDouble(args[l]);
                    l++;
                    System.out.println("Kolo o promieniu "+promien+" pole: "+Figury.JednoParametrowe.KOLO.pole(promien)+ " obwod: "+Figury.JednoParametrowe.KOLO.obwod(promien));
                }
                else if(x.charAt(i)=='p') {
                    double bok = Double.parseDouble(args[l]);
                    l++;
                    System.out.println("Pieciokat o boku "+bok+" pole: "+Figury.JednoParametrowe.PIECIOKAT.pole(bok)+ " obwod: "+Figury.JednoParametrowe.PIECIOKAT.obwod(bok));
                }
                else if(x.charAt(i)=='s') {
                    double bok = Double.parseDouble(args[l]);
                    l++;
                    System.out.println("Szesciokat o boku "+bok+" pole: "+Figury.JednoParametrowe.SZESCIOKAT.pole(bok)+ " obwod: "+Figury.JednoParametrowe.SZESCIOKAT.obwod(bok));
                }
                else if(x.charAt(i)=='c') {
                    if (Double.parseDouble(args[l+4])==90) {
                        if (Double.parseDouble(args[l])==Double.parseDouble(args[l+1]) && Double.parseDouble(args[l+1])==Double.parseDouble(args[l+2]) && Double.parseDouble(args[l+2])==Double.parseDouble(args[l+3])){
                            double bok = Double.parseDouble(args[l]);
                            l++;
                            System.out.println("Kwadrat o boku "+bok+" pole: "+Figury.JednoParametrowe.KWADRAT.pole(bok)+ " obwod: "+Figury.JednoParametrowe.KWADRAT.obwod(bok));
                        } 
                        else {
                            double[] boktester = {Double.parseDouble(args[l]),Double.parseDouble(args[l+1]),Double.parseDouble(args[l+2]),Double.parseDouble(args[l+3])};
                            Arrays.sort(boktester);
                            if (boktester[0]==boktester[1] && boktester[2]==boktester[3]){
                                double bok1 = Math.max(Double.parseDouble(args[l]),Double.parseDouble(args[l+1]));
                                double bok2 = Math.min(Double.parseDouble(args[l+2]),Double.parseDouble(args[l+3]));
                                l = l+5;
                                System.out.println("Prostokat o bokach "+bok1+" "+bok2+ " pole: "+Figury.DwuParametrowe.PROSTOKAT.pole(bok1,bok2)+ " obwod: "+Figury.DwuParametrowe.PROSTOKAT.obwod(bok1,bok2));
                            }
                            else {
                                System.out.println("Nieprawidlowe boki w prostokacie");
                                System.exit(0);
                            }

                        }
                    }
                    else if(Double.parseDouble(args[l+4])<180 && Double.parseDouble(args[l+4])>0){
                        double bok1 = Double.parseDouble(args[l]);
                        double kat = Double.parseDouble(args[l+4]);
                        l = l+5;
                        System.out.println("Romb o bokach "+bok1+", o kacie"+kat+ " pole: "+Figury.DwuParametrowe.ROMB.pole(bok1,kat)+ " obwod: "+Figury.DwuParametrowe.ROMB.obwod(bok1,kat));
                    }
                    else {
                        System.out.println("nieprawidlowy kat");
                        System.exit(0);
                    }
                }
                else {
                    System.out.println("Niepoprawne dane");
                    System.exit(0);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Za malo danych");
            System.exit(0);
        }
        catch (NumberFormatException ex) {
            System.out.println("Niepoprawne dane");
            System.exit(0);
        }
    
    if (l < args.length) {
        System.out.println("UWAGA! Podano za duzo argumentow");
    }
    }
}
