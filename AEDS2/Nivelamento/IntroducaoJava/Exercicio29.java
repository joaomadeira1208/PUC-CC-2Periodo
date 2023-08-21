import mypackage.MyIO;

public class Exercicio29 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite a quantidade de elementos que os dois vetores possuem: ");
        double[] Vetor_1 = new double[N];
        double[] Vetor_2 = new double[N];

        for(int i = 0; i < N; i++) {
            Vetor_1[i] = MyIO.readDouble("Digite o " + (i+1) + "-o elemento do vetor 1: ");
            Vetor_2[i] = MyIO.readDouble("Digite o " + (i+1) + "-o elemento do vetor 2: ");
        }

        MyIO.println("");

        for(int i = 0; i < N; i++) {
            MyIO.println((i+1) + "-o elemento do vetor 1: " + Vetor_1[i]);
            MyIO.println((i+1) + "-o elemento do vetor 2: " + Vetor_2[i]);
        }
    }
}
