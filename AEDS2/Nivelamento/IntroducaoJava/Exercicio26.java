import mypackage.MyIO;

public class Exercicio26 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o inteiro N: ");
        int[] elementos = new int[N];
        int contador_par = 0;
        int contador_divisiveis_tres = 0;
        
        for(int i = 0; i < N; i++) {
            elementos[i] = MyIO.readInt("Digite o " + (i+1) + "-o elemento: ");
            if(elementos[i] % 2 == 0) {
                contador_par++;
            }
            if(elementos[i] % 3 == 0) {
                contador_divisiveis_tres++;
            }
        }

        MyIO.println("Quantidade de numeros pares: " + contador_par);
        MyIO.println("Quantidade de numeros divisiveis por 3: " + contador_divisiveis_tres);
    }    
}
