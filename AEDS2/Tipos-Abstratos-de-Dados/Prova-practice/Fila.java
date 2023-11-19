public class Fila {
    private Celula primeiro, ultimo;

    public Fila() {
        this.primeiro = new Celula();
        this.ultimo = this.primeiro;
    }


    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover() throws Exception {
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

    public void mostrar() {
        Celula i;
        for(i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento);
        }
    }
}
