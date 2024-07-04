
public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public Arvore(No i) {
        this.raiz = i;
    }

    // INSERÇÃO RETORNO DE REFERÊNCIA
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception{
        if(i == null) {
            i = new No(x);
        }
        else if(x < i.elemento) {
            i.esq = inserir(x, i.esq);
        }
        else if(x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }
        else {
            throw new Exception("Erro: Numero repetido");
        }
        return i;
    }


    // INSERIR PASSAGEM PAI
    public void inserirPai(int x) throws Exception{
        if(raiz == null) {
            raiz = new No(x);
        }
        else if(x < raiz.elemento) {
            inserirPai(x, raiz.esq, raiz);
        }
        else if(x > raiz.elemento) {
            inserirPai(x, raiz.dir, raiz);
        }
        else {
            throw new Exception("Erro: Número repetido");
        }
    }

    public void inserirPai(int x, No i, No pai) throws Exception {
        if(i == null) {
            if(x < pai.elemento) {
                pai.esq = new No(x);
            }
            else {
                pai.dir = new No(x);
            }
        }
        else if(x < i.elemento) {
            inserirPai(x, i.esq, i);
        }
        else if(x > i.elemento) {
            inserirPai(x, i.dir, i);
        }
        else {
            throw new Exception("Erro: Número repetido");
        }
    }

    // PESQUISA

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    public boolean pesquisar(int x, No i) {
        boolean resp;
        if(i == null) {
            resp = false;
        }
        else if(x == i.elemento) {
            resp = true;
        }
        else if(x < i.elemento) {
            resp = pesquisar(x, i.esq);
        }
        else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    // CAMINHAR CENTRAL

    public void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    // CAMINHAR PRÉ-FIXADO

    public void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.elemento + " ");
        }
    }

    // CAMINHAR PÓS-FIXADO
    public void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    // ALTURA

    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(No i, int altura) {
        if(i == null) {
            altura--;
        }
        else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }


    // SOMA ELEMENTOS

    public int soma(No i) {
        int resp = 0;
        if(i != null) {
            resp = i.elemento + soma(i.esq) + soma(i.dir);
        }

        return resp;
    }

    // NÚMERO DE ELEMENTOS PARES

    public int pares(No i) {
        int resp = 0;
        if(i != null) {
            if(i.elemento % 2 == 0) {
                resp = 1;
            }
            resp += pares(i.esq) + pares(i.dir);
        }

        return resp;
    }

    // ARVORES IGUAIS

    public  static boolean igual(Arvore a, Arvore b) {
        return igual(a.raiz, b.raiz);
    }

    public static boolean igual(No i, No j) {
        boolean resp;
        if(i != null && j != null) {
            resp = (i.elemento == j.elemento) && igual(i.esq, j.esq) && igual(i.dir, j.dir);
        }
        else if(i == null && j == null) {
            resp = true;
        }
        else {
            resp = false;
        }

        return resp;
    }

    public boolean divisivel(No i) {
        boolean resp;
        if(i != null) {
            resp = (i.elemento % 11 == 0) || divisivel(i.esq) || divisivel(i.dir);
        }
        else {
            resp = false;
        }
        return resp;
    }

    public void balancearEsq() {
        raiz = balancearEsq(raiz);
    }

    public No balancearEsq(No i) {
        No noDir = i.dir;
        No noDirEsq = noDir.esq;
        noDir.esq = i;
        i.dir = nodirEsq;

        return noDir;
    }

    public void balancearDir() {
        raiz = balancearDir(raiz);
    }

    public No balancearDir(No i) {
        No noEsq = i.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = i;
        i.esq = noEsqDir;

        return noEsq;
    }

    public void balancearDirEsq() {
        raiz = balancearDirEsq(raiz);
    }

    public No balancearDirEsq(No i) {
        i.dir = balancearDir(i.dir);
        return balancearEsq(i);
    }

    public void balancearEsqDir() {
        raiz = balancearEsqDir(raiz);
    }

    public No balancearEsqDir(No i) {
        i.esq = balancearEsq(i.esq);
        return balancearDir(i);
    }

    public static void main(String[] args) throws Exception{
        Arvore a = new Arvore();
        Arvore b = new Arvore();
        a.inserir(4);
        a.inserir(110);
        a.inserir(100);
        b.inserir(4);
        b.inserir(3);
        b.inserir(1001);
        if(a.divisivel(a.raiz)) {
            System.out.println("SIM");
        }
        else {
            System.out.println("NAO");
        }
    }
    
}
