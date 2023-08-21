import mypackage.MyIO;

public class Exercicio28 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite a quantidade de numeros que serao lidos: ");
        int[] numeros = new int[N];
        int soma = 0;
        for(int i = 0; i < N; i++) {
            numeros[i] = MyIO.readInt("Digite o " + (i+1) + "-o numero: ");
            if(numeros[i] % 3 == 0) {
                soma += numeros[i];
            }
        }
        MyIO.println("");
        MyIO.println("Soma dos numeros divisiveis por 3 presentes no array = " + soma);
    }
}
