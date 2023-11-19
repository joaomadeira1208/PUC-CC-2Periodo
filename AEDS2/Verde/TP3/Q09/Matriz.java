import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Matriz {
    public Celula inicio;
    private int linha, coluna;

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;


        Celula i = new Celula();
        this.inicio = i;
        Celula j = i;
        for(int x = 1; x < coluna; x++) {
            j.dir = new Celula();
            j.dir.esq = j;
            j = j.dir;
        }

        for(int k = 1; k < linha; k++) {
            i.inf = new Celula();
            i.inf.sup = i;
            j = i.inf;
            i = i.inf;

            Celula tmp = j.sup.dir;

            for(int x = 1; x < coluna; x++) {
                j.dir = new Celula();
                j.dir.esq = j;
                j.dir.sup = tmp;
                tmp.inf = j.dir;
                j = j.dir;
                tmp = tmp.dir;
            }
        }
    }

    public void preencher(BufferedReader br) throws Exception{
        Celula tmp = inicio;
        for(int i = 0; i < linha; i++, tmp = tmp.inf) {
            Celula tmp2 = tmp;
            String str = br.readLine();
            String[] partes = str.split(" ");
            for(int j = 0; j < coluna; j++, tmp2 = tmp2.dir) {
                int numero = Integer.parseInt(partes[j]);
                tmp2.elemento = numero;
            }
        }
    }

    public Matriz soma(Matriz m) {
        Matriz C = null;
        if(this.linha == m.linha && this.coluna == m.coluna) {
            C = new Matriz(this.linha, this.coluna);

            for(Celula i = this.inicio, mi = m.inicio, ci = C.inicio;i != null; i = i.inf, mi = mi.inf, ci = ci.inf) {
                for(Celula j = i, mj = mi, cj = ci; j != null; j = j.dir, mj = mj.dir, cj = cj.dir) {
                    cj.elemento = j.elemento + mj.elemento;
                }
            }
            
        }
        return C;
    }

    public Matriz multiplicacao(Matriz m) {
        if (this.coluna == m.linha) {
            Matriz C = new Matriz(this.linha, m.coluna);
            Celula ref = C.inicio;
    
            for (Celula i = this.inicio; i != null; i = i.inf) {
                for (Celula j = m.inicio, cj = C.inicio; j != null; j = j.dir, cj = cj.dir) {
                    int soma = 0;
                    for (Celula k = i, mk = j; k != null && mk != null; k = k.dir, mk = mk.inf) {
                        soma += k.elemento * mk.elemento;
                    }
                    cj.elemento = soma;
                }
                if (C.inicio.inf != null) {
                    C.inicio = C.inicio.inf;
                }
            }
            C.inicio = ref;
            ref = null;
            return C;
        }
        return null; 
    }
    

    public void mostrarDiagonalPrincipal() {
        Celula i;

        for(i = inicio; i != null; i = i.inf) {
            System.out.print(i.elemento + " ");
            if(i.dir != null)
                i = i.dir;
        }

        System.out.println("");
    }

    public void mostrarDiagonalSecundaria() {
        Celula i;

        for(i = inicio; i.dir != null; i = i.dir);

        for (Celula j = i; j != null; ) {
            System.out.print(j.elemento + " ");
    
            if (j.inf != null && j.inf.esq != null) {
                j = j.inf.esq;
            } else {
                break;
            }
        }

        System.out.println("");
           
    }

    public static void main(String[] agrs)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int casos = Integer.parseInt(br.readLine());

        for(int i = 0; i < casos; i++) {
            int linhas = Integer.parseInt(br.readLine());
            int colunas = Integer.parseInt(br.readLine());

            Matriz a = new Matriz(linhas, colunas);

            a.preencher(br);

            linhas = Integer.parseInt(br.readLine());
            colunas = Integer.parseInt(br.readLine());

            Matriz b = new Matriz(linhas, colunas);

            b.preencher(br);

            a.mostrarDiagonalPrincipal();
            a.mostrarDiagonalSecundaria();

            Matriz soma = a.soma(b);
            for(Celula tmp = soma.inicio; tmp != null; tmp = tmp.inf) {
                for(Celula tmp2 = tmp; tmp2 != null; tmp2 = tmp2.dir) {
                    System.out.print(tmp2.elemento + " ");
                }
                System.out.println("");
            }
            Matriz multiplicacao = a.multiplicacao(b);
            for(Celula tmp = multiplicacao.inicio; tmp != null; tmp = tmp.inf) {
                for(Celula tmp2 = tmp; tmp2 != null; tmp2 = tmp2.dir) {
                    System.out.print(tmp2.elemento + " ");
                }
                System.out.println("");
            }
        }

            
    }
}


class Celula {
    public Celula sup, inf, esq, dir;
    public int elemento;

    public Celula() {
        this(0);
    }

    public Celula(int x) {
        this(x, null, null, null, null);
    }

    public Celula(int x, Celula sup, Celula inf, Celula esq, Celula dir) {
        this.elemento = x;
        this.sup = sup;
        this.inf = inf;
        this.esq = esq;
        this.dir = dir;
    }
}
