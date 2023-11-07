
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Face {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        
        while((entrada = br.readLine()) != null) {
            int numeroProcessos = Integer.parseInt(entrada);
            if(numeroProcessos < 1 || numeroProcessos > 105) {
                throw new Exception("Erro: numero de processos invalido");
            }

            int[] tempo = new int[numeroProcessos];
            int[] ciclos = new int[numeroProcessos];
            int soma = 0;
            int tempoEsperado = 0;
            int tempoAnterior = 1;
            for(int i = 0; i < numeroProcessos; i++) {
                String entrada2 = br.readLine();
                String []splits = entrada2.split(" ");
                tempo[i] = Integer.parseInt(splits[0]);
                ciclos[i] = Integer.parseInt(splits[1]);
            }

            ordenacao(ciclos, tempo);
            int resultado = 0;
            int ciclototal = ciclos[0] + 1;
            int clock = 1;
            for(int i = 1; i < numeroProcessos; i++) {
                resultado += ciclototal - tempo[i];
                ciclototal += ciclos[i];
            }

            System.out.println(resultado);
            
        }
    }
    

    public static void ordenacao(int[] ciclos, int[] tempo) {
        for(int i = 0; i < ciclos.length; i++) {
            for(int j = ciclos.length - 1; j > 1; j--) {
                if(ciclos[j] < ciclos[j-1]) {
                    int tmp = tempo[j];
                    tempo[j] = tempo[j-1];
                    tempo[j-1] = tmp;

                    tmp = ciclos[j];
                    ciclos[j] = ciclos[j-1];
                    ciclos[j-1] = tmp;
                }
            }
        }
    }
    
}
