public class No {
    public final int tamanho = 255;
    public boolean folha;
    public No[] prox;
    public char elemento;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }

        folha = false;
        
    }
}