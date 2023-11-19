public class ListaDupla {
    private CelulaDupla primeiro, ultimo;

    public ListaDupla() {
        this.primeiro = new CelulaDupla();
        this.ultimo = this.primeiro;
    }

    public void inserirInicio(int x) {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        
        if(primeiro == ultimo) {
            ultimo = tmp;
        }
        else {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserirFinal(int x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public int removerInicio() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception("Erro");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        int resp = primeiro.elemento;
        primeiro.ant = null;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public int removerFinal() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception();
        }

        int resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
    }

    public void inserir(int x, int pos) throws Exception {
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho) {
            throw new Exception("Erro");
        }
        else if(pos == 0) {
            inserirInicio(x);
        }
        else if(pos == tamanho) {
            inserirFinal(x);
        }
        else {
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public int remover(int pos) throws Exception {
        int tamanho = tamanho();
        int resp;
        if(primeiro == ultimo || pos < 0 || pos >= tamanho) {
            throw new Exception("Erro");
        }
        else if(pos == 0) {
            resp = removerInicio();
        }
        else if(pos == tamanho - 1) {
            resp = removerFinal();
        }
        else {
            CelulaDupla i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);
            resp = i.elemento;
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            i.prox = i.ant = null;
            i = null;
        }
        return resp;
    }

    public void inverter() {
        if(primeiro == ultimo || primeiro.prox == ultimo) {
            return;
        }

        CelulaDupla atual = primeiro.prox;
        CelulaDupla tmp = null;
        while(atual != null) {
            tmp = atual.prox;
            atual.prox = atual.ant;
            atual.ant = tmp;

            if(tmp == null) {
                ultimo = primeiro.prox;
                primeiro.prox = atual;
            }
            atual = tmp;
        }
    }

    public int tamanho() {
        CelulaDupla i;
        int tamanho = 0;
        for(i = primeiro.prox; i != null; i = i.prox, tamanho++);
        return tamanho;
    }
}
