public class LiczbyPierwszeTest {
    public static void main( String args[]) {
        try {
            int x = Integer.parseInt(args[0]);
            LiczbyPierwsze tester = new LiczbyPierwsze(x);
            for (int i = 1; i<args.length; i++){
                try {
                    int n = Integer.parseInt(args[i]);
                    if (tester.liczba(n)!= -1){
                        System.out.println(n + " - "+ tester.liczba(n));
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
