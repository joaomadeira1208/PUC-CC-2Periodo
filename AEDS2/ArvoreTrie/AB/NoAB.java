package AB;

public class NoAB {
    NoAB esq, dir;
    No no;
    char elemento;

    public NoAB() {
        this.elemento = 0;
        this.dir = this.esq = null;
        this.no = null;
    }

    public NoAB(char x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.no = new No(x);
    }
}
