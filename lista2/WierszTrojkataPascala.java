public class WierszTrojkataPascala {
    //Konstruktor 
    public WierszTrojkataPascala(int n) { 
        this.lista = wiersz(n);}

    int[] lista;

    public int[] wiersz(int n) {
        int[] w = new int [n];
        for (int i = 0; i<n; i++){
            w[i] = liczba(n,i);
        }
        return w;
    }

    //Metoda
    public int liczba (int n, int m) {
        if (m == 0){
            return 1;
        }
        else if (m==1){
            return n;
        }
        else if (m>1 && m>((n/2)+1)){ 
            return liczba(n, n-m);
        }
        else if (m>1 && m<=((n/2)+1)){
            return liczba(n-1, m-1)+liczba(n-1, m);
        }
        return 0;
    }


}
