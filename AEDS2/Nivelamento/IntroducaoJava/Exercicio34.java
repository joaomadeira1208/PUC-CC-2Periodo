import mypackage.MyIO;

public class Exercicio34 {
    public static void main(String[] args) {
        int L = MyIO.readInt("Digite quantas linhas vao ter: ");
        int C = MyIO.readInt("Digite quantas colunas vao ter: ");
    
        int[][] matriz = new int[L][C];
        int[][] matriz_2 = new int[L][C];        
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                matriz[i][j] = MyIO.readInt("Digite o elemento " + (i + 1) + (j + 1) + " da matriz 1 ");
                matriz_2[i][j] = MyIO.readInt("Digite o elemento " + (i + 1) + (j + 1) + " da matriz 2 ");

            }
        }
        int[][] matriz_soma = new int[L][C];
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                matriz_soma[i][j] = matriz[i][j] + matriz_2[i][j];
            }
        }
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                MyIO.print(matriz_soma[i][j] + "|");
            }
        MyIO.println(" ");
        }
    }
}