public class CelulaDupla {
    public CelulaDupla prox, ant;
    public int elemento;

    public CelulaDupla() {
        this(0);
    }

    public CelulaDupla(int x) {
        this.elemento = x;
        this.prox = null;
        this.ant = null;
    }
}
