import mypackage.MyIO;

public class Exercicio31 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite a quantidade de elementos do vetor: ");
        double[] Vetor = new double[N];

        MyIO.println("Digite os numeros do vetor da forma que voce quiser (escreva o numero e aperte ENTER): ");
        for(int i = 0; i < N; i++) {
            Vetor[i] = MyIO.readDouble("");
        }

        MyIO.println("Vetor antes da ordenacao: ");
        for(int i = 0; i < N; i++) {
            MyIO.print(Vetor[i] + ", ");
        }

        MyIO.println("");


        double aux;
        for(int i = 0; i < N; i++) {
            for(int j = N - 1; j > i; j--) {
                if(Vetor[i] > Vetor[j]) {
                    aux = Vetor[i];
                    Vetor[i] = Vetor[j];
                    Vetor[j] = aux;
                }
            }
        }
        MyIO.println("Vetor depois da ordenacao: ");
        for(int i = 0; i < N; i++) {
            MyIO.print(Vetor[i] + ", ");
        }

    }
}
