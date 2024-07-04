public class Alvinegra {
    public NoAN raiz;

    public Alvinegra() {
        raiz = null;
    }

    public void inserir(int x) throws Exception{
        if(raiz == null) {
            raiz = new NoAN(x);
        }
        else if(raiz.esq == null && raiz.dir == null) {
            if(x < raiz.elemento) {
                raiz.esq = new NoAN(x);
            }
            else {
                raiz.dir = new NoAN(x);
            }
        }
        else if(raiz.esq == null) {
            if(x < raiz.elemento) {
                raiz.esq = new NoAN(x);
            }
            else if(x < raiz.dir.elemento) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = x;
            }
            else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }
        else if(raiz.dir == null) {
            if(x > raiz.elemento) {
                raiz.dir = new NoAN(x);
            }
            else if(x > raiz.esq.elemento) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = x;
            }
            else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }
        else {
            inserir(x, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    public void inserir(int x, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception{
        if(i == null) {
            if(x < pai.elemento) {
                i = pai.esq = new NoAN(x, true);
            }
            else {
                i = pai.dir = new NoAN(x, true);
            }
            if(pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        }
        else {
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz) {
                    i.cor = false;
                }
                else if(pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }

            if(x < i.elemento) {
                inserir(x, bisavo, avo, pai, i.esq);
            }
            else if(x > i.elemento) {
                inserir(x, bisavo, avo, pai, i.dir);
            }
            else {
                throw new Exception("Erro");
            }
        }
    }

    public void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if(pai.cor == true) {
            if(pai.elemento > avo.elemento) {
                if(i.elemento > pai.elemento) {
                    avo = rotacionarEsq(avo);
                }
                else {
                    avo = rotacionarDirEsq(avo);
                }
            }
            else {
                if(i.elemento < pai.elemento) {
                    avo = rotacionarDir(avo);
                }
                else {
                    avo = rotacionarEsqDir(avo);
                }
            }

            if(bisavo == null) {
                raiz = avo;
            }
            else if(avo.elemento < bisavo.elemento) {
                bisavo.esq = avo;
            }
            else {
                bisavo.dir = avo;
            }
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    public NoAN rotacionarDir(NoAN i) {
        NoAN noEsq = i.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = i;
        i.esq = noEsqDir;

        return noEsq;
    }

    public NoAN rotacionarEsq(NoAN i) {
        NoAN noDir = i.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = i;
        i.dir = noDirEsq;

        return noDir;
    }

    public NoAN rotacionarEsqDir(NoAN i) {
        i.esq = rotacionarEsq(i.esq);
        return rotacionarDir(i);
    }

    public NoAN rotacionarDirEsq(NoAN i) {
        i.dir = rotacionarDir(i.dir);
        return rotacionarEsq(i);
    }
}
