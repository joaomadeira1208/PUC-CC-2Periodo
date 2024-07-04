public class NoAN {
    public boolean cor;
    public NoAN esq, dir;
    public int elemento;

    public NoAN() {
        this(-1);
    }

    public NoAN(int x) {
        this(x, null, null, false);
    }

    public NoAN(int x, boolean cor) {
        this(x, null, null, cor);
    }

    public NoAN(int x, NoAN esq, NoAN dir, boolean cor) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}