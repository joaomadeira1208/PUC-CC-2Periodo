import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SequenciaEspelho {
    // Função para calcular a sequência espelho
    public static String calcularSequenciaEspelho(int B, int E) {
        StringBuilder seq = new StringBuilder();
        for (int i = B; i <= E; i++) {
            seq.append(i);
        }
        return seq.toString() + new StringBuilder(seq.toString()).reverse().toString();
    }

    public static void main(String[] args) {
        try {
            // Abra o arquivo de entrada
            File inputFile = new File("pub.in");
            Scanner scanner = new Scanner(inputFile);

            // Lê a quantidade de casos de teste
            int C = scanner.nextInt();

            // Processa cada caso de teste
            for (int i = 0; i < C; i++) {
                int B = scanner.nextInt();
                int E = scanner.nextInt();
                String resultado = calcularSequenciaEspelho(B, E);
                System.out.println(resultado);
            }

            // Fecha o arquivo de entrada
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo 'pub.in' não encontrado.");
        }
    }
}
