import mypackage.MyIO;

public class Exercicio33 {
    public static void main(String[] args) {
        int L = MyIO.readInt("Digite a quantidade de linhas da matriz: ");
        int C = MyIO.readInt("Digite a quantidade de colunas da matriz: ");

        int[][] Matriz = new int[L][C];

        for(int i = 0; i < L; i++) {
            for(int j = 0; j < C; j++) {
                Matriz[i][j] = MyIO.readInt("Digite o elemento da linha " + (i+1) + ", coluna " + (j+1) + ": ");
            }
        }

        for(int i = 0; i < C; i++) {
            for(int j = 0; j < L; j++) {
                MyIO.print(Matriz[j][i] + " ");
            }
            MyIO.println("");
        }
    }
}
