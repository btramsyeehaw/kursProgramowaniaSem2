public class WierszTrojkataPascalaTest {
    public static void main( String args[]) {
        try {
            int x = Integer.parseInt(args[0]);
            WierszTrojkataPascala tester = new WierszTrojkataPascala(x);
            for (int i = 1; i<args.length; i++){
                try {
                    int n = Integer.parseInt(args[i]);
                    if (tester.liczba(x,n)!= 0){
                        System.out.println(n + " - "+ tester.liczba(x,n));
                    }
                    else {
                        System.out.println(n + " - liczba spoza zakresu");
                    }
                }
                catch(NumberFormatException ex) {
                    System.out.println(args[i] + " - nieprawidłowa dana");
                }
                catch(ArrayIndexOutOfBoundsException ex) {
                    System.out.println(args[i] + " - liczba spoza zakresu");
                }
            }
        }
        catch(NumberFormatException ex) {
            System.out.println("nieprawidłowy zakres");
        }


    }
}