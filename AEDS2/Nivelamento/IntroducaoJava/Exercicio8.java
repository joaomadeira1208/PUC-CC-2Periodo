import mypackage.MyIO;

public class Exercicio8 {
    public static void main(String[] args) {
        int menor = MyIO.readInt("Digite o valor do inteiro: ");
        int maior = menor;

        for(int i = 0; i < 9; i++) {
            int inteiro = MyIO.readInt("Digite o valor do inteiro: ");
            menor = Math.min(menor, inteiro);
            maior = Math.max(maior, inteiro);
        }

        MyIO.println("O menor inteiro lido = " + menor);
        MyIO.println("O maior inteiro lido = " + maior);
    }    
}
