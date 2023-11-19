public class Pilha {
    private Celula topo;

    public Pilha() {
        topo = null;
    }

    public void inserir(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover() throws Exception {
        if(topo == null) {
            throw new Exception();
        }
        int resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public void mostrar() {
        Celula i;
        for(i = topo; i != null; i = i.prox) {
            System.out.println(i.elemento);
        }
    }
}
