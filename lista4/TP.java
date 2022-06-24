public class TP {
    public static void main( String args[]) {
        if (args.length>1) {
            System.out.println("za duzo argumentow");
            System.exit(0);
        }
        try {
            int x = Integer.parseInt(args[0]);
            if (x<0){
                System.out.println("liczba mniejsza od 0 - nieprawidlowy zakres");
                System.exit(0);
            }
            if (x==0){
                System.out.println("1");
                System.exit(0);
            }
            else{
                int[][] tabela = new int[x+1][];
                for (int i = 0; i<=x; i++){
                    int[] wiersz = new int[i+1];
                    if (i==0){
                        wiersz[0]=1;
                    }
                    if (i==1){
                        wiersz[0]=1;
                        wiersz[1]=1;
                    }
                    else{
                        for (int j=0;j<=i;j++){
                            if(j==0 || j==i){
                                wiersz[j]=1;
                            }
                            else if(j==1){
                                wiersz[j]=i;
                            }
                            
                            else if(j>1 && j>((i/2)+1)){
                                wiersz[j] = wiersz[i-j];
                            }
                            else{
                                wiersz[j]=tabela[i-1][j-1]+tabela[i-1][j];
                            }
                        }
                    }
                    tabela[i]=wiersz;
                }
            String[] tab = new String[x+1];
            int[] lentab = new int[x+1];
            for (int i = 0; i<=x; i++){
                String z = "";
                for (int j = 0; j<i+1;j++) {
                    z = z+" "+String.valueOf(tabela[i][j]);
                }
                tab[i]=z;
                lentab[i]=z.length();
            }
            int najdluzsze = lentab[x];
            for (int i = 0; i<=x; i++){
                int y = najdluzsze-lentab[i];
                String a = "";

                for (int j = 0; j<y/2; j++){
                    a = a+" ";
                }
                
                a = a+tab[i];

                System.out.println(a);
            }
            }


            
            
        }
        catch(NumberFormatException ex) {
            System.out.println("nieprawidlowy zakres");
        }


    }
}