import mypackage.MyIO;

public class Exercicio12 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o inteiro N: ");
        int i = 1;
        int Elemento = 1;
        while(i <= N) {
            MyIO.println(Elemento + ", ");
            if(i % 2 != 0) {
                Elemento += 4;
            }
            else {
                Elemento += 7;
            }
            i++;
        }
    }    
}
