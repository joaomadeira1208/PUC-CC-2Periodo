

public void rotacionarEsq() {
    raiz = rotacionarEsq(raiz);
}

public No rotacionarEsq(No i) {
    No noDir = i.dir;
    No noDirEsq = noDir.esq;

    noDir.esq = i;
    i.dir = noDirEsq;
    return noDir;
}

public void rotacionarDir() {
    raiz = rotacionarDir(raiz);
}

public No rotacionarDir(No i) {
    No noEsq = i.esq;
    No noEsqDir = noEsq.dir;

    noEsq.dir = i;
    i.esq = noEsqDir;
    return noEsq;
}

public void rotacionarDirEsq() {
    raiz = rotacionarDirEsq(raiz);
}

public No rotacionarDirEsq(No i) {
    i.dir = rotacionarDir(i.dir);
    return rotacionarEsq(i);
}

public void rotacionarEsqDir() {
    raiz = rotacionarEsqDir(raiz);
}

public No rotacionarEsqDir(No i) {
    i.esq = rotacionarEsq(i.esq);
    return rotacionarDir(i);
}
