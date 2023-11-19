public class No2 {
    public No2 esq, dir;
    public String palavra;

    public No2() {
        this(null);
    }

    public No2(String palavra) {
        this.palavra = palavra;
        this.esq = null;
        this.dir = null;
    }
}
