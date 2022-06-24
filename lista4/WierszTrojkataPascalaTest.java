public class WierszTrojkataPascalaTest {
    public static void main( String args[]) {
        if (args.length>1) {
            System.out.println("za duzo argumentow");
            System.exit(0);
        }
        try {
            int x = Integer.parseInt(args[0]);
            if (x<=0){
                System.out.println("liczba mniejsza od 0 - nieprawidlowy zakres");
                System.exit(0);
            }
            WierszTrojkataPascala tester = new WierszTrojkataPascala(x);
            String[] tabela = new String[x];
            int[] lentab = new int[x];
            for (int i = 0; i<x; i++){
                String z = "";
                for (int j = 0; j<i+1;j++) {
                    z = z+" "+tester.liczba(i,j);
                }
                tabela[i]=z;
                lentab[i]=z.length();
            }
            int najdluzsze = lentab[x-1];
            for (int i = 0; i<x; i++){
                int y = najdluzsze-lentab[i];
                String a = "";

                for (int j = 0; j<y/2; j++){
                    a = a+" ";
                }
                
                a = a+tabela[i];

                System.out.println(a);
            }
            
        }
        catch(NumberFormatException ex) {
            System.out.println("nieprawidlowy zakres");
        }


    }
}