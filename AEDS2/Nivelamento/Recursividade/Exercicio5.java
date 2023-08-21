

public class Exercicio5 {
    public static int multiplicacaoComSoma(int num1, int num2) {
        if (num2 != 0) {
            return num1 + multiplicacaoComSoma(num1, num2 - 1);
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        int resultado = multiplicacaoComSoma(5, 3);
        System.out.println("Resultado: " + resultado);
    }
}