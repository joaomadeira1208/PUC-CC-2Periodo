import java.util.Scanner;

public class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        // Alocar a primeira célula
    inicio = new Celula();

    // Alocar a primeira linha
    Celula temp = inicio;
    for (int j = 1; j < coluna; j++) {
        temp.dir = new Celula();
        temp.dir.esq = temp;
        temp = temp.dir;
    }

    // Alocar as demais linhas
    Celula tempLinhaAcima = inicio;
    for (int i = 1; i < linha; i++) {
        Celula novaLinha = new Celula();
        novaLinha.sup = tempLinhaAcima;
        tempLinhaAcima.inf = novaLinha;

        Celula tempColunaEsquerda = novaLinha;
        for (int j = 1; j < coluna; j++) {
            tempColunaEsquerda.dir = new Celula();
            tempColunaEsquerda.dir.esq = tempColunaEsquerda;
            tempColunaEsquerda.dir.sup = tempLinhaAcima.dir;

            tempLinhaAcima.dir.inf = tempColunaEsquerda.dir;

            tempColunaEsquerda = tempColunaEsquerda.dir;
            tempLinhaAcima = tempLinhaAcima.dir;
        }

        tempLinhaAcima = novaLinha;
    }
    }

    
    public Matriz soma(Matriz m) {
        Matriz resp = null;
    
        if(this.linha == m.linha && this.coluna == m.coluna){
            resp = new Matriz(this.linha, this.coluna);
    
            for(Celula i = this.inicio, mi = m.inicio, ri = resp.inicio; i != null; i = i.inf, mi = mi.inf, ri = ri.inf){
                for(Celula j = i, mj = mi, rj = ri; j != null; j = j.dir, mj = mj.dir, rj = rj.dir){
                    rj.elemento = j.elemento * mj.elemento;
                }
            }
        }
    
        return resp;
    }

    public void ler() {
        Scanner scanner = new Scanner(System.in);
    
        // Criar ponteiros temporários para percorrer a matriz
        Celula tempLinha = this.inicio;
        Celula tempColuna;
    
        // Iterar sobre as linhas
        for (int i = 0; i < this.linha; i++) {
            tempColuna = tempLinha;
    
            // Iterar sobre as colunas
            for (int j = 0; j < this.coluna; j++) {
                System.out.println("Digite o elemento [" + i + "][" + j + "]: ");
                tempColuna.elemento = scanner.nextInt(); // Lê o elemento do usuário
    
                // Mover para a próxima célula na coluna
                tempColuna = tempColuna.dir;
            }
    
            // Mover para a próxima linha
            tempLinha = tempLinha.inf;
        }
    }
    

public void imprimir() {
    // Ponteiro para percorrer as linhas
    for (Celula i = inicio; i != null; i = i.inf) {
        // Ponteiro para percorrer as colunas
        for (Celula j = i; j != null; j = j.dir) {
            System.out.print(j.elemento + " ");
        }
        System.out.println();
    }
}

public int[] getDiagonalPrincipal() {
    int[] array = new int[this.linha];
    int n = 0;
    Celula i;
    for(i = inicio; i != null; i = i.inf, n++) {
        array[n] = i.elemento;
        if(n != this.linha - 1)
            i = i.dir;
    }
    return array;
}

public int[] getDiagonalSecundaria() {
    int[] array = new int[this.linha];
    int n = 0;
    Celula i = inicio;
    for(int j = 0; j < this.linha - 1;j++, i = i.inf);
    Celula tmp;
    for(tmp = i; tmp != null;tmp = tmp.sup) {
        array[n++] = tmp.elemento;
        if(n != this.linha) {
            tmp = tmp.dir;
        }
    }
    return array;
}

public static void main(String[] args) {
    Matriz a = new Matriz(3, 3);
    Matriz b = new Matriz(3, 3);

    a.ler();
    int []diagonalPrincipal = a.getDiagonalSecundaria();
    for(int i = 0; i < diagonalPrincipal.length; i++) {
        System.out.println(diagonalPrincipal[i]);
    }    
}
}
