public class Celula {
    public Celula prox;
    public int elemento;

    public Celula() {
        this(0);
    }

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}
