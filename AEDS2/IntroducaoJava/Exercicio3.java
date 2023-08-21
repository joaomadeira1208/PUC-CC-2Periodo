import mypackage.MyIO;

public class Exercicio3 {
       public static void main(String[] args) {
            int maior = MyIO.readInt("Digite um valor inteiro: ");
            for(int i = 0; i < 9; i++) {
                int inteiro = MyIO.readInt("Digite um valor inteiro: ");
                 maior = Math.max(maior, inteiro);
            }
            System.out.println("O maior inteiro é " + maior);
       }
}
