import mypackage.MyIO;

public class Exercicio21 {
    public static void main(String[] args) {
        int[] vetor = {10, 5, 8, 2, 8};
        for(int i = 0; i < vetor.length; i++) {
            MyIO.println("Termo " + (i+1) + " contido no vetor: " + vetor[i]);
        } 
    }
}
