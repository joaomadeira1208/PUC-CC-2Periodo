import mypackage.MyIO;

public class Exercicio24 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o tamanho do array ");
        int[] array = new int[N];
        for(int i=0; i < N; i++){
            array[i] = MyIO.readInt("Digite o " + (i+1) + " elemento: ");
        }
        if(N%2 == 0){
            for(int i=0; i < N; i = i + 2){
                int soma = array[i] + array[i+1];
                MyIO.println(soma);
            }
        }
    }
}