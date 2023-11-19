public class No {
    public char letra;
    public No esq, dir;
    public No2 raiz;

    public No(char letra) {
        this.letra = letra;
        this.esq = null;
        this.dir = null;
        this.raiz = new No2();
    }
}
