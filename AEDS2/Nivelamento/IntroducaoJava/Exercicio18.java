import mypackage.MyIO;

public class Exercicio18 {
    public static void main(String[] args) {
        int n = MyIO.readInt("Digite o tamanho do array: ");
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = MyIO.readInt("Digite o elemento " + (i + 1) + ": ");
        }

        int menorElemento = array[0];
        int posicaoMenor = 0;

        for (int i = 1; i < n; i++) {
            if (array[i] < menorElemento) {
                menorElemento = array[i];
                posicaoMenor = i;
            }
        }

        MyIO.println("O menor elemento do array está na posição: " + posicaoMenor);
    }
}