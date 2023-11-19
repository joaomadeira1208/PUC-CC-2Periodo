public class TreeSort {
    private No raiz;
    private int n;

    public TreeSort() {
        raiz = null;
        n = 0;
    }

    public int []sort() {
        int[] array = new int[n];
        n = 0;
        sort(raiz, array);
        return array;
    }

    public void sort(No i, int[] array) {
        if(i != null) {
            sort(i.esq, array);
            array[n++] = i.elemento;
            sort(i.dir, array);
        }
    }

    public int getMenor() {
        int resp = -1;
        if(raiz != null) {
            No i;
            for(i = raiz; i.esq != null; i = i.esq);
            resp = i.elemento;
        }

        return resp;
    }

    public int getMaior() {
        int resp = -1;
        if(raiz != null) {
            No i;
            for(i = raiz; i.dir != null; i = i.dir);
            resp = i.elemento;
        }
        return resp;
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    public No remover(int x, No i) throws Exception {
        if(i == null) {
            throw new Exception("Erro");
        }
        else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        }
        else if( x > i.elemento) {
            i.dir = remover(x, i.dir);
        }
        else if(i.dir == null) {
            i = i.esq;
        }
        else if(i.esq == null) {
            i = i.dir;
        }
        else {
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    public No maiorEsq(No i, No j) {
        if(j.dir == null) {
            i.elemento = j.elemento;
            j = j.dir;
        }
        else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }
