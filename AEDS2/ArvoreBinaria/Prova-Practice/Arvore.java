public class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserir(int x) throws Exception{
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception{
        if(i == null) {
            i = new No(x);
        }
        else if (x < i.elemento) {
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
            throw new Exception("Erro");
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
            throw new Exception("Erro");
        }
    }

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    public boolean pesquisar(int x, No i) {
        boolean resp;
        if(i == null) {
            resp = false;
        }
        else {
            if(x == i.elemento) {
                resp = true;
            }
            else if(x < i.elemento) {
                resp = pesquisar(x, i.esq);
            }
            else {
                resp = pesquisar(x, i.dir);
            }
        }
        return resp;
    }

    public void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento);
            caminharCentral(i.dir);
        }
    }

    public void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento);
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.elemento);
        }
    }

    public int getAltura(No i, int altura) {
        if(i == null) {
            altura--;
        }
        else {
            int alturaEsq = getAltura(i, altura + 1);
            int alturaDir = getAltura(i, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }

    public int soma(No i) {
        int soma = 0;
        if(i != null) {
            soma = i.elemento + soma(i.esq) + soma(i.dir);
        }
        return soma;
    }

    public int pares(No i) {
        int resp = 0;
        if(i != null) {
            resp = (i.elemento % 2 == 0) ? 1 : 0 + pares(i.esq) + pares(i.dir);
        }
        return resp;
    }

    public static boolean iguais(Arvore a, Arvore b) {
        return iguais(a.raiz, b.raiz);
    }

    public static boolean iguais(No i, No j) {
        boolean resp;
        if(i != null && j != null) {
            resp = (i.elemento == j.elemento)&& iguais(i.esq, j.esq) && iguais(i.dir, j.dir);
        }
        else if(i == null && j == null) {
            resp = true;
        }
        else {
            resp = false;
        }
        return resp;
    }

    public boolean div11(No i) {
        boolean resp;
        if(i != null) {
            resp = (i.elemento % 11 == 0) || div11(i.esq) || div11(i.dir);
        }
        else {
            resp = false;
        }
        return resp;
    }
}