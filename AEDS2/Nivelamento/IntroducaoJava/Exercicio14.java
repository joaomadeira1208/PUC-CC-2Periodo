import mypackage.MyIO;

public class Exercicio14 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o inteiro N: ");
        int i = 0;
        int Termo_1 = 1;
        int Termo_2 = 1;
        while(i < N) {
            int aux = Termo_1 + Termo_2;
            MyIO.print(Termo_1 + ", ");
            Termo_1 = Termo_2;
            Termo_2 = aux;
            i++;
        }
    }
}
