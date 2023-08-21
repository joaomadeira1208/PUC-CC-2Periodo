import mypackage.MyIO;

public class Exercicio35 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o numero N de linhas e colunas da matriz quadrada: ");
        int[][] Matriz = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Matriz[i][j] = MyIO.readInt("Digite o elemento da linha " + (i+1) + ", coluna " + (j+1) + ": ");
            }
        }

        //Diagonal Principal
        MyIO.println("Diagonal Principal: ");
        int j = 0;
        for(int i = 0; i < N; i++) {
            MyIO.print(Matriz[i][j] + " ");
            j++;
        }

        MyIO.println("");

        //Diagonal Secundaria
        MyIO.println("Diagonal secundaria: ");
        j = N - 1;
        for(int i = 0; i < N; i++) {
            MyIO.print(Matriz[i][j] + " ");
            j--;
        }
    }
}
