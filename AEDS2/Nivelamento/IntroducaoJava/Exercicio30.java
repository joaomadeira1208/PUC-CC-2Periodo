import mypackage.MyIO;

public class Exercicio30 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite a quantidade N de elementos: ");
        int M = MyIO.readInt("Digite a quantidade M de elementos: ");

        double[] Vetor_1 = new double[N];
        double[] Vetor_2 = new double[M];

        for(int i = 0; i < N; i++) {
            Vetor_1[i] = MyIO.readDouble("Digite o " + (i+1) + "-o elemento do vetor 1: ");
        }
        MyIO.println("");
        for(int i = 0; i < M; i++) {
            Vetor_2[i] = MyIO.readDouble("Digite o " + (i+1) + "-o elemento do vetor 2: ");
        }
        MyIO.println("");

        int j = 0;
        for(int i = 0; i < Math.max(N, M); i++) {
            if(i <= N -1 ) {
                MyIO.println("Elemento " + (i+1) + " do vetor 1: " + Vetor_1[i]);
            }
            if(j <= M - 1) {
                MyIO.println("Elemento " + (j+1) + " do vetor 2: " + Vetor_2[i]);
            }
            j++;
        }
    }
}
