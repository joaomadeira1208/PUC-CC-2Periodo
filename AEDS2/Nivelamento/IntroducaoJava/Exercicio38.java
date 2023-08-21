import mypackage.MyIO;

public class Exercicio38 {
    public static void main(String[] args) {
        int L = MyIO.readInt("Digite a quantidade de linhas da matriz: ");
        int C = MyIO.readInt("Digite a quantidade de colunas da matriz: ");

        int[][] Matriz = new int[L][C];

        for(int j = 0; j < C; j++) {
            float soma = 0;
            for(int i = 0; i < L; i++) {
                Matriz[i][j] = MyIO.readInt("Digite o elemento da linha " + (i+1) + ", coluna " + (j+1) + ": ");
                soma += Matriz[i][j];
            }
            float media = soma / L;
            MyIO.println("Media da coluna " + (j+1) + " = " + media);
        }

    }
}

