public class Lista {
    private Celula primeiro, ultimo;

    public Lista() {
        this.primeiro = new Celula();
        this.ultimo = this.primeiro;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        tmp = null;
        if(primeiro == ultimo) {
            ultimo = primeiro.prox;
        }
    }

    public void inserirFinal(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int removerInicio() throws Exception{
        if(primeiro == ultimo) {
            throw new Exception("Erro");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public int removerFinal() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception("Erro");
        }
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);
        int resp = ultimo.elemento;
        ultimo = i;
        i.prox = null;
        i = null;
        return resp;
    }

    public void inserir(int x, int pos) throws Exception {
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho) {
            throw new Exception();
        }
        else if(pos == 0) {
            inserirInicio(x);
        }
        else if(pos == tamanho) {
            inserirFinal(x);
        }
        else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null; 
        }
    }

    public int remover(int pos) throws Exception {
        int tamanho = tamanho();
        int resp;
        if(primeiro == ultimo || pos < 0 || pos >= tamanho) {
            throw new Exception();
        }
        else if(pos == 0) {
            resp = removerInicio();
        }
        else if(pos == tamanho - 1) {
            resp = removerFinal();
        }
        else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return resp;
    }

    public int tamanho() {
        Celula i;
        int tamanho = 0;
        for(i = primeiro.prox; i != null; i = i.prox, tamanho++);
        return tamanho;
    }

    public void inverter() throws Exception{
        if(primeiro == ultimo || primeiro.prox == ultimo) {
            return;
        }
        
        Celula atual = primeiro.prox;
        Celula ant = null;
        Celula proximo = null;

        while(atual != null) {
            proximo = atual.prox;
            atual.prox = ant;
            ant = atual;
            atual = proximo;
        }
        ultimo = primeiro.prox;
        primeiro.prox = ant;
        atual = ant = proximo = null;
        
    }
}