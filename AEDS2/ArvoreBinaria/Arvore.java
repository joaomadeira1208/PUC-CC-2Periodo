
public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    // INSERÇÃO COM RETORNO DE REFERÊNCIA
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro");
        }
        return i;

    }

    // INSERÇÃO COM PASSAGEM DE PAI

    public void inserirPai(int x) throws Exception {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x < raiz.elemento) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x > raiz.elemento) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro: Número repetido");
        }
    }

    public void inserirPai(int x, No i, No pai) throws Exception {
        if (i == null) {
            if (x < pai.elemento) {
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x < i.elemento) {
            inserirPai(x, i.esq, i);
        } else if (x > i.elemento) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro: Número repetido");
        }
    }
}
