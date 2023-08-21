import mypackage.MyIO;

public class Exercicio32 {
    public static void main(String[] args) {
        int L = MyIO.readInt("Digite quantas linhas vao ter: ");
        int C = MyIO.readInt("Digite quantas colunas vao ter: ");
    
        int[][] matriz = new int[L][C];
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                matriz[i][j] = MyIO.readInt("Digite o elemento " + (i + 1) + (j + 1) + " da matriz ");
            }
        }
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                MyIO.print(matriz[i][j] + "|");
            }
        MyIO.println(" ");
        }
    }
}