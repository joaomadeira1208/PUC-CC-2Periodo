

public class No {
    public NoAB raiz;
    public boolean folha;
    public char elemento;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        folha = false;
        raiz = null;
    }

    public No inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
        return pesquisar(x);
    }

    public NoAB inserir(char x, NoAB i) throws Exception {
        if(i == null) {
            i = new NoAB(x);
        }
        else if(x < i.elemento) {
            i.esq = inserir(x, i.esq);
        }
        else if(x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }
        else {
            throw new Exception("Erro");
        }
        return i;
    }

    public No pesquisar(char x) {
        return pesquisar(x, raiz);
    }

    public No pesquisar(char x, NoAB i) {
        No resp;
        if(i == null) {
            resp = null;
        }
        else if(x == i.elemento) {
            resp = i.no;
        }
        else if(x < i.elemento) {
            resp = pesquisar(x, i.esq);
        }
        else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void setFilho(char x) {
        setFilho(x, raiz);
    }

    public void setFilho(char x, NoAB i) {
        if(i == null) {

        }
        else if(x == i.elemento) {
            i.no.folha = true;
        }
        else if(x < i.elemento) {
            setFilho(x, i.esq);
        }
        else {
            setFilho(x, i.dir);
        }
    }

    public int getN() {
        return getN(raiz);
    }

    public int getN(NoAB i) {
        int n = 0;
        if(i != null) {
            n = 1 + getN(i.esq) + getN(i.dir);
        }
        return n;
    }

    public No[] getFilho() {
        int n = getN();
        No[] vet = new No[n];
        getFilho(vet, raiz, 0);
        return vet;
    }

    public int getFilho(No[] vet, NoAB i, int pos) {
        if(i != null) {
            vet[pos++] = i.no;
            pos = getFilho(vet, i.esq, pos);
            pos = getFilho(vet, i.dir, pos);
        }
        return pos;
    }
}
