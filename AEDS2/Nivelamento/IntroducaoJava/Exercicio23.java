import mypackage.MyIO;

public class Exercicio23 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o inteiro N: ");
        int[] numeros = new int[N];
        float soma = 0;
        for(int i = 0; i < N; i++) {
            numeros[i] = MyIO.readInt("Digite o " + (i + 1) + "-o numero: ");
            soma += numeros[i];
        }
        float media = soma / N;
        MyIO.println("Inteiros maiores que a media:");
        for(int i = 0; i < N; i++) {
            if(numeros[i] > media) {
                MyIO.println(numeros[i]);
            }
        }
    }
}
