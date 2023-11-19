public class No {
    public No esq, dir;
    public int elemento;

    public No(int x) {
        this(x, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
