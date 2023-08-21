import mypackage.MyIO;

public class Exercicio15 {
    public static void main(String[] args) {
        int n = MyIO.readInt("Digite o quantos inteiros serao lidos: ");
        int[] numeros = new int[n];
        int soma = 0;
        for(int i = 0; i < n; i++) {
            numeros[i] = MyIO.readInt("Digite o valor do inteiro: ");
            soma += numeros[i];
        }   
        int media = soma / n;

        MyIO.println("Inteiros acima da media: ");
        for(int i = 0; i < n; i++) {
            int j = i + 1;
            if(numeros[i] > media) {
                MyIO.println("Valor " + j + ": " + numeros[i]);
            }
        }

    }
}
