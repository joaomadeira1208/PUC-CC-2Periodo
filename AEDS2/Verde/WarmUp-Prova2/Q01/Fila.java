import java.util.Scanner;

public class Fila {


    public static int ordenacaoSelecao(int[] arrayNotas) {
        int contador = 0;
        for(int i = 0; i < arrayNotas.length - 1; i++) {
            if(arrayNotas[i] < arrayNotas[i+1]) {
                int tmp = arrayNotas[i];
                arrayNotas[i] = arrayNotas[i+1];
                arrayNotas[i+1] = tmp;
                if(arrayNotas.length - contador == 1) {
                    contador += 1;
                }
                else {
                    contador += 2;
                }
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int quantidadeTestes = sc.nextInt();
        for(int i = 0; i < quantidadeTestes; i++) {
            int quantidadeAlunos = sc.nextInt();
            sc.nextLine();
            String notas = sc.nextLine();
            int[] arrayNotas = new int[quantidadeAlunos];
            String[] splits = notas.split(" ");
            for(int j = 0; j < quantidadeAlunos;j++) {
                arrayNotas[j] = Integer.parseInt(splits[j]);
            }

            int movimentos = ordenacaoSelecao(arrayNotas);
            int resp = quantidadeAlunos - movimentos;
            System.out.println(resp);
            
        }
    }
}