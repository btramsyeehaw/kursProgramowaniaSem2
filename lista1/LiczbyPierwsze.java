public class LiczbyPierwsze {
    //Konstruktor 
    public LiczbyPierwsze(int n) { 
        this.lista = listalp(n);}

    int[] lista;

    public int[] listalp(int n) {
        //generacja tablicy długości n-1, 2,3,4,...,n
        int[] lp = new int [n-1];
        for (int i = 2; i<=n; i++){
            lp[i-2] = i;
        }
        //wstawienie -1 pod liczby nie pierwsze
        for (int j = 0; j<n-1; j++) {
            if (lp[j]!=-1){
                int x = lp[j];
                int y = j;
                while (y<n-1){
                    if (y!=j){
                        lp[y] = -1;
                    }
                    y = y+x;
                }
            }
        }

        //l to długość tablicy z liczbami pierwszymi
        int l = 0;
        for (int k = 0; k<n-2;k++){
            if (lp[k]!=-1){
                l++;
            }
        } 

        //stworzenie tablicy z liczbami pierwszymi
        int[] lpierwsze = new int[l];
        l = 0;
        for (int k = 0; k<n-2;k++){
            if(lp[k]!=-1){
                lpierwsze[l]=lp[k];
                l++;
            }
        }

        return lpierwsze;
    }
        




    //Metoda
    public int liczba(int m) {
        if (lista.length<m || m<0) {
            return -1;
        }
        else {
            return lista[m];
        }
    }


}
