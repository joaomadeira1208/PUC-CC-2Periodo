Celula* encontrarRepetidos(No* raiz, CelulaMatriz *inicio) {
    Celula* resp = null;
    int contador = 0;
    if(raiz != null) {
        resp = encontrarRepetidos(raiz.esq, inicio);
        bool tmp = numeroEncontrado(raiz->numero, inicio);
        if(tmp && contador == 0) {
            resp.elemento = raiz.numero;
            contador++;
            return resp;
        }
        resp = encontrarRepetidos(raiz.dir, inicio);
    }
    return resp;
}

bool numeroEncontrado(int numero, CelulaMatriz *inicio) {
    boolean resp = false;
    CelulaMatriz *tmp;
    CelulaMatriz *tmp2 = inicio
    int l = 0;
    for(tmp = inicio; tmp != NULL; tmp = tmp->inf, l++);
    for(int j = 0; j < l && !resp; j++) {
        for(tmp = tmp2; tmp != NULL && !resp; tmp = tmp->dir) {
            if(tmp->elemento == numero) {
                resp = true;
            }
        }
        tmp2 = tmp2->inf;
    }
    return resp;
}