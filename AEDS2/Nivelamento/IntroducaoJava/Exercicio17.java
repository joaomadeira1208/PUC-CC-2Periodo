import mypackage.MyIO;
public class Exercicio17 {
    public static void main(String[] args) {
        int n = MyIO.readInt("Digite n: ");
        double[] numeros = new double[n];
        for(int i = 0; i < n; i++) {
            numeros[i] = MyIO.readDouble("Digite o numero: ");
            
        }
        double soma = 0;

        for(int i = 0; 2*i+1 < n; i++) {
            soma += numeros[i] + numeros[2*i + 1];
        }

        MyIO.println("A soma dos termos = " + soma);


    }
}
