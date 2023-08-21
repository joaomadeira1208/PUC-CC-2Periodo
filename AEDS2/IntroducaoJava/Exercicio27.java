import mypackage.MyIO;

public class Exercicio27 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite a quantidade de numeros que serao lidos: ");
        double[] numeros = new double[N];
        for(int i = 0; i < N; i++) {
            numeros[i] = MyIO.readDouble("Digite o " + (i+1) + "-o numero: ");
        }

        int j = N -1;
        MyIO.println("");
        for(int i = 0; i < N/2; i++) {
             MyIO.println(numeros[i] + " + " + numeros[j] + " = " + (numeros[i] + numeros[j]));
             j--;
        }
    }
}
