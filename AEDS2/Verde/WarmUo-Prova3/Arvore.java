import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Arvore {
    No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception {
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
            throw new Exception("Erro ao inserir");
        }
        return i;
    }

    public void mostrar(int numElementos) {
        mostrar(raiz, false);
    }

    public void mostrar(No i, boolean aux) {
        if(i != null) {
            if(i.esq != null) {
                System.out.print(i.esq.elemento + " ");
            }
            if(i.dir != null) {
                System.out.print(i.dir.elemento + " ");
            }
            if(!aux)
            mostrar(i.esq, true);

            if(!aux)
            mostrar(i.dir, true);
        }
    }

    public static void main(String[] agrs) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arvore arvore = new Arvore();
        String entrada = br.readLine();
        int numCasos = Integer.parseInt(entrada);
        for(int i = 0; i < numCasos; i++) {
            int numElementos = Integer.parseInt(br.readLine());
            entrada = br.readLine();
            String [] parts = entrada.split(" ");
            for(int j = 0; j < numElementos; j++) {
                int elemento = Integer.parseInt(parts[j]);
                arvore.inserir(elemento);
            }
            arvore.mostrar();
        }
    }

}

class No {
    No esq, dir;
    int elemento;

    public No(int elemento) {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
