import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Bingo {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada1;
        while((entrada1 = br.readLine()) != null) {
            String[] partes = entrada1.split(" ");
            int N = Integer.parseInt(partes[0]);
            int K = Integer.parseInt(partes[1]);
            int maiorNatural = Integer.parseInt(partes[2]);

            String entrada2;
            Cartela cartela = new Cartela(K, N);
            Cartela cartelaVencedora = new Cartela(maiorNatural);
            int tempo = maiorNatural + 1;
            int vencedor = -1;
            int contador = 0;
            
            for(int i = 0; i < N; i++) {
                entrada2 = br.readLine();
                cartela.inserir(entrada2, K);
                cartela.inserir(contador++);
            }

            entrada2 = br.readLine();
            cartelaVencedora.inserir(entrada2, maiorNatural);
                        
            for(int i = 0; i < N; i++) {
                if(cartela.completou(cartelaVencedora, i)) {
                    if(cartela.tempoCompletar(cartelaVencedora, i) < tempo) {
                        tempo = cartela.tempoCompletar(cartelaVencedora, i);
                        vencedor = i + 1;
                    }
                }
            }

            System.out.println(vencedor);
        }
    }


}

class Cartela {
    private int []array;
    private int [][]arrayCartelas;

    public Cartela(int K, int N) {
        this.array = new int[K];
        this.arrayCartelas = new int[N][K];
    }
    public Cartela(int K) {
        this.array = new int[K];
        this.arrayCartelas = null;
    }

    public void inserir(String entrada, int k) {
        String[] partes = entrada.split(" ");
        for(int i = 0; i < k; i++) {
            array[i] = Integer.parseInt(partes[i]);
        }
    }

    public void inserir(int contador) {
        for(int i = 0; i < array.length; i++) {
            arrayCartelas[contador][i] = array[i];
        }
    }

    public boolean completou(Cartela vencedora, int count) {
        int n = vencedora.array.length;
        int contador = 0;
        boolean numeroEncontrado;
        for(int i = 0; i < n; i++) {
            numeroEncontrado = false;
            for(int j = 0; j < array.length && !numeroEncontrado; j++) {
                if(arrayCartelas[count][j] == vencedora.array[i]) {
                    contador++;
                    numeroEncontrado = true;
                }
            }
        }
        if(contador == array.length) {
            return true;
        }
        else {
            return false;
        }
    }

    public int tempoCompletar(Cartela vencedora, int count) {
        int n = vencedora.array.length;
        int contador = 0;
        int tempo = 0;
        boolean numeroEncontrado;
        for(int i = 0; i < n && contador != array.length; i++) {
            numeroEncontrado = false;
            for(int j = 0; j < array.length && !numeroEncontrado && contador != array.length; j++) {
                if(arrayCartelas[count][j] == vencedora.array[i]) {
                    contador++;
                    numeroEncontrado = true;
                }
            }
            tempo++;
        
        }
        return tempo;
    }
}
