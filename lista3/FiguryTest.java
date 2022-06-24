import java.util.Arrays;

public class FiguryTest {
    public static void main( String args[]) {
        String x = args[0]; 
        Figura[] figury = new Figura[x.length()];
        int l = 1;
        try {
            for (int i = 0; i<x.length();i++){
                if (x.charAt(i)=='o') {
                    Kolo y = new Kolo();
                    y.Promien = Double.parseDouble(args[l]);
                    l++;
                    figury[i] = y;
                    System.out.println("Kolo created");
                }
                else if(x.charAt(i)=='p') {
                    Pieciokat y = new Pieciokat();
                    y.bok = Double.parseDouble(args[l]);
                    l++;
                    figury[i] = y;
                    System.out.println("Pieciokat created");
                }
                else if(x.charAt(i)=='s') {
                    Szesciokat y = new Szesciokat();
                    y.bok = Double.parseDouble(args[l]);
                    l++;
                    figury[i] = y;
                    System.out.println("Szesciokat created");
                }
                else if(x.charAt(i)=='c') {
                    if (Double.parseDouble(args[l+4])==90) {
                        if (Double.parseDouble(args[l])==Double.parseDouble(args[l+1]) && Double.parseDouble(args[l+1])==Double.parseDouble(args[l+2]) && Double.parseDouble(args[l+2])==Double.parseDouble(args[l+3])){
                            Kwadrat y = new Kwadrat();
                            y.bok1 = Double.parseDouble(args[l]);
                            l = l+5;
                            figury[i] = y;
                            System.out.println("Kwadrat created");
                        }
                        else {
                            double[] boktester = {Double.parseDouble(args[l]),Double.parseDouble(args[l+1]),Double.parseDouble(args[l+2]),Double.parseDouble(args[l+3])};
                            Arrays.sort(boktester);
                            if (boktester[0]==boktester[1] && boktester[2]==boktester[3]){
                                Prostokat y = new Prostokat();
                                y.bok1 = Double.parseDouble(args[l]);
                                y.bok2 = Double.parseDouble(args[l+1]);
                                y.bok3 = Double.parseDouble(args[l+2]);
                                y.bok4 = Double.parseDouble(args[l+3]);
                                l = l+5;
                                figury[i] = y;
                                System.out.println("Prostokat created");
                            }
                            else {
                                System.out.println("nierowne boki");
                                System.exit(0);
                            }
                        }
                    }
                    else if(Double.parseDouble(args[l+4])<180 && Double.parseDouble(args[l+4])>0){
                        Romb y = new Romb();
                        y.bok1 = Double.parseDouble(args[l]);
                        y.kat = Double.parseDouble(args[l+4]);
                        l = l+5;
                        figury[i] = y;
                        System.out.println("Romb created");
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
    for (int j = 0; j<x.length();j++){
        System.out.println("figura "+j+" Pole: "+ figury[j].pole() + " Obwod: " + figury[j].obwod());
    }

    }
}